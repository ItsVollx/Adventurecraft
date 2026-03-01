# AdventureCraft Mod - Fresh Setup

## Current Status
- **Phase:** 9 Complete
- **Classes:** 62 AC_ mod files + 677 vanilla Minecraft B1.7.3 classes
- **JAR:** `adventurecraft-mod.jar` (1.04 MB - fully functional)
- **Working:** Yes ✓

## Workspace Structure

```
D:\Desktop\AdventureCraft-Dev\
├── src/                                 # Vanilla B1.7.3 deobfuscated source (739 files)
│   └── net/minecraft/src/*.java         # Can view/reference, marked as generated
├── src-ac/                              # AdventureCraft mod source (62 files)
│   └── net/minecraft/src/AC_*.java     # Editable mod files
├── lib/                                 # Required libraries
│   ├── minecraft.jar                    # Vanilla B1.7.3 (obfuscated)
│   ├── lwjgl-2.9.4-nightly-20150209.jar # Graphics (LWJGL)
│   ├── lwjgl_util-2.9.4-nightly-20150209.jar
│   ├── SoundSystem.jar                  # Audio
│   └── rhino-1.7.14.jar                 # JavaScript engine
├── natives/                             # Native libraries (.dll files)
│   ├── lwjgl.dll
│   ├── lwjgl64.dll
│   └── OpenAL64.dll
├── bin/                                 # Compiled .class files (created by build)
├── adventurecraft-mod.jar               # Compiled mod JAR
├── build.ps1                            # Build script
├── run.ps1                              # Launch script
└── README.md                            # Quick start
```

## Quick Start

### Play the game (right now):
```powershell
cd D:\Desktop\AdventureCraft-Dev
.\run.ps1
```

### Understand the code:
- **`src/`** - Full deobfuscated Minecraft B1.7.3 source (739 classes like World, Block, Entity)
  - Reference only - DO NOT EDIT these
  - Shows all vanilla game logic
  - Open in IDE for code completion and navigation
- **`src-ac/`** - AdventureCraft mod classes (62 AC_* files)
  - This is what you EDIT for mod features
  - Examples: AC_Blocks.java, AC_Items.java, AC_EntityNPC.java

### Modify and rebuild:
1. Edit any file in `src-ac/net/minecraft/src/AC_*.java`
2. Run: `.\build.ps1`
3. Run: `.\run.ps1`

## Important Notes

### The Build Challenge
- **AC_ source** uses deobfuscated class names (World, Block, Entity)
- **lib/minecraft.jar** contains obfuscated code (a.class, aa.class, etc.)
- These are incompatible for compilation from scratch
- **Solution:** Use the pre-compiled `adventurecraft-mod.jar` that already has everything working

### Modifying the Mod
To edit mod behavior:
1. Edit `.java` files in `src-ac/`
2. The `build.ps1` script will:
   - Extract vanilla classes from lib/minecraft.jar
   - Compile your AC_ changes against those classes
   - Package everything into a new JAR
3. Test with `run.ps1`

### Performance
- Initial build: ~10-15 seconds (extracts + compiles)
- Subsequent builds: ~5-10 seconds

## Requirements
- Java 8 (JDK 8.0.472+)
- 1GB+ RAM available
- Display resolution: 1024×768 minimum

## Troubleshooting

**Game won't start:**
1. Check Java is installed: `java -version`
2. Verify all `.dll` files in `natives/` exist
3. Check that `adventurecraft-mod.jar` exists

**Compilation errors during build:**
1. Make sure you're editing files from `src-ac/`, not elsewhere
2. Verify all `.jar` files exist in `lib/`
3. Run `build.ps1` again - sometimes it needs a second pass

**"ClassNotFoundException" when running:**
1. Delete `bin/` folder: `Remove-Item bin -Recurse -Force`
2. Run `build.ps1` again
3. Run `run.ps1`

## What's Already Done
✅ B1.7.3 source deobfuscation
✅ 62 AC_ mod classes created
✅ All dependencies gathered (LWJGL, SoundSystem, Rhino)
✅ Natives (32-bit and 64-bit) configured
✅ Phase 9 compilation verified
✅ Mod fully playable

## Next Steps
- Edit AC_ source files to add features
- Recompile with `build.ps1`
- Test in-game
- Iterate

## Documentation
- `README.md` - Quick start
- `README_PROJECT.md` - Full project details
- `INTELLIJ_SETUP.md` - IDE setup (if using IntelliJ)
- `REVERSE_ENGINEERING_SUCCESS.md` - How we got here
- `WHY_DEOBFUSCATION_MATTERS.md` - Technical background
