# IntelliJ IDEA - Fix "Cannot Find Symbol" Errors

## Problem
IntelliJ shows errors like "cannot find symbol: class xp, yb, ln, bt, eq, vf, eb"
These classes exist in `minecraft.jar` but IntelliJ isn't loading them.

## Solution: Force IntelliJ to Reload

### Method 1: Invalidate Caches (RECOMMENDED)
1. **File → Invalidate Caches...**
2. Check **all boxes**:
   - ✅ Invalidate and Restart
   - ✅ Clear file system cache
   - ✅ Clear downloaded shared indexes
3. Click **Invalidate and Restart**
4. Wait for IntelliJ to restart and re-index (2-5 minutes)

### Method 2: Reload Project
1. **File → Reload All from Disk** (or press `Ctrl+Shift+O`)
2. Wait for indexing to complete
3. **Build → Rebuild Project**

### Method 3: Re-import Module
1. Close IntelliJ completely
2. Delete `.idea/` folder (optional, but thorough)
3. Open IntelliJ
4. **File → Open** → Select `workspace` folder
5. Wait for project to load and index

### Method 4: Manual Library Sync
1. **File → Project Structure** (Ctrl+Alt+Shift+S)
2. Navigate to **Modules → AdventureCraft → Dependencies**
3. Verify these libraries are listed:
   - ✅ minecraft-b1.7.3 (minecraft.jar)
   - ✅ ACBin (ACBin.jar)
   - ✅ Rhino-JSLib (JSLib.jar)
   - ✅ LWJGL (both LWJGL JARs)
4. If any are missing, click **+** → **JARs or directories** → Add them
5. Click **Apply** → **OK**
6. **Build → Rebuild Project**

## Verification
After reloading, check that compilation works:
1. Open any file (e.g., `fd.java`)
2. No red underlines on `xp`, `ln`, `bt`, `eq`, `vf`, `yb`, `eb`
3. **Build → Rebuild Project** → 0 errors

## Why This Happens
- IntelliJ caches library indexes
- After we updated `.iml` and added LWJGL to `lib/`, the cache was stale
- Invalidating caches forces a fresh scan of all JAR files
- The obfuscated classes (xp, ln, bt, etc.) are IN minecraft.jar, just not in src/

## Still Not Working?
Check that Java JDK is configured:
1. **File → Project Structure → Project**
2. **Project SDK** should show a JDK (Java 8+ recommended, Java 25 works too)
3. **Project language level** → Set to "8 - Lambdas, type annotations, etc."
