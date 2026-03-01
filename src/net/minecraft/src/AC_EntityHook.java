package net.minecraft.src;

public class AC_EntityHook extends Entity {
	private EntityPlayer thrower;
	private int ticksInAir = 0;
	private boolean hooked = false;
	private int hookedX, hookedY, hookedZ;
	private static final int MAX_TICKS = 100;
	private static final double PULL_SPEED = 0.8;

	public AC_EntityHook(World world) {
		super(world);
		this.setSize(0.25F, 0.25F);
	}

	public AC_EntityHook(World world, EntityPlayer thrower) {
		super(world);
		this.thrower = thrower;
		this.setSize(0.25F, 0.25F);

		this.setPosition(thrower.posX, thrower.posY + 1.5, thrower.posZ);

		float yaw = thrower.rotationYaw / 180.0F * 3.14159F;
		float pitch = thrower.rotationPitch / 180.0F * 3.14159F;
		this.motionX = -MathHelper.sin(yaw) * MathHelper.cos(pitch) * 2.0;
		this.motionY = -MathHelper.sin(pitch) * 2.0;
		this.motionZ = MathHelper.cos(yaw) * MathHelper.cos(pitch) * 2.0;
	}

	protected void entityInit() {
	}

	public void onUpdate() {
		super.onUpdate();
		this.ticksInAir++;

		if(this.ticksInAir > MAX_TICKS) {
			this.setEntityDead();
			return;
		}

		if(this.thrower == null || this.thrower.isDead) {
			this.setEntityDead();
			return;
		}

		if(this.hooked) {
			// Pull player toward hook point
			double dx = (this.hookedX + 0.5) - this.thrower.posX;
			double dy = (this.hookedY + 0.5) - this.thrower.posY;
			double dz = (this.hookedZ + 0.5) - this.thrower.posZ;
			double dist = MathHelper.sqrt_double(dx * dx + dy * dy + dz * dz);

			if(dist < 1.5) {
				// Arrived
				this.thrower.motionX = 0;
				this.thrower.motionY = 0;
				this.thrower.motionZ = 0;
				this.thrower.fallDistance = 0.0F;
				this.setEntityDead();
				return;
			}

			this.thrower.motionX = dx / dist * PULL_SPEED;
			this.thrower.motionY = dy / dist * PULL_SPEED;
			this.thrower.motionZ = dz / dist * PULL_SPEED;
			this.thrower.fallDistance = 0.0F;

			// Spawn chain particles between player and hook
			for(int i = 0; i < 3; i++) {
				double t = (double)i / 3.0;
				double px = this.thrower.posX + dx * t;
				double py = this.thrower.posY + 1.0 + dy * t;
				double pz = this.thrower.posZ + dz * t;
				this.worldObj.spawnParticle("smoke", px, py, pz, 0.0, 0.0, 0.0);
			}
		} else {
			// Flying toward target
			this.motionY -= 0.03;
			this.moveEntity(this.motionX, this.motionY, this.motionZ);

			// Check for block hit
			int bx = MathHelper.floor_double(this.posX);
			int by = MathHelper.floor_double(this.posY);
			int bz = MathHelper.floor_double(this.posZ);
			int blockId = this.worldObj.getBlockId(bx, by, bz);

			if(blockId > 0 && Block.blocksList[blockId] != null && Block.blocksList[blockId].isOpaqueCube()) {
				this.hooked = true;
				this.hookedX = bx;
				this.hookedY = by;
				this.hookedZ = bz;
				this.motionX = 0;
				this.motionY = 0;
				this.motionZ = 0;
				this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.click", 0.5F, 1.0F);
			}

			// Spawn trail particles
			this.worldObj.spawnParticle("smoke", this.posX, this.posY, this.posZ, 0.0, 0.0, 0.0);
		}
	}

	public void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setBoolean("Hooked", this.hooked);
		nbt.setInteger("HookedX", this.hookedX);
		nbt.setInteger("HookedY", this.hookedY);
		nbt.setInteger("HookedZ", this.hookedZ);
	}

	public void readEntityFromNBT(NBTTagCompound nbt) {
		this.hooked = nbt.getBoolean("Hooked");
		this.hookedX = nbt.getInteger("HookedX");
		this.hookedY = nbt.getInteger("HookedY");
		this.hookedZ = nbt.getInteger("HookedZ");
	}
}
