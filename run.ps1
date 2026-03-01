#!/usr/bin/env pwsh
<# AdventureCraft Run Script #>

$workspace = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $workspace

Write-Host "=== AdventureCraft B1.7.3 Launcher ===" -ForegroundColor Cyan
Write-Host ""

# Use Java 8 for LWJGL 2.9.4 compatibility
$java8Path = "E:\Program Files\Eclipse Adoptium\jdk-8.0.472.8-hotspot\bin\java.exe"
if (Test-Path $java8Path) {
    $javaExe = $java8Path
    Write-Host "Using Java 8 for compatibility" -ForegroundColor Gray
} else {
    $javaExe = "java"
    Write-Host "Warning: Java 8 not found, using system Java" -ForegroundColor Yellow
}
Write-Host ""

if (-not (Test-Path adventurecraft-mod.jar)) {
    Write-Host "error: adventurecraft-mod.jar not found" -ForegroundColor Red
    Write-Host "Run: .\build.ps1" -ForegroundColor Yellow
    exit 1
}

Write-Host "Launching Minecraft B1.7.3 with AdventureCraft mod..." -ForegroundColor Green
Write-Host ""

$nativesPath = Join-Path $workspace "natives"
$classPath = "adventurecraft-mod.jar;lib/ACBin.jar;lib/AdventureCraft.jar;lib/JSLib.jar;lib/minecraft.jar;lib/rhino-1.7.14.jar;lib/SoundSystem.jar;lib/lwjgl-2.9.4-nightly-20150209.jar;lib/lwjgl_util-2.9.4-nightly-20150209.jar"

Write-Host "Natives: $nativesPath" -ForegroundColor Gray
Write-Host ""

& $javaExe -Xmx1024M -Xms512M "-Djava.library.path=$nativesPath" -cp $classPath net.minecraft.src.MinecraftImpl
