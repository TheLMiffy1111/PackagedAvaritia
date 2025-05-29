package thelm.packagedavaritia.recipe;

import java.util.List;
import java.util.stream.IntStream;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;

import committee.nova.mods.avaritia.common.crafting.recipe.ShapedTableCraftingRecipe;
import committee.nova.mods.avaritia.init.registry.ModBlocks;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntRBTreeSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import net.minecraft.core.Vec3i;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import thelm.packagedauto.api.IPackageRecipeInfo;
import thelm.packagedauto.api.IPackageRecipeType;
import thelm.packagedauto.api.IRecipeSlotViewWrapper;
import thelm.packagedauto.api.IRecipeSlotsViewWrapper;

public class ExtremePackageRecipeType implements IPackageRecipeType {

	public static final ExtremePackageRecipeType INSTANCE = new ExtremePackageRecipeType();
	public static final ResourceLocation NAME = ResourceLocation.parse("packagedavaritia:extreme");
	public static final IntSet SLOTS;
	public static final List<ResourceLocation> CATEGORIES = List.of(ResourceLocation.parse("avaritia:extreme_craft"));
	public static final Vec3i COLOR = new Vec3i(139, 139, 139);
	public static final Vec3i COLOR_DISABLED = new Vec3i(64, 64, 64);

	static {
		SLOTS = new IntRBTreeSet();
		IntStream.range(0, 81).forEachOrdered(SLOTS::add);
	}

	protected ExtremePackageRecipeType() {}

	@Override
	public ResourceLocation getName() {
		return NAME;
	}

	@Override
	public MutableComponent getDisplayName() {
		return Component.translatable("recipe.packagedavaritia.extreme");
	}

	@Override
	public MutableComponent getShortDisplayName() {
		return Component.translatable("recipe.packagedavaritia.extreme.short");
	}

	@Override
	public MapCodec<? extends IPackageRecipeInfo> getRecipeInfoMapCodec() {
		return ExtremePackageRecipeInfo.MAP_CODEC;
	}

	@Override
	public Codec<? extends IPackageRecipeInfo> getRecipeInfoCodec() {
		return ExtremePackageRecipeInfo.CODEC;
	}

	@Override
	public StreamCodec<RegistryFriendlyByteBuf, ? extends IPackageRecipeInfo> getRecipeInfoStreamCodec() {
		return ExtremePackageRecipeInfo.STREAM_CODEC;
	}

	@Override
	public IPackageRecipeInfo generateRecipeInfoFromStacks(List<ItemStack> inputs, List<ItemStack> outputs, Level level) {
		return new ExtremePackageRecipeInfo(inputs, level);
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
	public Int2ObjectMap<ItemStack> getRecipeTransferMap(IRecipeSlotsViewWrapper recipeLayoutWrapper) {
		Int2ObjectMap<ItemStack> map = new Int2ObjectOpenHashMap<>();
		List<IRecipeSlotViewWrapper> slotViews = recipeLayoutWrapper.getRecipeSlotViews();
		int width = 9;
		int height = 9;
		IntList slots = new IntArrayList(81);
		if(recipeLayoutWrapper.getRecipe() instanceof ShapedTableCraftingRecipe recipe) {
			width = recipe.getWidth();
			height = recipe.getHeight();
		}
		int widthOffset = (9-width)/2;
		int heightOffset = (9-height)/2;
		for(int i = heightOffset; i < heightOffset+height; ++i) {
			for(int j = widthOffset; j < widthOffset+width; ++j) {
				slots.add(9*i+j);
			}
		}
		int index = 0;
		int[] slotArray = slots.toIntArray();
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
		return new ItemStack(ModBlocks.extreme_crafting_table.get());
	}

	@Override
	public Vec3i getSlotColor(int slot) {
		if(slot >= 81 && slot != 81 && slot < 90) {
			return COLOR_DISABLED;
		}
		return COLOR;
	}
}
