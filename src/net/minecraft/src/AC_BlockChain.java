package net.minecraft.src;

/**
 * Chain decorative block (cross-shaped).
 * Uses two alternating textures per subtype.
 * side % 2 alternates between two face textures.
 * metadata / 3 selects the subtype, multiplied by 2 for stride.
 */
public class AC_BlockChain extends AC_BlockRope {
	public AC_BlockChain(int id, int tex) {
		super(id, tex);
	}

	public int getBlockTextureFromSideAndMetadata(int side, int metadata) {
		return this.blockIndexInTexture + (side % 2) + (metadata / 3) * 2;
	}
}
