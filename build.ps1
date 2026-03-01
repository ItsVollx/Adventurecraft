#!/usr/bin/env pwsh
# AdventureCraft B1.7.3 - Fresh Build Script

$workspace = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $workspace

Write-Host "=== AdventureCraft B1.7.3 Build ===" -ForegroundColor Cyan
Write-Host ""

# Step 0: Reset vanilla terrain atlas
Write-Host "0. Resetting vanilla terrain atlas..." -ForegroundColor Yellow
if (Test-Path "lib/terrain.png") {
    Copy-Item "lib/terrain.png" "bin/terrain.png" -Force
    Write-Host "   bin/terrain.png restored from lib/terrain.png" -ForegroundColor Green
} else {
    Write-Host "   Warning: lib/terrain.png not found" -ForegroundColor Yellow
}

# Step 1: Setup build directory
Write-Host "1. Preparing build environment..." -ForegroundColor Yellow
# Clean only class files, preserve resources
Get-ChildItem bin -Recurse -Filter "*.class" -ErrorAction SilentlyContinue | Remove-Item -Force
# Ensure bin directory exists
New-Item bin -ItemType Directory -Force -ErrorAction SilentlyContinue > $null
Write-Host "   Done" -ForegroundColor Green

# Step 2: Compile all source files
Write-Host "2. Compiling source files..." -ForegroundColor Yellow
$srcFiles = @(Get-ChildItem src -Recurse -Filter "*.java" | ForEach-Object {$_.FullName})

if ($srcFiles.Count -gt 0) {
    Write-Host "   Found $($srcFiles.Count) files to compile..."
    $srcFiles | Out-File -Encoding ASCII compile-files.txt
    $cp = "lib/lwjgl-2.9.4-nightly-20150209.jar;lib/lwjgl_util-2.9.4-nightly-20150209.jar;lib/SoundSystem.jar;lib/rhino-1.7.14.jar"
    javac -cp $cp -d bin -encoding UTF-8 "@compile-files.txt" 2>&1 | Select-Object -Last 15
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host "   Compilation successful!" -ForegroundColor Green
    } else {
        Write-Host "   Compilation failed!" -ForegroundColor Red
        exit 1
    }
} else {
    Write-Host "   No source files found!" -ForegroundColor Red
    exit 1
}

# Step 3: Create JAR
Write-Host "3. Building JAR..." -ForegroundColor Yellow
@"
Manifest-Version: 1.0
Main-Class: net.minecraft.src.MinecraftImpl
"@ | Out-File -Encoding ASCII manifest.txt

jar cvfm adventurecraft-mod.jar manifest.txt -C bin . 2>&1 | Select-Object -Last 1
Write-Host "   Done!" -ForegroundColor Green

Write-Host ""
Write-Host "Build complete!" -ForegroundColor Green
Write-Host "Output: adventurecraft-mod.jar" -ForegroundColor Cyan
