package thelm.packagedavaritia;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import thelm.packagedavaritia.event.CommonEventHandler;

@Mod(PackagedAvaritia.MOD_ID)
public class PackagedAvaritia {

	public static final String MOD_ID = "packagedavaritia";

	public PackagedAvaritia(IEventBus modEventBus, ModContainer modContainer) {
		CommonEventHandler.getInstance().onConstruct(modEventBus, modContainer);
	}
}
