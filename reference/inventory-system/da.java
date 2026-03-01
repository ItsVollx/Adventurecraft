/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  du
 *  ke
 *  nw
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 *  ub
 */
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class da
extends ub {
    protected Minecraft b;
    public int c;
    public int d;
    protected List e = new ArrayList();
    public boolean f = false;
    protected sj g;
    public du h;
    protected ke a = null;
    public boolean disableInputGrabbing = false;

    public void a(int i, int j, float f) {
        for (int k = 0; k < this.e.size(); ++k) {
            ke guibutton = (ke)this.e.get(k);
            guibutton.a(this.b, i, j);
        }
    }

    protected void a(char c2, int i) {
        if (i == 1) {
            this.b.a((da)null);
            this.b.g();
        }
    }

    public static String d() {
        try {
            Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
            if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                String s = (String)transferable.getTransferData(DataFlavor.stringFlavor);
                return s;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return null;
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
    }

    protected void b(int i, int j, int k) {
        if (this.a != null && k == 0) {
            this.a.a(i, j);
            this.a = null;
        }
    }

    protected void a(ke guibutton) {
    }

    public void a(Minecraft minecraft, int i, int j) {
        this.h = new du(minecraft);
        this.b = minecraft;
        this.g = minecraft.q;
        this.c = i;
        this.d = j;
        this.e.clear();
        this.b();
    }

    public void b() {
    }

    public void e() {
        while (Mouse.next()) {
            this.f();
        }
        while (Keyboard.next()) {
            this.g();
        }
    }

    public void f() {
        if (Mouse.getEventButtonState()) {
            int i = Mouse.getEventX() * this.c / this.b.d;
            int k = this.d - Mouse.getEventY() * this.d / this.b.e - 1;
            this.a(i, k, Mouse.getEventButton());
        } else {
            int j = Mouse.getEventX() * this.c / this.b.d;
            int l = this.d - Mouse.getEventY() * this.d / this.b.e - 1;
            this.b(j, l, Mouse.getEventButton());
        }
    }

    public void g() {
        if (Keyboard.getEventKeyState()) {
            if (Keyboard.getEventKey() == 87) {
                this.b.j();
                return;
            }
            this.a(Keyboard.getEventCharacter(), Keyboard.getEventKey());
        }
    }

    public void a() {
    }

    public void h() {
    }

    public void i() {
        this.a(0);
    }

    public void a(int i) {
        if (this.b.f != null) {
            this.a(0, 0, this.c, this.d, -1072689136, -804253680);
        } else {
            this.b(i);
        }
    }

    public void b(int i) {
        GL11.glDisable((int)2896);
        GL11.glDisable((int)2912);
        nw tessellator = nw.a;
        GL11.glBindTexture((int)3553, (int)this.b.p.b("/gui/background.png"));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        float f = 32.0f;
        tessellator.b();
        tessellator.b(0x404040);
        tessellator.a(0.0, (double)this.d, 0.0, 0.0, (double)((float)this.d / f + (float)i));
        tessellator.a((double)this.c, (double)this.d, 0.0, (double)((float)this.c / f), (double)((float)this.d / f + (float)i));
        tessellator.a((double)this.c, 0.0, 0.0, (double)((float)this.c / f), (double)(0 + i));
        tessellator.a(0.0, 0.0, 0.0, 0.0, (double)(0 + i));
        tessellator.a();
    }

    public boolean c() {
        return true;
    }

    public void a(boolean flag, int i) {
    }

    public void j() {
    }
}

