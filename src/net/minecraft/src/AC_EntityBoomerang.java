package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

public class AC_EntityBoomerang extends Entity {
	private EntityPlayer thrower;
	private int ticksInAir = 0;
	private boolean returning = false;
	private static final int RETURN_TICK = 15;
	private static final int MAX_TICKS = 100;

	/** Items being carried by the boomerang (Zelda-style pickup and return) */
	public ArrayList carriedItems = new ArrayList();
	private static final int MAX_CARRIED = 5;

	public AC_EntityBoomerang(World world) {
		super(world);
		this.setSize(0.5F, 0.5F);
	}

	public AC_EntityBoomerang(World world, EntityPlayer thrower) {
		super(world);
		this.thrower = thrower;
		this.setSize(0.5F, 0.5F);

		this.setPosition(thrower.posX, thrower.posY + 1.2, thrower.posZ);

		float yaw = thrower.rotationYaw / 180.0F * 3.14159F;
		float pitch = thrower.rotationPitch / 180.0F * 3.14159F;
		this.motionX = -MathHelper.sin(yaw) * MathHelper.cos(pitch) * 1.5;
		this.motionY = -MathHelper.sin(pitch) * 1.0;
		this.motionZ = MathHelper.cos(yaw) * MathHelper.cos(pitch) * 1.5;
	}

	protected void entityInit() {
	}

	public void onUpdate() {
		super.onUpdate();
		this.ticksInAir++;

		if(this.ticksInAir > MAX_TICKS) {
			this.dropCarriedItems();
			this.setEntityDead();
			return;
		}

		if(this.ticksInAir >= RETURN_TICK) {
			this.returning = true;
		}

		if(this.returning && this.thrower != null) {
			double dx = this.thrower.posX - this.posX;
			double dy = (this.thrower.posY + 1.0) - this.posY;
			double dz = this.thrower.posZ - this.posZ;
			double dist = MathHelper.sqrt_double(dx * dx + dy * dy + dz * dz);

			if(dist < 1.5) {
				// Caught - deliver carried items to player
				this.deliverItemsToPlayer();
				this.setEntityDead();
				return;
			}

			double speed = 1.2;
			this.motionX = dx / dist * speed;
			this.motionY = dy / dist * speed;
			this.motionZ = dz / dist * speed;
		} else if(!this.returning) {
			// Slow down slightly
			this.motionX *= 0.98;
			this.motionY *= 0.98;
			this.motionZ *= 0.98;
		}

		// Move
		this.moveEntity(this.motionX, this.motionY, this.motionZ);

		// Check block collision
		int bx = MathHelper.floor_double(this.posX);
		int by = MathHelper.floor_double(this.posY);
		int bz = MathHelper.floor_double(this.posZ);
		int blockId = this.worldObj.getBlockId(bx, by, bz);
		if(blockId > 0 && Block.blocksList[blockId] != null && Block.blocksList[blockId].isOpaqueCube()) {
			if(!this.returning) {
				this.returning = true;
			}
		}

		// Hit entities
		if(!this.worldObj.multiplayerWorld) {
			List entities = this.worldObj.getEntitiesWithinAABBExcludingEntity(this,
				this.boundingBox.expand(0.5, 0.5, 0.5));
			if(entities != null) {
				for(int i = 0; i < entities.size(); i++) {
					Entity entity = (Entity)entities.get(i);
					if(entity != this.thrower && entity instanceof EntityLiving) {
						entity.attackEntityFrom(this.thrower, 3);
						if(!this.returning) {
							this.returning = true;
						}
					}
				}
			}

			// Pick up items - carry them instead of giving directly
			if(this.carriedItems.size() < MAX_CARRIED) {
				List items = this.worldObj.getEntitiesWithinAABB(EntityItem.class,
					this.boundingBox.expand(1.0, 1.0, 1.0));
				if(items != null) {
					for(int i = 0; i < items.size(); i++) {
						if(this.carriedItems.size() >= MAX_CARRIED) break;
						EntityItem entityItem = (EntityItem)items.get(i);
						if(entityItem.delayBeforeCanPickup <= 0 && !entityItem.isDead) {
							// Store the item stack and remove the entity from world
							this.carriedItems.add(entityItem.item.copy());
							entityItem.setEntityDead();
							this.worldObj.playSoundAtEntity(this, "random.pop", 0.2F,
								((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
						}
					}
				}
			}
		}

		// Spawn particles
		this.worldObj.spawnParticle("crit", this.posX, this.posY, this.posZ, 0.0, 0.0, 0.0);
	}

	/** Deliver all carried items to the thrower's inventory */
	private void deliverItemsToPlayer() {
		if(this.thrower == null) {
			this.dropCarriedItems();
			return;
		}
		for(int i = 0; i < this.carriedItems.size(); i++) {
			ItemStack stack = (ItemStack)this.carriedItems.get(i);
			if(!this.thrower.inventory.addItemStackToInventory(stack)) {
				// Inventory full - drop the item
				this.thrower.dropPlayerItem(stack);
			}
		}
		this.carriedItems.clear();
	}

	/** Drop all carried items at the boomerang's current position */
	private void dropCarriedItems() {
		for(int i = 0; i < this.carriedItems.size(); i++) {
			ItemStack stack = (ItemStack)this.carriedItems.get(i);
			EntityItem drop = new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, stack);
			drop.delayBeforeCanPickup = 10;
			this.worldObj.entityJoinedWorld(drop);
		}
		this.carriedItems.clear();
	}

	public void setEntityDead() {
		// Drop any remaining carried items before dying
		if(!this.carriedItems.isEmpty() && !this.worldObj.multiplayerWorld) {
			this.dropCarriedItems();
		}
		super.setEntityDead();
	}

	public void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setShort("TicksInAir", (short)this.ticksInAir);
		nbt.setBoolean("Returning", this.returning);

		// Save carried items
		NBTTagList itemList = new NBTTagList();
		for(int i = 0; i < this.carriedItems.size(); i++) {
			ItemStack stack = (ItemStack)this.carriedItems.get(i);
			NBTTagCompound itemTag = new NBTTagCompound();
			stack.writeToNBT(itemTag);
			itemList.setTag(itemTag);
		}
		nbt.setTag("CarriedItems", itemList);
	}

	public void readEntityFromNBT(NBTTagCompound nbt) {
		this.ticksInAir = nbt.getShort("TicksInAir");
		this.returning = nbt.getBoolean("Returning");

		// Load carried items
		this.carriedItems.clear();
		NBTTagList itemList = nbt.getTagList("CarriedItems");
		if(itemList != null) {
			for(int i = 0; i < itemList.tagCount(); i++) {
				NBTTagCompound itemTag = (NBTTagCompound)itemList.tagAt(i);
				ItemStack stack = new ItemStack(itemTag);
				if(stack != null) {
					this.carriedItems.add(stack);
				}
			}
		}
	}
}
