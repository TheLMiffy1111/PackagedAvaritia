package thelm.packagedavaritia.integration.appeng;

import appeng.api.AECapabilities;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import thelm.packagedauto.integration.appeng.AppEngUtil;
import thelm.packagedavaritia.block.entity.PackagedAvaritiaBlockEntities;

public class AppEngEventHandler {

	public static final AppEngEventHandler INSTANCE = new AppEngEventHandler();

	public static AppEngEventHandler getInstance() {
		return INSTANCE;
	}

	@SubscribeEvent
	public void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
		event.registerBlockEntity(AECapabilities.IN_WORLD_GRID_NODE_HOST, PackagedAvaritiaBlockEntities.SCULK_CRAFTER.get(), (be, v)->AppEngUtil.getAsInWorldGridNodeHost(be));
		event.registerBlockEntity(AECapabilities.IN_WORLD_GRID_NODE_HOST, PackagedAvaritiaBlockEntities.NETHER_CRAFTER.get(), (be, v)->AppEngUtil.getAsInWorldGridNodeHost(be));
		event.registerBlockEntity(AECapabilities.IN_WORLD_GRID_NODE_HOST, PackagedAvaritiaBlockEntities.END_CRAFTER.get(), (be, v)->AppEngUtil.getAsInWorldGridNodeHost(be));
		event.registerBlockEntity(AECapabilities.IN_WORLD_GRID_NODE_HOST, PackagedAvaritiaBlockEntities.EXTREME_CRAFTER.get(), (be, v)->AppEngUtil.getAsInWorldGridNodeHost(be));
	}
}
