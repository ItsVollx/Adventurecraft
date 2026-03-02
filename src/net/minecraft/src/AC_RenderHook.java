package net.minecraft.src;

import org.lwjgl.opengl.GL11;

/**
 * Renders the hookshot projectile as a small hook sprite with a chain line
 * stretching back to the player.
 */
public class AC_RenderHook extends Render {

	public AC_RenderHook() {
		this.shadowSize = 0.0F;
	}

	public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTick) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x, (float)y, (float)z);

		// Billboard: face the camera
		GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);

		float scale = 0.5F;
		GL11.glScalef(scale, scale, scale);

		this.loadTexture("/gui/items.png");

		Tessellator tess = Tessellator.instance;

		// Use the hookshot item icon
		int iconIndex = AC_Items.itemHookshot.getIconFromDamage(0);
		float u0 = (float)(iconIndex % 16 * 16) / 256.0F;
		float u1 = (float)(iconIndex % 16 * 16 + 15.99F) / 256.0F;
		float v0 = (float)(iconIndex / 16 * 16) / 256.0F;
		float v1 = (float)(iconIndex / 16 * 16 + 15.99F) / 256.0F;

		GL11.glEnable(32826); // GL_RESCALE_NORMAL

		tess.startDrawingQuads();
		tess.setNormal(0.0F, 0.0F, 1.0F);
		tess.addVertexWithUV(-0.5, -0.5, 0.0, u0, v1);
		tess.addVertexWithUV( 0.5, -0.5, 0.0, u1, v1);
		tess.addVertexWithUV( 0.5,  0.5, 0.0, u1, v0);
		tess.addVertexWithUV(-0.5,  0.5, 0.0, u0, v0);
		tess.draw();

		GL11.glDisable(32826);
		GL11.glPopMatrix();

		// Draw chain line from hook to player
		if(entity instanceof AC_EntityHook) {
			AC_EntityHook hook = (AC_EntityHook)entity;
			EntityPlayer thrower = hook.getThrower();
			if(thrower != null) {
				renderChain(thrower, x, y, z, partialTick);
			}
		}
	}

	private void renderChain(EntityPlayer player, double hookX, double hookY, double hookZ, float partialTick) {
		// Interpolate player hand position
		double px = player.prevPosX + (player.posX - player.prevPosX) * partialTick;
		double py = player.prevPosY + (player.posY - player.prevPosY) * partialTick + 1.2;
		double pz = player.prevPosZ + (player.posZ - player.prevPosZ) * partialTick;

		// Offset to relative (hookX/Y/Z are already relative to render manager)
		double dx = px - RenderManager.renderPosX - hookX;
		double dy = py - RenderManager.renderPosY - hookY;
		double dz = pz - RenderManager.renderPosZ - hookZ;

		GL11.glDisable(3553); // GL_TEXTURE_2D
		GL11.glDisable(2896); // GL_LIGHTING
		GL11.glEnable(3042);  // GL_BLEND
		GL11.glBlendFunc(770, 771);

		Tessellator tess = Tessellator.instance;
		tess.startDrawing(3); // GL_LINE_STRIP
		tess.setColorRGBA(80, 80, 80, 255); // Dark gray chain color

		int segments = 16;
		for(int i = 0; i <= segments; i++) {
			float t = (float)i / (float)segments;
			// Slight droop (catenary approximation via parabola)
			float sag = t * (1.0F - t) * -0.5F;
			double lx = (float)hookX + dx * t;
			double ly = (float)hookY + dy * t + sag;
			double lz = (float)hookZ + dz * t;
			tess.addVertex(lx, ly, lz);
		}
		tess.draw();

		GL11.glDisable(3042);
		GL11.glEnable(2896);
		GL11.glEnable(3553);
	}
}
