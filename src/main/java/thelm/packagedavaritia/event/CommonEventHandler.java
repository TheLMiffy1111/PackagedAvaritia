package thelm.packagedavaritia.event;

import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import thelm.packagedauto.util.ApiImpl;
import thelm.packagedavaritia.block.EnderCrafterBlock;
import thelm.packagedavaritia.block.ExtremeCrafterBlock;
import thelm.packagedavaritia.block.NetherCrafterBlock;
import thelm.packagedavaritia.config.PackagedAvaritiaConfig;
import thelm.packagedavaritia.container.EnderCrafterContainer;
import thelm.packagedavaritia.container.ExtremeCrafterContainer;
import thelm.packagedavaritia.container.NetherCrafterContainer;
import thelm.packagedavaritia.recipe.EnderPackageRecipeType;
import thelm.packagedavaritia.recipe.ExtremePackageRecipeType;
import thelm.packagedavaritia.recipe.NetherPackageRecipeType;
import thelm.packagedavaritia.tile.EnderCrafterTile;
import thelm.packagedavaritia.tile.ExtremeCrafterTile;
import thelm.packagedavaritia.tile.NetherCrafterTile;

public class CommonEventHandler {

	public static final CommonEventHandler INSTANCE = new CommonEventHandler();

	public static CommonEventHandler getInstance() {
		return INSTANCE;
	}

	public void onConstruct() {
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
		PackagedAvaritiaConfig.registerConfig();
	}

	@SubscribeEvent
	public void onBlockRegister(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();
		registry.register(NetherCrafterBlock.INSTANCE);
		registry.register(EnderCrafterBlock.INSTANCE);
		registry.register(ExtremeCrafterBlock.INSTANCE);
	}

	@SubscribeEvent
	public void onItemRegister(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();
		registry.register(NetherCrafterBlock.ITEM_INSTANCE);
		registry.register(EnderCrafterBlock.ITEM_INSTANCE);
		registry.register(ExtremeCrafterBlock.ITEM_INSTANCE);
	}

	@SubscribeEvent
	public void onTileEntityRegister(RegistryEvent.Register<TileEntityType<?>> event) {
		IForgeRegistry<TileEntityType<?>> registry = event.getRegistry();
		registry.register(NetherCrafterTile.TYPE_INSTANCE);
		registry.register(EnderCrafterTile.TYPE_INSTANCE);
		registry.register(ExtremeCrafterTile.TYPE_INSTANCE);
	}

	@SubscribeEvent
	public void onMenuRegister(RegistryEvent.Register<ContainerType<?>> event) {
		IForgeRegistry<ContainerType<?>> registry = event.getRegistry();
		registry.register(NetherCrafterContainer.TYPE_INSTANCE);
		registry.register(EnderCrafterContainer.TYPE_INSTANCE);
		registry.register(ExtremeCrafterContainer.TYPE_INSTANCE);
	}

	@SubscribeEvent
	public void onCommonSetup(FMLCommonSetupEvent event) {
		ApiImpl.INSTANCE.registerRecipeType(NetherPackageRecipeType.INSTANCE);
		ApiImpl.INSTANCE.registerRecipeType(EnderPackageRecipeType.INSTANCE);
		ApiImpl.INSTANCE.registerRecipeType(ExtremePackageRecipeType.INSTANCE);
	}

	@SubscribeEvent
	public void onModConfig(ModConfigEvent event) {
		switch(event.getConfig().getType()) {
		case SERVER:
			PackagedAvaritiaConfig.reloadServerConfig();
			break;
		default:
			break;
		}
	}
}
