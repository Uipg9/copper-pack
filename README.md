# Copper Pack

All seven copper mods in one jar, for Minecraft **26.3** (Fabric). Requires Fabric API.

| Mod | What it adds |
|---|---|
| Copper Bucket | A bucket that wears out after 64 uses |
| Copper Shears | Cheaper shears, 150 durability |
| Copper Furnace | Smelts 1.5x faster; slows as it oxidizes (scrape or wax it) |
| Copper Hopper | A hopper without iron; moves items at half speed |
| Copper Anvil | A cheaper anvil that wears out twice as fast |
| Copper Fishing Rod | A fishing rod with a built-in Lure I |
| Copper Flint and Steel | Cheaper flint and steel, 40 durability |

Each mod is also available on its own. Don't install a single mod next to the pack.

## Building

The pack has no code of its own; it nests the other mods (jar-in-jar).

1. `.\scripts\publish-all.ps1` publishes every mod in `Desktop\mods\<modid>` to `~/.m2`.
2. `.\scripts\verify.ps1` builds the pack and runs the client gametest, which checks that every
   mod loaded and registered its item (screenshot: `build\run\clientGameTest\screenshots\`).

The list of bundled mods lives in `gradle.properties` (`bundled_mods`) and must match `BundledMods.ALL`.
