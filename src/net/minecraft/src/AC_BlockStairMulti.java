package net.minecraft.src;

import java.util.ArrayList;

/**
 * Stair block with multiple subtypes.
 * Uses metadata >> 2 for texture subtype, lower 2 bits for orientation.
 * Renders as stairs (render type 10).
 * Has stair-like collision (2 boxes) matching vanilla BlockStairs.
 */
public class AC_BlockStairMulti extends Block implements AC_IBlockColor {
	private Block modelBlock;

	public AC_BlockStairMulti(int id, int tex, Block model) {
		super(id, tex, model.blockMaterial);
		this.modelBlock = model;
		this.setHardness(model.blockHardness);
		this.setResistance(model.blockResistance / 3.0F);
		this.setStepSound(model.stepSound);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	public int getBlockTextureFromSideAndMetadata(int side, int metadata) {
		return this.blockIndexInTexture + (metadata >> 2);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public int getRenderType() {
		return 10; // Stairs
	}

	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	public void getCollidingBoundingBoxes(World world, int x, int y, int z, AxisAlignedBB mask, ArrayList list) {
		int meta = world.getBlockMetadata(x, y, z) & 3;

		if(meta == 0) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.5F, 0.5F, 1.0F);
			super.getCollidingBoundingBoxes(world, x, y, z, mask, list);
			this.setBlockBounds(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			super.getCollidingBoundingBoxes(world, x, y, z, mask, list);
		} else if(meta == 1) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F);
			super.getCollidingBoundingBoxes(world, x, y, z, mask, list);
			this.setBlockBounds(0.5F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
			super.getCollidingBoundingBoxes(world, x, y, z, mask, list);
		} else if(meta == 2) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 0.5F);
			super.getCollidingBoundingBoxes(world, x, y, z, mask, list);
			this.setBlockBounds(0.0F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F);
			super.getCollidingBoundingBoxes(world, x, y, z, mask, list);
		} else {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
			super.getCollidingBoundingBoxes(world, x, y, z, mask, list);
			this.setBlockBounds(0.0F, 0.0F, 0.5F, 1.0F, 0.5F, 1.0F);
			super.getCollidingBoundingBoxes(world, x, y, z, mask, list);
		}

		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity) {
		int subtype = (world.getBlockMetadata(x, y, z) >> 2) & 3;
		int dir = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		int orientation;
		if(dir == 0) {
			orientation = 2;
		} else if(dir == 1) {
			orientation = 1;
		} else if(dir == 2) {
			orientation = 3;
		} else {
			orientation = 0;
		}
		world.setBlockMetadataWithNotify(x, y, z, orientation | (subtype << 2));
	}

	public void incrementColor(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		int orientation = meta & 3;
		int subtype = meta >> 2;
		subtype = (subtype + 1) % Block.subTypes[this.blockID];
		world.setBlockMetadataWithNotify(x, y, z, orientation | (subtype << 2));
	}
}
