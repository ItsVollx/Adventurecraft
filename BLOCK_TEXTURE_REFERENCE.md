# AdventureCraft Block Texture Reference

**Source:** Decompiled from original `ACBin.jar` via `javap -c -p -cp lib/ACBin.jar AC_Blocks`

All texture tiles are from `terrain2.png` (16x16 atlas) unless marked as `terrain3.png`.
The `setTextureNum` value indicates which atlas the block's texture source is from (2=terrain2, 3=terrain3).

## Original ACBin Block ID & Texture Mappings

| Block ID | Class | Name (setBlockName) | Texture Tile | Atlas | Notes |
|----------|-------|---------------------|-------------|-------|-------|
| 150 | AC_BlockLockedDoor | lockedDoor | 208 | terrain3 | Key=doorKey |
| 151 | AC_BlockMobSpawner | mobSpawner2 | 65 | vanilla | Vanilla spawner tex |
| 152 | AC_BlockSpawn | spawn | 0 | terrain2 | setTextureNum(2) |
| 153 | AC_BlockTrigger | trigger | 1 | terrain2 | setTextureNum(2) |
| 154 | AC_BlockTriggeredDoor | triggeredDoor | 208 | terrain3 | setTextureNum(3), hardcoded in constructor |
| 155 | AC_BlockSpike | spike | (none) | terrain3 | setTextureNum(3) |
| 156 | AC_BlockLockedDoor | lockedBossDoor | 210 | terrain3 | setTextureNum(3), Key=bossKey |
| 157 | AC_BlockTriggerInverter | triggerInverter | 2 | terrain2 | setTextureNum(2) |
| 158 | AC_BlockTriggerMemory | triggerMemory | 3 | terrain2 | setTextureNum(2) |
| 159 | AC_BlockClip | clip | 4 | terrain2 | setTextureNum(2), Material.rock |
| 160 | AC_BlockRedstoneTrigger | redstoneTrigger | 228 | terrain3 | setTextureNum(3) |
| 161 | AC_BlockRedstonePower | redstonePower | 185 | terrain3 | (no explicit setTextureNum) |
| 162 | AC_BlockBombable | crackedStonebrick | 166 | terrain3 | (bombableCobblestone field) |
| 163 | AC_BlockBombable | crackedStone | 167 | terrain3 | (bombableStone field) |
| 164 | AC_BlockWeather | weather | 5 | terrain2 | setTextureNum(2) |
| 165 | AC_BlockMusic | music | 9 | terrain2 | setTextureNum(2) |
| 166 | AC_BlockPushable | pushable | 212 | terrain3 | setTextureNum(3) |
| 167 | AC_BlockTimer | timer | 8 | terrain2 | setTextureNum(2) |
| 168 | AC_BlockMessage | message | 7 | terrain2 | setTextureNum(2) |
| 169 | AC_BlockFan | fan | 184 | terrain3 | Fan ON variant (boolean=true) |
| 170 | AC_BlockCamera | camera | 6 | terrain2 | setTextureNum(2) |
| 171 | AC_BlockLightBulb | lightBulb | 14 | terrain2 | setTextureNum(2) |
| 172 | AC_BlockFan | fan | 200 | terrain3 | Fan OFF variant (boolean=false) |
| 173 | AC_BlockScript | script | 15 | terrain2 | setTextureNum(2) |
| 174 | AC_BlockStore | store | 49 | terrain2 | (Material.wood) |
| 175 | AC_BlockEffect | effect | 244 | terrain3 | setTextureNum(3) |
| 176 | AC_BlockUrl | url | 245 | terrain3 | setTextureNum(3) |
| 177 | AC_BlockNpcPath | NPC Path Block | 247 | terrain3 | setTextureNum(3) |
| 200 | AC_BlockDarkness | darkness | 10 | terrain2 | setTextureNum(2) |
| 201 | AC_BlockTriggerPushable | triggerPushable | 213 | terrain3 | setTextureNum(3) |
| 202 | AC_BlockStorage | storage | 11 | terrain2 | setTextureNum(2) |
| 203 | AC_BlockHealDamage | healDamage | 12 | terrain2 | setTextureNum(2) |
| 204 | AC_BlockTeleport | teleport | 13 | terrain2 | setTextureNum(2) |
| 205 | AC_BlockPillar | pillarStone | 32 | terrain2 | setTextureNum(2), 16 subtypes |
| 206 | AC_BlockPillar | pillarMetal | 80 | terrain2 | setTextureNum(2), 16 subtypes |
| 207 | AC_BlockPlant | flower | 112 | terrain2 | setTextureNum(2), 16 subtypes |
| 208 | AC_BlockTree | sapling | 128 | terrain2 | setTextureNum(2), 16 subtypes |
| 209 | AC_BlockTransparent | (transparent1) | 144 | terrain2 | setTextureNum(2), 16 subtypes |
| 210 | AC_BlockTransparent | (transparent2) | 160 | terrain2 | setTextureNum(2), 16 subtypes |
| 211 | AC_BlockSolid | (solid1) | 176 | terrain2 | setTextureNum(2), 16 subtypes |
| 212 | AC_BlockSolid | (solid2) | 192 | terrain2 | setTextureNum(2), 16 subtypes |
| 213 | AC_BlockOverlay | (overlay) | 0 | terrain3 | setTextureNum(3), 16 subtypes |
| 214 | AC_BlockColor | (color) | 16 | terrain3 | setTextureNum(3), 16 subtypes |
| 215 | AC_BlockHalfStep | halfStep | 0 | terrain2 | setTextureNum(3), 16 subtypes |
| 216 | AC_BlockHalfStep | halfStepWood | 16 | terrain2 | setTextureNum(3), 16 subtypes |
| 217 | AC_BlockHalfStep | halfStepWood | 32 | terrain2 | setTextureNum(3), 16 subtypes |
| 218 | AC_BlockTable | table | 32 | terrain3 | setTextureNum(3), 16 subtypes |
| 219 | AC_BlockChair | chair | 64 | terrain3 | setTextureNum(3), 16 subtypes |
| 220 | AC_BlockChair | chair | 68 | terrain3 | setTextureNum(3), 16 subtypes |
| 221 | AC_BlockChair | chair | 64 | terrain3 | setTextureNum(3), 16 subtypes |
| 222 | AC_BlockChair | chair | 68 | terrain3 | setTextureNum(3), 16 subtypes |

