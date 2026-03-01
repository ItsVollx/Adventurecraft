package net.minecraft.src;

public class AC_EntityNPC extends EntityCreature {
	public String npcName = "Villager";
	public String dialogue = "";
	private int dialogueCooldown = 0;

	public AC_EntityNPC(World world) {
		super(world);
		this.texture = "/mob/char.png";
		this.moveSpeed = 0.0F;
		this.health = 20;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if(this.dialogueCooldown > 0) {
			this.dialogueCooldown--;
		}
		// NPCs don't move by default
		this.motionX = 0.0;
		this.motionZ = 0.0;
	}

	protected void updatePlayerActionState() {
		// Look at nearest player
		EntityPlayer player = this.worldObj.getClosestPlayerToEntity(this, 8.0);
		if(player != null) {
			this.faceEntity(player, 10.0F, 40.0F);
		}
	}

	public boolean interact(EntityPlayer player) {
		if(this.dialogueCooldown <= 0) {
			if(this.dialogue != null && this.dialogue.length() > 0) {
				// Show dialogue lines
				String[] lines = this.dialogue.split("\\|");
				for(int i = 0; i < lines.length; i++) {
					player.addChatMessage("<" + this.npcName + "> " + lines[i]);
				}
			} else {
				player.addChatMessage("<" + this.npcName + "> ...");
			}
			this.dialogueCooldown = 20;
		}
		return true;
	}

	protected Entity findPlayerToAttack() {
		return null;
	}

	protected boolean canDespawn() {
		return false;
	}

	protected String getLivingSound() {
		return null;
	}

	protected String getHurtSound() {
		return "random.hurt";
	}

	protected String getDeathSound() {
		return "random.hurt";
	}

	protected int getDropItemId() {
		return 0;
	}

	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setString("NpcName", this.npcName);
		nbt.setString("Dialogue", this.dialogue);
	}

	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		this.npcName = nbt.getString("NpcName");
		this.dialogue = nbt.getString("Dialogue");
		if(this.npcName == null || this.npcName.length() == 0) {
			this.npcName = "Villager";
		}
	}
}
