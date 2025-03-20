package thelm.packagedavaritia.client.event;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import thelm.packagedavaritia.client.screen.EnderCrafterScreen;
import thelm.packagedavaritia.client.screen.ExtremeCrafterScreen;
import thelm.packagedavaritia.client.screen.NetherCrafterScreen;
import thelm.packagedavaritia.container.EnderCrafterContainer;
import thelm.packagedavaritia.container.ExtremeCrafterContainer;
import thelm.packagedavaritia.container.NetherCrafterContainer;

public class ClientEventHandler {

	public static final ClientEventHandler INSTANCE = new ClientEventHandler();

	public static ClientEventHandler getInstance() {
		return INSTANCE;
	}

	public void onConstruct() {
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	public void onClientSetup(FMLClientSetupEvent event) {
		ScreenManager.register(NetherCrafterContainer.TYPE_INSTANCE, NetherCrafterScreen::new);
		ScreenManager.register(EnderCrafterContainer.TYPE_INSTANCE, EnderCrafterScreen::new);
		ScreenManager.register(ExtremeCrafterContainer.TYPE_INSTANCE, ExtremeCrafterScreen::new);
	}
}