## Our Remapped Blocks (IDs 150-180)

We remapped the original scattered IDs to contiguous range 150-180.
Each uses `TEX_BASE + offset` where `TEX_BASE = 208` (row 13 in terrain.png).
AC_StaticTextureFX copies source tiles into terrain.png atlas at runtime.

### Current Texture Source Mappings (AC_Mod.java)

| Offset | Target Atlas Idx | Block | Source | Source Tile |
|--------|-----------------|-------|--------|-------------|
| 0 | 208 | Message | terrain2 | 7 |
| 1 | 209 | Script | terrain2 | 15 |
| 2 | 210 | Camera | terrain2 | 6 |
| 3 | 211 | Triggered Door | terrain3 | 208 |
| 4 | 212 | Spawn | terrain2 | 0 |
| 5 | 213 | Teleport | terrain2 | 13 |
| 6 | 214 | Storage | terrain2 | 11 |
| 7 | 215 | Timer | terrain2 | 8 |
| 8 | 216 | Music | terrain2 | 9 |
| 9 | 217 | Weather | terrain2 | 5 |
| 10 | 218 | Effect | terrain3 | 244 |
| 11 | 219 | Trigger | terrain2 | 1 |
| 12 | 220 | Darkness | terrain2 | 10 |
| 13 | 221 | Lightbulb | terrain2 | 14 |
| 14 | 222 | HealDamage | terrain2 | 12 |
| 15 | 223 | Trigger Inverter | terrain2 | 2 |
| 16 | 224 | Trigger Memory | terrain2 | 3 |
| 17 | 225 | Redstone Power | terrain2 | 25 |
| 18 | 226 | Redstone Trigger | terrain2 | 68 |
| 19 | 227 | Clip | terrain2 | 4 |
| 20 | 228 | Trigger Pushable | terrain2 | 72 |
| 21 | 229 | NPC Path | terrain3 | 247 |
| 22 | 230 | Spikes | terrain2 | 93 |
| 23 | 231 | Pushable | terrain2 | 84 |
| 24 | 232 | Store | terrain2 | 65 |
| 25 | 233 | Cracked Stone | terrain2 | 69 |
| 26 | 234 | Cracked Cobble | terrain2 | 85 |
| 27 | 235 | Boss Door | terrain2 | 78 |
| 28 | 236 | Locked Door | terrain2 | 94 |
| — | — | Fan | vanilla | 74 (noteblock) |
| — | — | Mob Spawner | vanilla | 65 (spawner) |

