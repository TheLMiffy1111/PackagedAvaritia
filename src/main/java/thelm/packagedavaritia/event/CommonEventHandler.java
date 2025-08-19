package thelm.packagedavaritia.event;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thelm.packagedauto.api.RecipeTypeRegistry;
import thelm.packagedavaritia.block.BlockExtremeCrafter;
import thelm.packagedavaritia.config.PackagedAvaritiaConfig;
import thelm.packagedavaritia.recipe.RecipeTypeExtreme;
import thelm.packagedavaritia.tile.TileExtremeCrafter;

public class CommonEventHandler {

	public void registerBlock(Block block) {
		ForgeRegistries.BLOCKS.register(block);
	}

	public void registerItem(Item item) {
		ForgeRegistries.ITEMS.register(item);
	}

	public void onPreInit(FMLPreInitializationEvent event) {
		registerConfig(event);
		registerBlocks();
		registerItems();
		registerTileEntities();
		registerRecipeTypes();
	}

	protected void registerConfig(FMLPreInitializationEvent event) {
		PackagedAvaritiaConfig.init(event.getSuggestedConfigurationFile());
	}

	protected void registerBlocks() {
		registerBlock(BlockExtremeCrafter.INSTANCE);
	}

	protected void registerItems() {
		registerItem(BlockExtremeCrafter.ITEM_INSTANCE);
	}

	protected void registerTileEntities() {
		GameRegistry.registerTileEntity(TileExtremeCrafter.class, new ResourceLocation("packagedavaritia:extreme_crafter"));
	}

	protected void registerRecipeTypes() {
		RecipeTypeRegistry.registerRecipeType(RecipeTypeExtreme.INSTANCE);
	}
}
