/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lw
 */
public class InventoryDebug
implements lw {
    private String inventoryTitle;
    private int size;
    private iz[] inventoryContents;
    public int firstItem;
    public int lastItem;
    public boolean atEnd;

    public InventoryDebug(String s, int i) {
        this.inventoryTitle = s;
        this.size = i;
        this.inventoryContents = new iz[i];
    }

    private int getID(int i) {
        int j;
        for (j = 0; j < 4; ++j) {
            if (i <= uu.v.bn) continue;
            --i;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= uu.F.bn) continue;
            --i;
        }
        if (i > uu.K.bn) {
            --i;
        }
        if (i > uu.K.bn) {
            --i;
        }
        for (j = 0; j < 2; ++j) {
            if (i <= uu.Y.bn) continue;
            --i;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= uu.ac.bn) continue;
            --i;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= uu.al.bn) continue;
            --i;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= AC_Blocks.pillarStone.bn) continue;
            --i;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= AC_Blocks.pillarMetal.bn) continue;
            --i;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= AC_Blocks.plant1.bn) continue;
            --i;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= AC_Blocks.trees.bn) continue;
            --i;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= AC_Blocks.glassBlocks.bn) continue;
            --i;
        }
        for (j = 0; j < 9; ++j) {
            if (i <= AC_Blocks.cageBlocks.bn) continue;
            --i;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= AC_Blocks.stoneBlocks1.bn) continue;
            --i;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= AC_Blocks.stoneBlocks2.bn) continue;
            --i;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= AC_Blocks.stoneBlocks3.bn) continue;
            --i;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= AC_Blocks.woodBlocks.bn) continue;
            --i;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= AC_Blocks.halfSteps1.bn) continue;
            --i;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= AC_Blocks.halfSteps2.bn) continue;
            --i;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= AC_Blocks.halfSteps3.bn) continue;
            --i;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= AC_Blocks.tableBlocks.bn) continue;
            --i;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= AC_Blocks.chairBlocks1.bn) continue;
            --i;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= AC_Blocks.chairBlocks2.bn) continue;
            --i;
        }
        for (j = 0; j < 14; ++j) {
            if (i <= AC_Blocks.ropes1.bn) continue;
            --i;
        }
        for (j = 0; j < 14; ++j) {
            if (i <= AC_Blocks.ropes2.bn) continue;
            --i;
        }
        for (j = 0; j < 8; ++j) {
            if (i <= AC_Blocks.chains.bn) continue;
            --i;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= AC_Blocks.ladders1.bn) continue;
            --i;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= AC_Blocks.ladders2.bn) continue;
            --i;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= AC_Blocks.ladders3.bn) continue;
            --i;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= AC_Blocks.ladders4.bn) continue;
            --i;
        }
        for (j = 0; j < 13; ++j) {
            if (i <= AC_Blocks.lights1.bn) continue;
            --i;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= AC_Blocks.plant2.bn) continue;
            --i;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= AC_Blocks.plant3.bn) continue;
            --i;
        }
        for (j = 0; j < 6; ++j) {
            if (i <= AC_Blocks.overlay1.bn) continue;
            --i;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= AC_Blocks.stairs1.bn) continue;
            --i;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= AC_Blocks.stairs2.bn) continue;
            --i;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= AC_Blocks.stairs3.bn) continue;
            --i;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= AC_Blocks.stairs4.bn) continue;
            --i;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= AC_Blocks.slopes1.bn) continue;
            --i;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= AC_Blocks.slopes2.bn) continue;
            --i;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= AC_Blocks.slopes3.bn) continue;
            --i;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= AC_Blocks.slopes4.bn) continue;
            --i;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= gm.aU.bf) continue;
            --i;
        }
        return i;
    }

    private int getSubtype(int i) {
        int j;
        int subtype = 0;
        for (j = 0; j < 4; ++j) {
            if (i <= uu.v.bn) continue;
            --i;
            ++subtype;
        }
        if (i > uu.v.bn) {
            subtype = 0;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= uu.F.bn) continue;
            --i;
            ++subtype;
        }
        if (i > uu.F.bn) {
            subtype = 0;
        }
        if (i > uu.K.bn) {
            --i;
            ++subtype;
        }
        if (i > uu.K.bn) {
            --i;
            ++subtype;
        }
        if (i > uu.K.bn) {
            subtype = 0;
        }
        for (j = 0; j < 2; ++j) {
            if (i <= uu.Y.bn) continue;
            --i;
            ++subtype;
        }
        if (i > uu.Y.bn) {
            subtype = 0;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= uu.ac.bn) continue;
            --i;
            ++subtype;
        }
        if (i > uu.ac.bn) {
            subtype = 0;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= uu.al.bn) continue;
            --i;
            ++subtype;
        }
        if (i > uu.al.bn) {
            subtype = 0;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= AC_Blocks.pillarStone.bn) continue;
            --i;
            ++subtype;
        }
        if (i > AC_Blocks.pillarStone.bn) {
            subtype = 0;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= AC_Blocks.pillarMetal.bn) continue;
            --i;
            ++subtype;
        }
        if (i > AC_Blocks.pillarMetal.bn) {
            subtype = 0;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= AC_Blocks.plant1.bn) continue;
            --i;
            ++subtype;
        }
        if (i > AC_Blocks.plant1.bn) {
            subtype = 0;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= AC_Blocks.trees.bn) continue;
            --i;
            ++subtype;
        }
        if (i > AC_Blocks.trees.bn) {
            subtype = 0;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= AC_Blocks.glassBlocks.bn) continue;
            --i;
            ++subtype;
        }
        if (i > AC_Blocks.glassBlocks.bn) {
            subtype = 0;
        }
        for (j = 0; j < 9; ++j) {
            if (i <= AC_Blocks.cageBlocks.bn) continue;
            --i;
            ++subtype;
        }
        if (i > AC_Blocks.cageBlocks.bn) {
            subtype = 0;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= AC_Blocks.stoneBlocks1.bn) continue;
            --i;
            ++subtype;
        }
        if (i > AC_Blocks.stoneBlocks1.bn) {
            subtype = 0;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= AC_Blocks.stoneBlocks2.bn) continue;
            --i;
            ++subtype;
        }
        if (i > AC_Blocks.stoneBlocks2.bn) {
            subtype = 0;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= AC_Blocks.stoneBlocks3.bn) continue;
            --i;
            ++subtype;
        }
        if (i > AC_Blocks.stoneBlocks3.bn) {
            subtype = 0;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= AC_Blocks.woodBlocks.bn) continue;
            --i;
            ++subtype;
        }
        if (i > AC_Blocks.woodBlocks.bn) {
            subtype = 0;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= AC_Blocks.halfSteps1.bn) continue;
            --i;
            ++subtype;
        }
        if (i > AC_Blocks.halfSteps1.bn) {
            subtype = 0;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= AC_Blocks.halfSteps2.bn) continue;
            --i;
            ++subtype;
        }
        if (i > AC_Blocks.halfSteps2.bn) {
            subtype = 0;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= AC_Blocks.halfSteps3.bn) continue;
            --i;
            ++subtype;
        }
        if (i > AC_Blocks.halfSteps3.bn) {
            subtype = 0;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= AC_Blocks.tableBlocks.bn) continue;
            --i;
            ++subtype;
        }
        if (i > AC_Blocks.tableBlocks.bn) {
            subtype = 0;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= AC_Blocks.chairBlocks1.bn) continue;
            --i;
            subtype += 4;
        }
        if (i > AC_Blocks.chairBlocks1.bn) {
            subtype = 0;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= AC_Blocks.chairBlocks2.bn) continue;
            --i;
            subtype += 4;
        }
        if (i > AC_Blocks.chairBlocks2.bn) {
            subtype = 0;
        }
        for (j = 0; j < 14; ++j) {
            if (i <= AC_Blocks.ropes1.bn) continue;
            --i;
            ++subtype;
        }
        if (i > AC_Blocks.ropes1.bn) {
            subtype = 0;
        }
        for (j = 0; j < 14; ++j) {
            if (i <= AC_Blocks.ropes2.bn) continue;
            --i;
            ++subtype;
        }
        if (i > AC_Blocks.ropes2.bn) {
            subtype = 0;
        }
        for (j = 0; j < 8; ++j) {
            if (i <= AC_Blocks.chains.bn) continue;
            --i;
            ++subtype;
        }
        if (i > AC_Blocks.chains.bn) {
            subtype = 0;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= AC_Blocks.ladders1.bn) continue;
            --i;
            subtype += 4;
        }
        if (i > AC_Blocks.ladders1.bn) {
            subtype = 0;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= AC_Blocks.ladders2.bn) continue;
            --i;
            subtype += 4;
        }
        if (i > AC_Blocks.ladders2.bn) {
            subtype = 0;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= AC_Blocks.ladders3.bn) continue;
            --i;
            subtype += 4;
        }
        if (i > AC_Blocks.ladders3.bn) {
            subtype = 0;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= AC_Blocks.ladders4.bn) continue;
            --i;
            subtype += 4;
        }
        if (i > AC_Blocks.ladders4.bn) {
            subtype = 0;
        }
        for (j = 0; j < 13; ++j) {
            if (i <= AC_Blocks.lights1.bn) continue;
            --i;
            ++subtype;
        }
        if (i > AC_Blocks.lights1.bn) {
            subtype = 0;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= AC_Blocks.plant2.bn) continue;
            --i;
            ++subtype;
        }
        if (i > AC_Blocks.plant2.bn) {
            subtype = 0;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= AC_Blocks.plant3.bn) continue;
            --i;
            ++subtype;
        }
        if (i > AC_Blocks.plant3.bn) {
            subtype = 0;
        }
        for (j = 0; j < 6; ++j) {
            if (i <= AC_Blocks.overlay1.bn) continue;
            --i;
            ++subtype;
        }
        if (i > AC_Blocks.overlay1.bn) {
            subtype = 0;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= AC_Blocks.stairs1.bn) continue;
            --i;
            subtype += 4;
        }
        if (i > AC_Blocks.stairs1.bn) {
            subtype = 0;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= AC_Blocks.stairs2.bn) continue;
            --i;
            subtype += 4;
        }
        if (i > AC_Blocks.stairs2.bn) {
            subtype = 0;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= AC_Blocks.stairs3.bn) continue;
            --i;
            subtype += 4;
        }
        if (i > AC_Blocks.stairs3.bn) {
            subtype = 0;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= AC_Blocks.stairs4.bn) continue;
            --i;
            subtype += 4;
        }
        if (i > AC_Blocks.stairs4.bn) {
            subtype = 0;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= AC_Blocks.slopes1.bn) continue;
            --i;
            subtype += 4;
        }
        if (i > AC_Blocks.slopes1.bn) {
            subtype = 0;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= AC_Blocks.slopes2.bn) continue;
            --i;
            subtype += 4;
        }
        if (i > AC_Blocks.slopes2.bn) {
            subtype = 0;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= AC_Blocks.slopes3.bn) continue;
            --i;
            subtype += 4;
        }
        if (i > AC_Blocks.slopes3.bn) {
            subtype = 0;
        }
        for (j = 0; j < 3; ++j) {
            if (i <= AC_Blocks.slopes4.bn) continue;
            --i;
            subtype += 4;
        }
        if (i > AC_Blocks.slopes4.bn) {
            subtype = 0;
        }
        for (j = 0; j < 15; ++j) {
            if (i <= gm.aU.bf) continue;
            --i;
            ++subtype;
        }
        if (i > gm.aU.bf) {
            subtype = 0;
        }
        return subtype;
    }

    public void fillInventory(int offset) {
        boolean filledFirst = false;
        this.atEnd = false;
        for (int i = 0; i < this.size; ++i) {
            int id = this.getID(i + offset);
            if (gm.c[id] != null) {
                this.inventoryContents[i] = new iz(gm.c[id], -64);
                this.inventoryContents[i].b(this.getSubtype(i + offset));
                this.lastItem = i + offset;
                if (filledFirst) continue;
                this.firstItem = i + offset;
                filledFirst = true;
                continue;
            }
            if (id < 31999) {
                --i;
                ++offset;
                continue;
            }
            this.atEnd = true;
            while (i < this.size) {
                this.inventoryContents[i] = null;
                ++i;
            }
            return;
        }
    }

    public void fillInventoryBackwards(int offset) {
        boolean filledFirst = false;
        this.atEnd = false;
        for (int i = 0; i < this.size; ++i) {
            int id = this.getID(offset - i);
            if (id > 0 && gm.c[id] != null) {
                this.inventoryContents[this.size - i - 1] = new iz(gm.c[id], -64);
                this.inventoryContents[this.size - i - 1].b(this.getSubtype(offset - i));
                this.firstItem = offset - i;
                if (filledFirst) continue;
                this.lastItem = offset - i;
                filledFirst = true;
                continue;
            }
            if (offset - i > 1) {
                --i;
                --offset;
                continue;
            }
            while (i < this.size) {
                this.inventoryContents[this.size - i - 1] = null;
                ++i;
            }
            return;
        }
    }

    public iz f_(int i) {
        return this.inventoryContents[i];
    }

    public iz a(int i, int j) {
        if (this.inventoryContents[i] != null) {
            return this.inventoryContents[i].k();
        }
        return null;
    }

    public void a(int i, iz itemstack) {
        if (this.inventoryContents[i] != null) {
            this.inventoryContents[i] = this.inventoryContents[i].k();
        }
    }

    public int a() {
        return this.size;
    }

    public String c() {
        return this.inventoryTitle;
    }

    public int d() {
        return 64;
    }

    public void y_() {
    }

    public boolean a_(gs entityplayer) {
        return true;
    }
}

