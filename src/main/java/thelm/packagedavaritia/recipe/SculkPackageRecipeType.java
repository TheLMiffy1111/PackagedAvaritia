package thelm.packagedavaritia.recipe;

import java.util.List;

import committee.nova.mods.avaritia.common.crafting.recipe.ShapedTableCraftingRecipe;
import committee.nova.mods.avaritia.init.registry.ModBlocks;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntRBTreeSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import thelm.packagedauto.api.IPackageRecipeInfo;
import thelm.packagedauto.api.IPackageRecipeType;
import thelm.packagedauto.api.IRecipeSlotViewWrapper;
import thelm.packagedauto.api.IRecipeSlotsViewWrapper;

public class SculkPackageRecipeType implements IPackageRecipeType {

	public static final SculkPackageRecipeType INSTANCE = new SculkPackageRecipeType();
	public static final ResourceLocation NAME = new ResourceLocation("packagedavaritia:sculk");
	public static final IntSet SLOTS;
	public static final List<ResourceLocation> CATEGORIES = List.of(new ResourceLocation("avaritia:sculk_craft"));
	public static final List<ResourceLocation> CATEGORIES_EMI = List.of(new ResourceLocation("avaritia:sculk_crafting_table"));
	public static final Vec3i COLOR = new Vec3i(139, 139, 139);
	public static final Vec3i COLOR_DISABLED = new Vec3i(64, 64, 64);

	static {
		SLOTS = new IntRBTreeSet();
		for(int i = 3; i < 6; ++i) {
			for(int j = 3; j < 6; ++j) {
				SLOTS.add(9*i+j);
			}
		}
	}

	protected SculkPackageRecipeType() {}

	@Override
	public ResourceLocation getName() {
		return NAME;
	}

	@Override
	public MutableComponent getDisplayName() {
		return Component.translatable("recipe.packagedavaritia.sculk");
	}

	@Override
	public MutableComponent getShortDisplayName() {
		return Component.translatable("recipe.packagedavaritia.sculk.short");
	}

	@Override
	public IPackageRecipeInfo getNewRecipeInfo() {
		return new SculkPackageRecipeInfo();
	}

	@Override
	public IntSet getEnabledSlots() {
		return SLOTS;
	}

	@Override
	public List<ResourceLocation> getJEICategories() {
		return CATEGORIES;
	}

	@Override
	public List<ResourceLocation> getEMICategories() {
		return CATEGORIES_EMI;
	}

	@Override
	public Int2ObjectMap<ItemStack> getRecipeTransferMap(IRecipeSlotsViewWrapper recipeLayoutWrapper) {
		Int2ObjectMap<ItemStack> map = new Int2ObjectOpenHashMap<>();
		List<IRecipeSlotViewWrapper> slotViews = recipeLayoutWrapper.getRecipeSlotViews();
		int width = 3;
		int height = 3;
		int[] slotArray = SLOTS.toIntArray();
		IntList slots = new IntArrayList(9);
		if(recipeLayoutWrapper.getRecipe() instanceof ShapedTableCraftingRecipe recipe) {
			width = recipe.getWidth();
			height = recipe.getHeight();
		}
		int widthOffset = (3-width)/2;
		int heightOffset = (3-height)/2;
		for(int i = heightOffset; i < heightOffset+height; ++i) {
			for(int j = widthOffset; j < widthOffset+width; ++j) {
				slots.add(slotArray[3*i+j]);
			}
		}
		int index = 0;
		slotArray = slots.toIntArray();
		for(IRecipeSlotViewWrapper slotView : slotViews) {
			if(slotView.isInput()) {
				Object displayed = slotView.getDisplayedIngredient().orElse(null);
				if(displayed instanceof ItemStack stack && !stack.isEmpty()) {
					map.put(slotArray[index], stack);
				}
				++index;
			}
			if(index >= slots.size()) {
				break;
			}
		}
		return map;
	}

	@Override
	public Object getRepresentation() {
		return new ItemStack(ModBlocks.sculk_crafting_table.get());
	}

	@Override
	public Vec3i getSlotColor(int slot) {
		if(!SLOTS.contains(slot) && slot != 81) {
			return COLOR_DISABLED;
		}
		return COLOR;
	}
}
