package thelm.packagedavaritia.event;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import thelm.packagedauto.util.ApiImpl;
import thelm.packagedavaritia.block.EndCrafterBlock;
import thelm.packagedavaritia.block.ExtremeCrafterBlock;
import thelm.packagedavaritia.block.NetherCrafterBlock;
import thelm.packagedavaritia.block.SculkCrafterBlock;
import thelm.packagedavaritia.block.entity.EndCrafterBlockEntity;
import thelm.packagedavaritia.block.entity.ExtremeCrafterBlockEntity;
import thelm.packagedavaritia.block.entity.NetherCrafterBlockEntity;
import thelm.packagedavaritia.block.entity.SculkCrafterBlockEntity;
import thelm.packagedavaritia.config.PackagedAvaritiaConfig;
import thelm.packagedavaritia.menu.EndCrafterMenu;
import thelm.packagedavaritia.menu.ExtremeCrafterMenu;
import thelm.packagedavaritia.menu.NetherCrafterMenu;
import thelm.packagedavaritia.menu.SculkCrafterMenu;
import thelm.packagedavaritia.recipe.EndPackageRecipeType;
import thelm.packagedavaritia.recipe.ExtremePackageRecipeType;
import thelm.packagedavaritia.recipe.NetherPackageRecipeType;
import thelm.packagedavaritia.recipe.SculkPackageRecipeType;

public class CommonEventHandler {

	public static final CommonEventHandler INSTANCE = new CommonEventHandler();

	public static CommonEventHandler getInstance() {
		return INSTANCE;
	}

	public void onConstruct() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.register(this);
		PackagedAvaritiaConfig.registerConfig();

		DeferredRegister<Block> blockRegister = DeferredRegister.create(Registries.BLOCK, "packagedavaritia");
		blockRegister.register(modEventBus);
		blockRegister.register("sculk_crafter", ()->SculkCrafterBlock.INSTANCE);
		blockRegister.register("nether_crafter", ()->NetherCrafterBlock.INSTANCE);
		blockRegister.register("end_crafter", ()->EndCrafterBlock.INSTANCE);
		blockRegister.register("extreme_crafter", ()->ExtremeCrafterBlock.INSTANCE);

		DeferredRegister<Item> itemRegister = DeferredRegister.create(Registries.ITEM, "packagedavaritia");
		itemRegister.register(modEventBus);
		itemRegister.register("sculk_crafter", ()->SculkCrafterBlock.ITEM_INSTANCE);
		itemRegister.register("nether_crafter", ()->NetherCrafterBlock.ITEM_INSTANCE);
		itemRegister.register("end_crafter", ()->EndCrafterBlock.ITEM_INSTANCE);
		itemRegister.register("extreme_crafter", ()->ExtremeCrafterBlock.ITEM_INSTANCE);

		DeferredRegister<BlockEntityType<?>> blockEntityRegister = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, "packagedavaritia");
		blockEntityRegister.register(modEventBus);
		blockEntityRegister.register("sculk_crafter", ()->SculkCrafterBlockEntity.TYPE_INSTANCE);
		blockEntityRegister.register("nether_crafter", ()->NetherCrafterBlockEntity.TYPE_INSTANCE);
		blockEntityRegister.register("end_crafter", ()->EndCrafterBlockEntity.TYPE_INSTANCE);
		blockEntityRegister.register("extreme_crafter", ()->ExtremeCrafterBlockEntity.TYPE_INSTANCE);

		DeferredRegister<MenuType<?>> menuRegister = DeferredRegister.create(Registries.MENU, "packagedavaritia");
		menuRegister.register(modEventBus);
		menuRegister.register("sculk_crafter", ()->SculkCrafterMenu.TYPE_INSTANCE);
		menuRegister.register("nether_crafter", ()->NetherCrafterMenu.TYPE_INSTANCE);
		menuRegister.register("end_crafter", ()->EndCrafterMenu.TYPE_INSTANCE);
		menuRegister.register("extreme_crafter", ()->ExtremeCrafterMenu.TYPE_INSTANCE);

		DeferredRegister<CreativeModeTab> creativeTabRegister = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "packagedavaritia");
		creativeTabRegister.register(modEventBus);
		creativeTabRegister.register("tab",
				()->CreativeModeTab.builder().
				title(Component.translatable("itemGroup.packagedavaritia")).
				icon(()->new ItemStack(ExtremeCrafterBlock.ITEM_INSTANCE)).
				displayItems((parameters, output)->{
					output.accept(SculkCrafterBlock.ITEM_INSTANCE);
					output.accept(NetherCrafterBlock.ITEM_INSTANCE);
					output.accept(EndCrafterBlock.ITEM_INSTANCE);
					output.accept(ExtremeCrafterBlock.ITEM_INSTANCE);
				}).
				build());
	}

	@SubscribeEvent
	public void onCommonSetup(FMLCommonSetupEvent event) {
		ApiImpl.INSTANCE.registerRecipeType(SculkPackageRecipeType.INSTANCE);
		ApiImpl.INSTANCE.registerRecipeType(NetherPackageRecipeType.INSTANCE);
		ApiImpl.INSTANCE.registerRecipeType(EndPackageRecipeType.INSTANCE);
		ApiImpl.INSTANCE.registerRecipeType(ExtremePackageRecipeType.INSTANCE);
	}

	@SubscribeEvent
	public void onModConfig(ModConfigEvent event) {
		switch(event.getConfig().getType()) {
		case SERVER -> PackagedAvaritiaConfig.reloadServerConfig();
		default -> {}
		}
	}
}
