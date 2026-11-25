<#
.SYNOPSIS
    Publishes every copper mod to the local maven repo (~/.m2) so Copper Pack can nest them.
.DESCRIPTION
    Run from anywhere. Each mod must live next to this project in Desktop\mods\<modid>.
    Then build the pack with .\gradlew.bat build (or .\scripts\verify.ps1).
#>
$ErrorActionPreference = 'Stop'
$modsDir = Split-Path (Split-Path $PSScriptRoot -Parent) -Parent
$props = Get-Content (Join-Path $PSScriptRoot '..\gradle.properties')
$list = ($props | Where-Object { $_ -like 'bundled_mods=*' }) -replace '^bundled_mods=', ''
$failed = @()
foreach ($mod in $list.Split(',')) {
    $dir = Join-Path $modsDir $mod
    if (-not (Test-Path (Join-Path $dir 'gradlew.bat'))) { $failed += "$mod (folder missing)"; continue }
    Push-Location $dir
    try {
        $out = & .\gradlew.bat publishToMavenLocal --quiet 2>&1
        if ($LASTEXITCODE -ne 0) {
            $failed += $mod
            $out | Select-String -Pattern 'error|FAILED|What went wrong' | Select-Object -First 5 | ForEach-Object { "  ${mod}: $_" }
        } else { "published $mod" }
    } finally { Pop-Location }
}
if ($failed) { "FAILED: $($failed -join ', ')"; exit 1 }
"all $(($list.Split(',')).Count) mods published"