## Blocks NOT Yet Reimplemented (exist in original but not in our code)

These blocks exist in the original ACBin at higher IDs. They use terrain2/terrain3 tiles
with `setTextureNum()` for multi-texture support. Many have 16 subtypes.

| Block ID | Class | Texture Tile | Atlas | Notes |
|----------|-------|-------------|-------|-------|
| 162 | AC_BlockBombable | 166 | terrain3 | crackedStonebrick |
| 163 | AC_BlockBombable | 167 | terrain3 | crackedStone |
| 205 | AC_BlockPillar | 32 | terrain2 | pillarStone, 16 subtypes |
| 206 | AC_BlockPillar | 80 | terrain2 | pillarMetal, 16 subtypes |
| 207 | AC_BlockPlant | 112 | terrain2 | flower, 16 subtypes |
| 208 | AC_BlockTree | 128 | terrain2 | sapling, 16 subtypes |
| 209 | AC_BlockTransparent | 144 | terrain2 | 16 subtypes |
| 210 | AC_BlockTransparent | 160 | terrain2 | 16 subtypes |
| 211 | AC_BlockSolid | 176 | terrain2 | 16 subtypes |
| 212 | AC_BlockSolid | 192 | terrain2 | 16 subtypes |
| 213 | AC_BlockOverlay | 0 | terrain3 | 16 subtypes |
| 214 | AC_BlockColor | 16 | terrain3 | 16 subtypes |
| 215-217 | AC_BlockHalfStep | various | terrain2/3 | 16 subtypes each |
| 218 | AC_BlockTable | 32 | terrain3 | 16 subtypes |
| 219-222 | AC_BlockChair | 64/68 | terrain3 | 16 subtypes each |

## Key Differences: Original IDs vs Our Remapped IDs

**IMPORTANT:** The original ACBin uses different block IDs than our code!

