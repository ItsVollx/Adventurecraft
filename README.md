# AdventureCraft Mod - B1.7.3

## Quick Start

### Build the mod:
```powershell
.\build.ps1
```
This will:
1. Extract vanilla Minecraft B1.7.3 classes
2. Compile all `AC_*.java` mod files
3. Package everything into `adventurecraft-mod.jar`

### Run the game:
```powershell
.\run.ps1
```

## Project Structure

- **src-ac/** - AdventureCraft mod source code (all AC_*.java files)
- **lib/** - Required libraries:
  - lwjgl-2.9.4-nightly-20150209.jar (graphics)
  - lwjgl_util-2.9.4-nightly-20150209.jar (graphics utilities)
  - SoundSystem.jar (audio)
  - rhino-1.7.14.jar (JavaScript engine)
  - minecraft.jar (vanilla Minecraft B1.7.3)
- **natives/** - Native libraries (lwjgl.dll, OpenAL64.dll, etc.)
- **bin/** - Compiled classes (created during build)

## Editing the Mod

1. Edit any file in `src-ac/net/minecraft/src/AC_*.java`
2. Run `.\build.ps1` to recompile
3. Run `.\run.ps1` to test

## Troubleshooting

**"Cannot find or load main class..."**
- Run build.ps1 first

**"ClassNotFoundException: ..."**
- Make sure all lib/*.jar files exist
- Check that natives/ folder has the DLL files

**"UnsatisfiedLinkError" on startup**
- Verify natives/lwjgl.dll and natives/OpenAL64.dll exist
