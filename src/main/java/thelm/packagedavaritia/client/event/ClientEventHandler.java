package thelm.packagedavaritia.client.event;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import thelm.packagedavaritia.client.screen.ExtremeCrafterScreen;
import thelm.packagedavaritia.menu.PackagedAvaritiaMenus;

public class ClientEventHandler {

	public static final ClientEventHandler INSTANCE = new ClientEventHandler();

	public static ClientEventHandler getInstance() {
		return INSTANCE;
	}

	public void onConstruct(IEventBus modEventBus) {
		modEventBus.register(this);
	}

	@SubscribeEvent
	public void onRegisterMenuScreens(RegisterMenuScreensEvent event) {
		event.register(PackagedAvaritiaMenus.EXTREME_CRAFTER.get(), ExtremeCrafterScreen::new);
	}
}