| Block | Original ID | Our ID | Original Tex Tile | Our Tex Source |
|-------|------------|--------|-------------------|----------------|
| LockedDoor | 150 | 178 | terrain3:208 | terrain2:94 |
| MobSpawner | 151 | 180 | vanilla:65 | vanilla:65 |
| Spawn | 152 | 154 | terrain2:0 | terrain2:0 |
| Trigger | 153 | 161 | terrain2:1 | terrain2:1 |
| TriggeredDoor | 154 | 153 | terrain3:208 | terrain3:208 |
| Spike | 155 | 172 | terrain3 | terrain2:93 |
| LockedBossDoor | 156 | 177 | terrain3:210 | terrain2:78 |
| TriggerInverter | 157 | 165 | terrain2:2 | terrain2:2 |
| TriggerMemory | 158 | 166 | terrain2:3 | terrain2:3 |
| Clip | 159 | 169 | terrain2:4 | terrain2:4 |
| RedstoneTrigger | 160 | 168 | terrain3:228 | terrain2:68 |
| RedstonePower | 161 | 167 | terrain3:185 | terrain2:25 |
| CrackedStonebrick | 162 | 176 | terrain3:166 | terrain2:85 |
| CrackedStone | 163 | 175 | terrain3:167 | terrain2:69 |
| Weather | 164 | 159 | terrain2:5 | terrain2:5 |
| Music | 165 | 158 | terrain2:9 | terrain2:9 |
| Pushable | 166 | 173 | terrain3:212 | terrain2:84 |
| Timer | 167 | 157 | terrain2:8 | terrain2:8 |
| Message | 168 | 150 | terrain2:7 | terrain2:7 |
| Fan (ON) | 169 | 179 | terrain3:184 | vanilla:74 |
| Camera | 170 | 152 | terrain2:6 | terrain2:6 |
| LightBulb | 171 | 163 | terrain2:14 | terrain2:14 |
| Fan (OFF) | 172 | — | terrain3:200 | — |
| Script | 173 | 151 | terrain2:15 | terrain2:15 |
| Store | 174 | 174 | terrain2:49 | terrain2:65 |
| Effect | 175 | 160 | terrain3:244 | terrain3:244 |
| Url | 176 | — | terrain3:245 | — |
| NpcPath | 177 | 171 | terrain3:247 | terrain3:247 |
| Darkness | 200 | 162 | terrain2:10 | terrain2:10 |
| TriggerPushable | 201 | 170 | terrain3:213 | terrain2:72 |
| Storage | 202 | 156 | terrain2:11 | terrain2:11 |
| HealDamage | 203 | 164 | terrain2:12 | terrain2:12 |
| Teleport | 204 | 155 | terrain2:13 | terrain2:13 |

## Known Texture Mismatches (need terrain3 but using terrain2)

These blocks originally loaded from **terrain3.png** but we're loading from terrain2.
Some terrain2 tiles may coincidentally look similar, others won't match at all.

| Block | Should be | Currently |
|-------|-----------|-----------|
| LockedDoor | terrain3:208 | terrain2:94 |
| Spike | terrain3 (no tile passed) | terrain2:93 |
| LockedBossDoor | terrain3:210 | terrain2:78 |
| RedstoneTrigger | terrain3:228 | terrain2:68 |
| RedstonePower | terrain3:185 | terrain2:25 |
| CrackedStonebrick | terrain3:166 | terrain2:85 |
| CrackedStone | terrain3:167 | terrain2:69 |
| Pushable | terrain3:212 | terrain2:84 |
| Fan (ON) | terrain3:184 | vanilla:74 |
| TriggerPushable | terrain3:213 | terrain2:72 |
| Store | terrain2:49 | terrain2:65 |

## Item Texture Reference (gui/items.png)

Pre-baked in the original items.png atlas. No runtime injection needed.

| Item | ID | Shifted | Icon Index | Row,Col |
|------|-----|---------|-----------|---------|
| Bomb | 400 | 656 | 150 | 9,6 |
| Boomerang | 401 | 657 | 144 | 9,0 |
| Brush | 402 | 658 | 225 | 14,1 |
| Cursor | 403 | 659 | 224 | 14,0 |
| Eraser | 404 | 660 | 226 | 14,2 |
| Hammer | 405 | 661 | 228 | 14,4 |
| Hookshot | 406 | 662 | 163 | 10,3 |
| Lantern | 407 | 663 | 180 | 11,4 |
| NpcStick | 408 | 664 | 53 | 3,5 |
| PaintBucket | 409 | 665 | 227 | 14,3 |
| Paste | 410 | 666 | 231 | 14,7 |
| Pistol | 411 | 667 | 192 | 12,0 |
| PowerGlove | 412 | 668 | 177 | 11,1 |
| Quill | 413 | 669 | 229 | 14,5 |
| Rifle | 414 | 670 | 193 | 12,1 |
| Shotgun | 415 | 671 | 194 | 12,2 |
| TriggerStick | 416 | 672 | 53 | 3,5 |
| Umbrella | 417 | 673 | 179 | 11,3 |
| Wrench | 418 | 674 | 230 | 14,6 |
