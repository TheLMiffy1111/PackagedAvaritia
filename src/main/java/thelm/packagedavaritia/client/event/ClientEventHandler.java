package thelm.packagedavaritia.client.event;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import thelm.packagedavaritia.client.screen.EndCrafterScreen;
import thelm.packagedavaritia.client.screen.ExtremeCrafterScreen;
import thelm.packagedavaritia.client.screen.NetherCrafterScreen;
import thelm.packagedavaritia.client.screen.SculkCrafterScreen;
import thelm.packagedavaritia.menu.EndCrafterMenu;
import thelm.packagedavaritia.menu.ExtremeCrafterMenu;
import thelm.packagedavaritia.menu.NetherCrafterMenu;
import thelm.packagedavaritia.menu.SculkCrafterMenu;

public class ClientEventHandler {

	public static final ClientEventHandler INSTANCE = new ClientEventHandler();

	public static ClientEventHandler getInstance() {
		return INSTANCE;
	}

	@SuppressWarnings("removal")
	public void onConstruct() {
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	public void onClientSetup(FMLClientSetupEvent event) {
		MenuScreens.register(SculkCrafterMenu.TYPE_INSTANCE, SculkCrafterScreen::new);
		MenuScreens.register(NetherCrafterMenu.TYPE_INSTANCE, NetherCrafterScreen::new);
		MenuScreens.register(EndCrafterMenu.TYPE_INSTANCE, EndCrafterScreen::new);
		MenuScreens.register(ExtremeCrafterMenu.TYPE_INSTANCE, ExtremeCrafterScreen::new);
	}
}
