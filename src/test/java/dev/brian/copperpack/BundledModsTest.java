package dev.brian.copperpack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.Test;

class BundledModsTest {
	@Test
	void bundlesAllSevenCopperMods() {
		assertEquals(
			List.of("copperbucket", "coppershears", "copperfurnace", "copperhopper",
				"copperanvil", "copperfishingrod", "copperflintandsteel"),
			BundledMods.ALL.stream().map(BundledMods.Entry::modId).toList());
	}

	@Test
	void modIdsAreUnique() {
		var ids = BundledMods.ALL.stream().map(BundledMods.Entry::modId).toList();
		assertEquals(ids.size(), new HashSet<>(ids).size());
	}

	@Test
	void eachMainItemLivesInItsOwnModNamespace() {
		for (var entry : BundledMods.ALL) {
			assertTrue(entry.mainItem().startsWith(entry.modId() + ":"),
				entry.mainItem() + " should be in namespace " + entry.modId());
		}
	}
}
