package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class AC_RenderBoomerang extends Render {
	private int textureIndex;

	public AC_RenderBoomerang() {
		this.textureIndex = Item.stick.iconIndex;
	}

	public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTick) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x, (float)y, (float)z);

		// Spin animation
		float spin = (entity.ticksExisted + partialTick) * 30.0F;
		GL11.glRotatef(spin, 0.0F, 1.0F, 0.0F);

		this.loadTexture("/gui/items.png");

		Tessellator tess = Tessellator.instance;
		float u0 = (float)(this.textureIndex % 16) / 16.0F;
		float u1 = u0 + 0.0624375F;
		float v0 = (float)(this.textureIndex / 16) / 16.0F;
		float v1 = v0 + 0.0624375F;
		float size = 0.5F;

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		tess.startDrawingQuads();
		tess.setNormal(0.0F, 1.0F, 0.0F);
		tess.addVertexWithUV(-size, 0.0, -size, u0, v1);
		tess.addVertexWithUV(size, 0.0, -size, u1, v1);
		tess.addVertexWithUV(size, 0.0, size, u1, v0);
		tess.addVertexWithUV(-size, 0.0, size, u0, v0);
		tess.draw();

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}
}
