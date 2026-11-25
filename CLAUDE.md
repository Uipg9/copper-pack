# Copper Pack: working notes for Claude

- Minecraft 26.3 Fabric mod. Java 25. Unobfuscated: use Mojang names, never add mappings/yarn.
- No gameplay code here: the pack nests the copper mods from `~/.m2` (jar-in-jar). Gameplay changes
  belong in the individual mod folders (`Desktop\mods\copper*`).
- Before building: `.\scripts\publish-all.ps1`. The mod list is `bundled_mods` in `gradle.properties`
  and must match `BundledMods.ALL` (unit-tested).
- Shell is Windows PowerShell: use `.\gradlew.bat`. Filter Gradle output to errors only.
- Verify with `.\scripts\verify.ps1`. Never kill processes: `runClientGameTest` exits by itself.
- Mod Menu is dev-only (`localRuntime`); do not make it a hard dependency.
