package thelm.packagedavaritia.creativetab;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import thelm.packagedavaritia.item.PackagedAvaritiaItems;

public class PackagedAvaritiaCreativeTabs {

	private PackagedAvaritiaCreativeTabs() {}

	public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "packagedavaritia");

	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = CREATIVE_TABS.register(
			"tab", ()->CreativeModeTab.builder().
			title(Component.translatable("itemGroup.packagedavaritia")).
			icon(PackagedAvaritiaItems.EXTREME_CRAFTER::toStack).
			displayItems((parameters, output)->{
				output.accept(PackagedAvaritiaItems.SCULK_CRAFTER);
				output.accept(PackagedAvaritiaItems.NETHER_CRAFTER);
				output.accept(PackagedAvaritiaItems.END_CRAFTER);
				output.accept(PackagedAvaritiaItems.EXTREME_CRAFTER);
			}).build());
}
