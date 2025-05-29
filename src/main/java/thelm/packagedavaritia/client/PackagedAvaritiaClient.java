package thelm.packagedavaritia.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import thelm.packagedavaritia.PackagedAvaritia;
import thelm.packagedavaritia.client.event.ClientEventHandler;

@Mod(value = PackagedAvaritia.MOD_ID, dist = Dist.CLIENT)
public class PackagedAvaritiaClient {

	public PackagedAvaritiaClient(IEventBus modEventBus) {
		ClientEventHandler.getInstance().onConstruct(modEventBus);
	}
}
