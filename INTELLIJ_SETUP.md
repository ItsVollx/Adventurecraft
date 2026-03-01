# IntelliJ IDEA Setup Guide

## ✅ Project is Ready!

The workspace has been cleaned up and configured for IntelliJ IDEA.

## 🚀 Getting Started

### 1. Open Project in IntelliJ

```
File → Open → Navigate to: adventurecraft re-enigeener/workspace/
```

IntelliJ will detect `AdventureCraft.iml` and load the project automatically.

### 2. Verify Project Structure

**Project Settings** (`Ctrl+Alt+Shift+S` or `File → Project Structure`)

- **SDK**: Java 8 (JDK 1.8)
- **Language Level**: 8 - Lambdas, type annotations etc.
- **Output Path**: `workspace/bin`

### 3. Source Folders

These are already configured in `AdventureCraft.iml`:

- ✅ `src/` - Decompiled AdventureCraft source (909 files)

### 4. Libraries

All libraries are pre-configured:

| Library | Path | Description |
|---------|------|-------------|
| **minecraft-b1.7.3** | `lib/minecraft.jar` | Base Minecraft B1.7.3 |
| **ACBin** | `lib/ACBin.jar` | AdventureCraft mod |
| **Rhino-JSLib** | `lib/JSLib.jar` | JavaScript engine |
| **LWJGL** | `../tools/RetroMCP-Java/...` | Graphics & input |
| **AC-Launcher** | `lib/AdventureCraft.jar` | Game launcher |

## 🔨 Building & Running

### Build in IntelliJ

```
Build → Build Project (Ctrl+F9)
```

**Note**: Source code now compiles successfully! All CFR placeholder types have been fixed.

### Run the Game

**From Terminal:**
```batch
RUN_GAME.bat
```

**From IntelliJ Terminal:**
```
.\RUN_GAME.bat
```

The game will launch with full AdventureCraft features!

## 📂 Project Structure

```
workspace/
│
├── 📁 src/                       ⭐ Main source code (browse here!)
│   ├── AC_BlockCamera.java      ✅ Readable AdventureCraft classes
│   ├── AC_TriggerManager.java   ✅ Readable
│   ├── fd.java                  🟡 Obfuscated (World class)
│   ├── gs.java                  🟡 Obfuscated (EntityPlayer)
│   └── net/minecraft/script/    ✅ Readable script API
│
├── 📁 lib/                       Required JARs
├── 📁 bin/                       Compiled output
│
├── 📁 scripts/                   Build utilities
├── 📁 docs/                      Documentation
│
├── AdventureCraft.iml            ⚙️ IntelliJ module config
├── build.bat                     Build script
└── RUN_GAME.bat                  Launch game
```

## 🎯 What You Can Do Now

### 1. Browse Source Code

Navigate to interesting classes:

**AdventureCraft Features:**
- `src/AC_TriggerManager.java` - Trigger system
- `src/AC_BlockCamera.java` - Cutscene cameras
- `src/AC_EntityNPC.java` - Custom NPCs
- `src/AC_JScriptHandler.java` - JavaScript integration

**Script API:**
- `src/net/minecraft/script/Script.java`
- `src/net/minecraft/script/ScriptWorld.java`
- `src/net/minecraft/script/ScriptEntity.java`

### 2. Search & Navigate

**Find Class:** `Ctrl+N`
- Type: `AC_Block` to find all block classes
- Type: `Script` to find script-related classes

**Find in Files:** `Ctrl+Shift+F`
- Search across all source code

**Go to Definition:** `Ctrl+B` or `Ctrl+Click`

### 3. Edit & Experiment

**Create New Class:**
```
src/ → Right-click → New → Java Class
```

**Modify Existing:**
- Edit files in `src/`
- Rebuild with `build.bat` to test changes

### 4. Code Analysis

**Find Usages:** `Alt+F7`
- See where methods/classes are used

**Call Hierarchy:** `Ctrl+Alt+H`
- See call chains

**Type Hierarchy:** `Ctrl+H`
- See class inheritance

## 📝 Source Code Status

| Category | Files | Readability |
|----------|-------|-------------|
| AC_* Classes | 181 | ✅ Fully readable class names |
| Script API | 36 | ✅ Fully readable |
| Base Minecraft | 691 | 🟡 Obfuscated (fd, gs, uu...) |

**Methods**: Mostly single letters (a(), b(), c())  
**Variables**: Mostly single letters (i, j, k, x, y, z)

### Common Obfuscated Classes

Reference guide for reading code:

| Obfuscated | Actual Class | Usage |
|------------|--------------|-------|
| `fd` | World | World/level management |
| `gs` | EntityPlayer | Player entity |
| `uu` | Block | Block types |
| `gm` | Item | Item types |
| `sn` | Entity | Base entity class |
| `ln`/`lm` | Chunk | World chunks |
| `bt` | Vec3D | 3D vectors |
| `vf` | MovingObjectPosition | Ray trace results |
| `eq` | AxisAlignedBB | Bounding boxes |

## 🐛 Known Issues

1. **Obfuscated Names**
   - Base Minecraft classes use short names
   - Use the reference table above
   - AC-specific code is fully readable

2. **Method Names**
   - Many methods named `a()`, `b()`, `c()`
   - Use "Find Usages" to understand what they do
   - Add comments as you figure things out

## 📚 Documentation

- `docs/QUICKSTART.md` - Quick reference
- `docs/deobfuscation_mappings.txt` - Class name mappings
- `README_PROJECT.md` - Full project overview

## 🎓 Tips for Working with Obfuscated Code

1. **Use IntelliJ's Navigation**
   - Let the IDE show you where things are used
   - Type hierarchy helps understand inheritance

2. **Add Comments**
   - Document what you figure out
   - Help yourself and others

3. **Focus on AC_* Classes**
   - These are the interesting, readable parts
   - Base Minecraft code is just framework

4. **Use Debugger**
   - Set breakpoints to watch execution
   - Helps understand what obfuscated methods do

## ✨ Success!

Your IntelliJ workspace is ready to go. You can:

- ✅ Browse all decompiled source code
- ✅ Search & navigate through classes
- ✅ Compile source code (compile.bat)
- ✅ Run AdventureCraft from compiled source
- ✅ Edit, rebuild, and test changes
- ✅ Full reverse engineering capabilities

**Have fun exploring AdventureCraft! 🎮**
