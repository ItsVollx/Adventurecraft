package net.minecraft.src;

import java.awt.image.BufferedImage;

/**
 * A TextureFX that copies a static 16x16 tile from a source image
 * into a position in the terrain.png or items.png atlas.
 *
 * tileImage: 0 = /terrain.png, 1 = /gui/items.png
 */
public class AC_StaticTextureFX extends TextureFX {

	public AC_StaticTextureFX(int destIndex, int tileImageType, BufferedImage sourceImage, int srcIndex) {
		super(destIndex);
		this.tileImage = tileImageType;

		// Extract 16x16 tile from source image at srcIndex position
		int srcX = (srcIndex % 16) * 16;
		int srcY = (srcIndex / 16) * 16;

		int[] pixels = new int[256]; // 16x16
		sourceImage.getRGB(srcX, srcY, 16, 16, pixels, 0, 16);

		// Convert ARGB int pixels to RGBA byte array (what TextureFX expects)
		for (int i = 0; i < 256; i++) {
			int argb = pixels[i];
			this.imageData[i * 4 + 0] = (byte)((argb >> 16) & 0xFF); // R
			this.imageData[i * 4 + 1] = (byte)((argb >> 8) & 0xFF);  // G
			this.imageData[i * 4 + 2] = (byte)(argb & 0xFF);          // B
			this.imageData[i * 4 + 3] = (byte)((argb >> 24) & 0xFF); // A
		}
	}

	public void onTick() {
		// Static texture - no animation, imageData never changes
	}
}
