package dev.brian.copperpack.client;

import dev.brian.copperpack.BundledMods;
import java.util.ArrayList;
import java.util.List;
import net.fabricmc.fabric.api.client.gametest.v1.FabricClientGameTest;
import net.fabricmc.fabric.api.client.gametest.v1.context.ClientGameTestContext;
import net.fabricmc.fabric.api.client.gametest.v1.context.TestSingleplayerContext;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

/** Boots the client with the pack and checks every nested copper mod loaded and registered its item. */
public class CopperpackClientGameTest implements FabricClientGameTest {
	@Override
	public void runTest(ClientGameTestContext context) {
		List<String> missing = new ArrayList<>();
		for (var entry : BundledMods.ALL) {
			if (!FabricLoader.getInstance().isModLoaded(entry.modId())) missing.add(entry.modId());
		}
		if (!missing.isEmpty()) throw new AssertionError("bundled mods not loaded: " + missing);

		context.waitForScreen(TitleScreen.class);

		try (TestSingleplayerContext world = context.worldBuilder().create()) {
			context.waitTicks(60);
			List<String> unregistered = new ArrayList<>();
			for (var entry : BundledMods.ALL) {
				if (!BuiltInRegistries.ITEM.containsKey(Identifier.parse(entry.mainItem()))) {
					unregistered.add(entry.mainItem());
				}
			}
			if (!unregistered.isEmpty()) throw new AssertionError("items not registered: " + unregistered);

			// Put one of each item in the hotbar so the screenshot shows every texture.
			world.getServer().runOnServer(server -> {
				var player = server.getPlayerList().getPlayers().getFirst();
				for (int i = 0; i < BundledMods.ALL.size(); i++) {
					var item = BuiltInRegistries.ITEM.getValue(Identifier.parse(BundledMods.ALL.get(i).mainItem()));
					player.getInventory().setItem(i, new ItemStack(item));
				}
			});
			context.waitTicks(20);
			context.getInput().pressKey(options -> options.keyInventory);
			context.waitForScreen(AbstractContainerScreen.class);
			context.takeScreenshot("copper_pack_items");
		}
	}
}
