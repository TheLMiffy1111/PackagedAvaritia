package thelm.packagedavaritia.client.event;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import thelm.packagedavaritia.PackagedAvaritia;

@Mod(value = PackagedAvaritia.MOD_ID, dist = Dist.CLIENT)
public class PackagedAvaritiaClient {

	public PackagedAvaritiaClient(IEventBus modEventBus) {
		ClientEventHandler.getInstance().onConstruct(modEventBus);
	}
}
