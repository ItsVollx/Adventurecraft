/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  hp
 *  ke
 *  lw
 *  org.lwjgl.opengl.GL11
 */
import org.lwjgl.opengl.GL11;

public class AC_GuiPalette
extends hp {
    private InventoryDebug palette;
    GuiSlider2 extraWidth;
    GuiSlider2 extraDepth;
    private ke a;

    public AC_GuiPalette(lw iinventory, InventoryDebug p) {
        super(iinventory, (lw)p);
        this.palette = p;
    }

    public void b() {
        super.b();
        this.extraDepth = new GuiSlider2(50, this.c / 2 + 2, 3, 10, String.format("Extra Depth: %d", this.b.c.destroyExtraDepth), (float)this.b.c.destroyExtraDepth / 16.0f);
        this.extraWidth = new GuiSlider2(50, this.c / 2 - 2 - this.extraDepth.a, 3, 10, String.format("Extra Width: %d", this.b.c.destroyExtraWidth), (float)this.b.c.destroyExtraWidth / 5.0f);
        this.e.add(this.extraDepth);
        this.e.add(this.extraWidth);
    }

    protected void a(char c2, int i) {
        super.a(c2, i);
        if (i == 65 && this.palette.firstItem > 1) {
            this.palette.fillInventoryBackwards(this.palette.firstItem - 1);
        } else if (i == 66 && !this.palette.atEnd) {
            this.palette.fillInventory(this.palette.lastItem + 1);
        }
    }

    public void a(int i, int j, float f) {
        super.a(i, j, f);
        GL11.glPushMatrix();
        GL11.glDisable((int)2896);
        GL11.glDisable((int)2929);
        for (int k = 0; k < this.e.size(); ++k) {
            ke guibutton = (ke)this.e.get(k);
            guibutton.a(this.b, i, j);
        }
        GL11.glEnable((int)2896);
        GL11.glEnable((int)2929);
        GL11.glPopMatrix();
        this.b.c.destroyExtraDepth = (int)Math.min(16.0f * this.extraDepth.sliderValue, 15.0f);
        this.b.c.destroyExtraWidth = (int)Math.min(5.0f * this.extraWidth.sliderValue, 4.0f);
        this.extraWidth.e = String.format("Extra Width: %d", this.b.c.destroyExtraWidth);
        this.extraDepth.e = String.format("Extra Depth: %d", this.b.c.destroyExtraDepth);
    }

    protected void a(int i, int j, int k) {
        if (k == 0) {
            for (int l = 0; l < this.e.size(); ++l) {
                ke guibutton = (ke)this.e.get(l);
                if (!guibutton.c(this.b, i, j)) continue;
                this.a = guibutton;
                this.b.B.a("random.click", 1.0f, 1.0f);
                this.a(guibutton);
            }
        }
        super.a(i, j, k);
    }

    protected void b(int i, int j, int k) {
        if (this.a != null && k == 0) {
            this.a.a(i, j);
            this.a = null;
        }
        super.b(i, j, k);
    }
}

