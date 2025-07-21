package thelm.packagedavaritia.event;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLConstructModEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import thelm.packagedauto.block.entity.BaseBlockEntity;
import thelm.packagedauto.util.ApiImpl;
import thelm.packagedauto.util.MiscHelper;
import thelm.packagedavaritia.block.PackagedAvaritiaBlocks;
import thelm.packagedavaritia.block.entity.PackagedAvaritiaBlockEntities;
import thelm.packagedavaritia.config.PackagedAvaritiaConfig;
import thelm.packagedavaritia.creativetab.PackagedAvaritiaCreativeTabs;
import thelm.packagedavaritia.integration.appeng.AppEngEventHandler;
import thelm.packagedavaritia.item.PackagedAvaritiaItems;
import thelm.packagedavaritia.menu.PackagedAvaritiaMenus;
import thelm.packagedavaritia.recipe.EndPackageRecipeType;
import thelm.packagedavaritia.recipe.ExtremePackageRecipeType;
import thelm.packagedavaritia.recipe.NetherPackageRecipeType;
import thelm.packagedavaritia.recipe.SculkPackageRecipeType;
import thelm.packagedavaritia.variant.VariantChecker;

public class CommonEventHandler {

	public static final CommonEventHandler INSTANCE = new CommonEventHandler();

	public static CommonEventHandler getInstance() {
		return INSTANCE;
	}

	public void onConstruct(IEventBus modEventBus, ModContainer modContainer) {
		modEventBus.register(this);
		MiscHelper.INSTANCE.conditionalRunnable(()->ModList.get().isLoaded("ae2"), ()->()->{
			modEventBus.register(AppEngEventHandler.getInstance());
		}, ()->()->{}).run();
		PackagedAvaritiaConfig.registerConfig(modContainer);

		PackagedAvaritiaBlocks.BLOCKS.register(modEventBus);
		PackagedAvaritiaItems.ITEMS.register(modEventBus);
		PackagedAvaritiaBlockEntities.BLOCK_ENTITIES.register(modEventBus);
		PackagedAvaritiaMenus.MENUS.register(modEventBus);
		PackagedAvaritiaCreativeTabs.CREATIVE_TABS.register(modEventBus);
	}

	@SubscribeEvent
	public void onConstructMod(FMLConstructModEvent event) {
		VariantChecker.check();
	}

	@SubscribeEvent
	public void onCommonSetup(FMLCommonSetupEvent event) {
		ApiImpl.INSTANCE.registerRecipeType(SculkPackageRecipeType.INSTANCE);
		ApiImpl.INSTANCE.registerRecipeType(NetherPackageRecipeType.INSTANCE);
		ApiImpl.INSTANCE.registerRecipeType(EndPackageRecipeType.INSTANCE);
		ApiImpl.INSTANCE.registerRecipeType(ExtremePackageRecipeType.INSTANCE);
	}

	@SubscribeEvent
	public void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, PackagedAvaritiaBlockEntities.SCULK_CRAFTER.get(), BaseBlockEntity::getItemHandler);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, PackagedAvaritiaBlockEntities.NETHER_CRAFTER.get(), BaseBlockEntity::getItemHandler);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, PackagedAvaritiaBlockEntities.END_CRAFTER.get(), BaseBlockEntity::getItemHandler);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, PackagedAvaritiaBlockEntities.EXTREME_CRAFTER.get(), BaseBlockEntity::getItemHandler);

		event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, PackagedAvaritiaBlockEntities.SCULK_CRAFTER.get(), BaseBlockEntity::getEnergyStorage);
		event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, PackagedAvaritiaBlockEntities.NETHER_CRAFTER.get(), BaseBlockEntity::getEnergyStorage);
		event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, PackagedAvaritiaBlockEntities.END_CRAFTER.get(), BaseBlockEntity::getEnergyStorage);
		event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, PackagedAvaritiaBlockEntities.EXTREME_CRAFTER.get(), BaseBlockEntity::getEnergyStorage);
	}

	@SubscribeEvent
	public void onModConfigLoading(ModConfigEvent.Loading event) {
		switch(event.getConfig().getType()) {
		case SERVER -> PackagedAvaritiaConfig.reloadServerConfig();
		default -> {}
		}
	}

	@SubscribeEvent
	public void onModConfigReloading(ModConfigEvent.Reloading event) {
		switch(event.getConfig().getType()) {
		case SERVER -> PackagedAvaritiaConfig.reloadServerConfig();
		default -> {}
		}
	}
}
