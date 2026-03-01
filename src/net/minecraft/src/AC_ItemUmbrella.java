package net.minecraft.src;

import java.util.List;

public class AC_ItemUmbrella extends Item {

	public AC_ItemUmbrella(int itemId) {
		super(itemId);
		this.maxStackSize = 1;
		this.setMaxDamage(256);
		this.setItemName("acUmbrella");
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(!world.multiplayerWorld) {
			// Slow fall if in air
			if(!player.onGround) {
				player.motionY = -0.1;
				player.fallDistance = 0.0F;
			}

			// Push nearby entities away
			List entities = world.getEntitiesWithinAABBExcludingEntity(player,
				player.boundingBox.expand(3.0, 2.0, 3.0));
			if(entities != null) {
				for(int i = 0; i < entities.size(); i++) {
					Entity entity = (Entity)entities.get(i);
					double dx = entity.posX - player.posX;
					double dz = entity.posZ - player.posZ;
					double dist = MathHelper.sqrt_double(dx * dx + dz * dz);
					if(dist > 0.0) {
						entity.motionX += dx / dist * 0.8;
						entity.motionY += 0.3;
						entity.motionZ += dz / dist * 0.8;
					}
				}
			}

			world.playSoundAtEntity(player, "random.pop", 0.3F, 0.5F);
			stack.damageItem(1, player);
		}
		return stack;
	}
}
