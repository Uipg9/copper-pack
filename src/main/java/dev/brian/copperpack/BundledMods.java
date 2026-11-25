package dev.brian.copperpack;

import java.util.List;

/**
 * The copper mods nested in this jar (see {@code build.gradle}). Copper Pack has no code of its
 * own; this list only exists so the tests can check that every bundled mod actually loaded.
 */
public final class BundledMods {
	public static final String MOD_ID = "copperpack";

	/** A bundled mod and the item id that proves it registered its content. */
	public record Entry(String modId, String mainItem) {}

	public static final List<Entry> ALL = List.of(
		new Entry("copperbucket", "copperbucket:copper_bucket"),
		new Entry("coppershears", "coppershears:copper_shears"),
		new Entry("copperfurnace", "copperfurnace:copper_furnace"),
		new Entry("copperhopper", "copperhopper:copper_hopper"),
		new Entry("copperanvil", "copperanvil:copper_anvil"),
		new Entry("copperfishingrod", "copperfishingrod:copper_fishing_rod"),
		new Entry("copperflintandsteel", "copperflintandsteel:copper_flint_and_steel"));

	private BundledMods() {}
}
