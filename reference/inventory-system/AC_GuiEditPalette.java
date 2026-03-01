/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ke
 *  net.minecraft.src.Block
 *  net.minecraft.src.GuiButton
 *  org.lwjgl.opengl.GL11
 *  u
 *  ub
 */
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Block;
import net.minecraft.src.GuiButton;
import org.lwjgl.opengl.GL11;

class AC_GuiEditPalette
extends ub {
    int numRows;
    List<GuiButton> controlList = new ArrayList<GuiButton>();
    ArrayList<Block> blocks;
    ke selectedButton;
    iz item = new iz(0, 0, 0);
    private static bb itemRenderer = new bb();
    static int rows = 8;
    static int columns = 4;
    static int scrollHeight = 8;
    float scrollPosition = 0.0f;

    AC_GuiEditPalette() {
        this.blocks = new ArrayList();
        this.filterBlocks(0);
    }

    void filterBlocks(int f) {
        this.blocks.clear();
        for (int i = 0; i < 255; ++i) {
            if (uu.m[i] == null) continue;
            this.blocks.add((Block)uu.m[i]);
        }
        this.numRows = (this.blocks.size() + columns - 1) / columns;
    }

    boolean mouseClicked(int x, int y, int buttonClicked, Minecraft mc, int width, int height) {
        if (buttonClicked == 0) {
            int pLeft = 0;
            int pTop = height / 2 - rows * 8;
            int columnClicked = (x - pLeft) / 16;
            int rowClicked = (y - pTop) / 16;
            if (rowClicked < rows && rowClicked >= 0) {
                if (columnClicked < columns && columnClicked >= 0) {
                    mc.B.a("random.click", 1.0f, 1.0f);
                    int i = columnClicked + rowClicked * columns;
                    if (i + this.getOffset() < this.blocks.size()) {
                        AC_DebugMode.mapEditing.setBlock(((uu)this.blocks.get((int)(i + this.getOffset()))).bn, 0);
                        return true;
                    }
                } else if (columnClicked == columns && x % 16 < 4 && this.needScrollbar()) {
                    int yClicked = y - pTop - scrollHeight / 2;
                    this.scrollPosition = Math.max(Math.min((float)yClicked / ((float)rows * 16.0f - (float)scrollHeight), 1.0f), 0.0f);
                    return true;
                }
            }
        }
        return false;
    }

    void drawPalette(Minecraft mc, sj fontRenderer, int width, int height) {
        int pLeft = 0;
        int pWidth = 16 * columns;
        int pTop = height / 2 - rows * 8;
        int pHeight = rows * 16;
        if (this.needScrollbar()) {
            pWidth += 4;
        }
        this.a(pLeft, pTop, pLeft + pWidth, pTop + pHeight, Integer.MIN_VALUE);
        if (this.needScrollbar()) {
            int yOffset = (int)(this.scrollPosition * (float)(pHeight - scrollHeight));
            this.a(pLeft + pWidth - 4, pTop + yOffset, pLeft + pWidth, pTop + yOffset + scrollHeight, -2130706433);
        }
        GL11.glPushMatrix();
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        u.b();
        GL11.glPopMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)32826);
        int offset = this.getOffset();
        for (int i = 0; i < rows * columns && i + offset < this.blocks.size(); ++i) {
            this.item.c = ((uu)this.blocks.get((int)(i + offset))).bn;
            itemRenderer.a(fontRenderer, mc.p, this.item, i % columns * 16, height / 2 - rows * 8 + 16 * (i / columns));
        }
        GL11.glDisable((int)32826);
        u.a();
    }

    private int getOffset() {
        return columns * (int)((double)(this.scrollPosition * (float)(this.numRows - rows)) + 0.5);
    }

    private boolean needScrollbar() {
        return this.numRows > rows;
    }
}

