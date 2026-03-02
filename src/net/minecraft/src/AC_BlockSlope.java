package net.minecraft.src;

import java.util.ArrayList;

/**
 * Slope decorative block (diagonal/ramp shape).
 * Extends AC_BlockStairMulti but uses slope rendering.
 * Collision uses 2 boxes identical to vanilla stairs — the only
 * approach that works smoothly in Beta 1.7.3's collision engine.
 * Each step = 0.5 blocks = player's stepHeight, so auto-climb is seamless.
 * Metadata lower 2 bits = rotation (0-3).
 */
public class AC_BlockSlope extends AC_BlockStairMulti {

	public AC_BlockSlope(int id, int tex, Block model) {
		super(id, tex, model);
	}

	public int getRenderType() {
		return 38;
	}

	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	public void getCollidingBoundingBoxes(World world, int x, int y, int z, AxisAlignedBB mask, ArrayList list) {
		int meta = world.getBlockMetadata(x, y, z) & 3;

		if(meta == 0) {
			// Rises toward +X: low half on left, full on right
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.5F, 0.5F, 1.0F);
			super.getCollidingBoundingBoxes(world, x, y, z, mask, list);
			this.setBlockBounds(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			super.getCollidingBoundingBoxes(world, x, y, z, mask, list);
		} else if(meta == 1) {
			// Rises toward -X: full on left, low half on right
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F);
			super.getCollidingBoundingBoxes(world, x, y, z, mask, list);
			this.setBlockBounds(0.5F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
			super.getCollidingBoundingBoxes(world, x, y, z, mask, list);
		} else if(meta == 2) {
			// Rises toward +Z: low half at front, full at back
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 0.5F);
			super.getCollidingBoundingBoxes(world, x, y, z, mask, list);
			this.setBlockBounds(0.0F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F);
			super.getCollidingBoundingBoxes(world, x, y, z, mask, list);
		} else {
			// Rises toward -Z: full at front, low half at back
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
			super.getCollidingBoundingBoxes(world, x, y, z, mask, list);
			this.setBlockBounds(0.0F, 0.0F, 0.5F, 1.0F, 0.5F, 1.0F);
			super.getCollidingBoundingBoxes(world, x, y, z, mask, list);
		}

		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	public void setBlockBoundsForItemRender() {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}
}
