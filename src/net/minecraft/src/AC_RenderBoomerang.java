package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class AC_RenderBoomerang extends Render {

	public AC_RenderBoomerang() {
		this.shadowSize = 0.15F;
		this.field_194_c = 0.75F;
	}

	public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTick) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x, (float)y, (float)z);

		// Rotation: spin around Y based on entity age
		float spin = (entity.ticksExisted + partialTick) * 30.0F;
		GL11.glRotatef(-yaw, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(spin, 0.0F, 1.0F, 0.0F);

		this.loadTexture("/gui/items.png");

		Tessellator tess = Tessellator.instance;

		// Get the boomerang icon index
		int iconIndex = AC_Items.itemBoomerang.getIconFromDamage(0);
		float u0 = (float)(iconIndex % 16 * 16 + 0) / 256.0F;
		float u1 = (float)(iconIndex % 16 * 16 + 15.99F) / 256.0F;
		float v0 = (float)(iconIndex / 16 * 16 + 0) / 256.0F;
		float v1 = (float)(iconIndex / 16 * 16 + 15.99F) / 256.0F;
		float size = 1.0F;
		float thickness = 0.0625F;

		GL11.glEnable(32826); // GL_RESCALE_NORMAL

		GL11.glTranslatef(-0.5F, 0.0F, -0.5F);

		// Front face
		tess.startDrawingQuads();
		tess.setNormal(0.0F, 0.0F, 1.0F);
		tess.addVertexWithUV(0.0, 0.0 - thickness, 0.0, u1, v1);
		tess.addVertexWithUV(size, 0.0 - thickness, 0.0, u0, v1);
		tess.addVertexWithUV(size, 0.0 - thickness, 1.0, u0, v0);
		tess.addVertexWithUV(0.0, 0.0 - thickness, 1.0, u1, v0);
		tess.draw();

		// Back face
		tess.startDrawingQuads();
		tess.setNormal(0.0F, 0.0F, -1.0F);
		tess.addVertexWithUV(0.0, 0.0, 1.0, u1, v0);
		tess.addVertexWithUV(size, 0.0, 1.0, u0, v0);
		tess.addVertexWithUV(size, 0.0, 0.0, u0, v1);
		tess.addVertexWithUV(0.0, 0.0, 0.0, u1, v1);
		tess.draw();

		// Side edges (thickness) - left/right
		int tilesX = 16;
		tess.startDrawingQuads();
		tess.setNormal(-1.0F, 0.0F, 0.0F);
		for(int i = 0; i < tilesX; i++) {
			float frac = (float)i / (float)tilesX;
			float edgeU = u1 + (u0 - u1) * frac - (0.5F / 256.0F);
			float xPos = size * frac;
			tess.addVertexWithUV(xPos, 0.0 - thickness, 1.0, edgeU, v0);
			tess.addVertexWithUV(xPos, 0.0, 1.0, edgeU, v0);
			tess.addVertexWithUV(xPos, 0.0, 0.0, edgeU, v1);
			tess.addVertexWithUV(xPos, 0.0 - thickness, 0.0, edgeU, v1);
		}
		tess.draw();

		tess.startDrawingQuads();
		tess.setNormal(1.0F, 0.0F, 0.0F);
		for(int i = 0; i < tilesX; i++) {
			float frac = (float)i / (float)tilesX;
			float edgeU = u1 + (u0 - u1) * frac - (0.5F / 256.0F);
			float xPos = size * frac + 1.0F / (float)tilesX;
			tess.addVertexWithUV(xPos, 0.0 - thickness, 0.0, edgeU, v1);
			tess.addVertexWithUV(xPos, 0.0, 0.0, edgeU, v1);
			tess.addVertexWithUV(xPos, 0.0, 1.0, edgeU, v0);
			tess.addVertexWithUV(xPos, 0.0 - thickness, 1.0, edgeU, v0);
		}
		tess.draw();

		// Top/bottom edges
		int tilesY = 16;
		tess.startDrawingQuads();
		tess.setNormal(0.0F, 1.0F, 0.0F);
		for(int i = 0; i < tilesY; i++) {
			float frac = (float)i / (float)tilesY;
			float edgeV = v1 + (v0 - v1) * frac - (0.5F / 256.0F);
			float zPos = size * frac + 1.0F / (float)tilesY;
			tess.addVertexWithUV(0.0, 0.0 - thickness, zPos, u1, edgeV);
			tess.addVertexWithUV(size, 0.0 - thickness, zPos, u0, edgeV);
			tess.addVertexWithUV(size, 0.0, zPos, u0, edgeV);
			tess.addVertexWithUV(0.0, 0.0, zPos, u1, edgeV);
		}
		tess.draw();

		tess.startDrawingQuads();
		tess.setNormal(0.0F, -1.0F, 0.0F);
		for(int i = 0; i < tilesY; i++) {
			float frac = (float)i / (float)tilesY;
			float edgeV = v1 + (v0 - v1) * frac - (0.5F / 256.0F);
			float zPos = size * frac;
			tess.addVertexWithUV(size, 0.0 - thickness, zPos, u0, edgeV);
			tess.addVertexWithUV(0.0, 0.0 - thickness, zPos, u1, edgeV);
			tess.addVertexWithUV(0.0, 0.0, zPos, u1, edgeV);
			tess.addVertexWithUV(size, 0.0, zPos, u0, edgeV);
		}
		tess.draw();

		GL11.glDisable(32826);
		GL11.glPopMatrix();

		// Render carried items orbiting the boomerang
		if(entity instanceof AC_EntityBoomerang) {
			AC_EntityBoomerang boomerang = (AC_EntityBoomerang)entity;
			if(!boomerang.carriedItems.isEmpty()) {
				this.renderCarriedItems(boomerang, x, y, z, partialTick);
			}
		}
	}

	private void renderCarriedItems(AC_EntityBoomerang boomerang, double x, double y, double z, float partialTick) {
		int count = boomerang.carriedItems.size();
		float time = (boomerang.ticksExisted + partialTick) * 10.0F;

		for(int i = 0; i < count; i++) {
			ItemStack stack = (ItemStack)boomerang.carriedItems.get(i);
			if(stack == null) continue;

			// Orbit position: items circle around the boomerang
			float angle = time + (float)i * (360.0F / (float)count);
			float rad = angle * 0.017453292F; // degrees to radians
			float orbitRadius = 0.5F;
			float ox = MathHelper.cos(rad) * orbitRadius;
			float oz = MathHelper.sin(rad) * orbitRadius;
			float oy = MathHelper.sin(rad * 2.0F) * 0.15F; // slight bobbing

			GL11.glPushMatrix();
			GL11.glTranslatef((float)x + ox, (float)y + oy + 0.1F, (float)z + oz);

			// Billboard - face the player
			GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);

			float scale = 0.35F;
			GL11.glScalef(scale, scale, scale);

			// Get icon
			int icon = stack.getIconIndex();
			String texture;
			if(stack.itemID < 256) {
				texture = "/terrain.png";
			} else {
				texture = "/gui/items.png";
			}
			this.loadTexture(texture);

			float cu0 = (float)(icon % 16 * 16) / 256.0F;
			float cu1 = (float)(icon % 16 * 16 + 16) / 256.0F;
			float cv0 = (float)(icon / 16 * 16) / 256.0F;
			float cv1 = (float)(icon / 16 * 16 + 16) / 256.0F;

			Tessellator tess = Tessellator.instance;
			tess.startDrawingQuads();
			tess.setNormal(0.0F, 1.0F, 0.0F);
			tess.addVertexWithUV(-0.5, -0.25, 0.0, cu0, cv1);
			tess.addVertexWithUV( 0.5, -0.25, 0.0, cu1, cv1);
			tess.addVertexWithUV( 0.5,  0.75, 0.0, cu1, cv0);
			tess.addVertexWithUV(-0.5,  0.75, 0.0, cu0, cv0);
			tess.draw();

			GL11.glPopMatrix();
		}
	}
}
