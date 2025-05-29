package thelm.packagedavaritia.recipe;

import java.util.List;

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

public class EndPackageRecipeType implements IPackageRecipeType {

	public static final EndPackageRecipeType INSTANCE = new EndPackageRecipeType();
	public static final ResourceLocation NAME = ResourceLocation.parse("packagedavaritia:end");
	public static final IntSet SLOTS;
	public static final List<ResourceLocation> CATEGORIES = List.of(ResourceLocation.parse("avaritia:end_craft"));
	public static final Vec3i COLOR = new Vec3i(139, 139, 139);
	public static final Vec3i COLOR_DISABLED = new Vec3i(64, 64, 64);

	static {
		SLOTS = new IntRBTreeSet();
		for(int i = 1; i < 8; ++i) {
			for(int j = 1; j < 8; ++j) {
				SLOTS.add(9*i+j);
			}
		}
	}

	protected EndPackageRecipeType() {}

	@Override
	public ResourceLocation getName() {
		return NAME;
	}

	@Override
	public MutableComponent getDisplayName() {
		return Component.translatable("recipe.packagedavaritia.end");
	}

	@Override
	public MutableComponent getShortDisplayName() {
		return Component.translatable("recipe.packagedavaritia.end.short");
	}

	@Override
	public MapCodec<? extends IPackageRecipeInfo> getRecipeInfoMapCodec() {
		return EndPackageRecipeInfo.MAP_CODEC;
	}

	@Override
	public Codec<? extends IPackageRecipeInfo> getRecipeInfoCodec() {
		return EndPackageRecipeInfo.CODEC;
	}

	@Override
	public StreamCodec<RegistryFriendlyByteBuf, ? extends IPackageRecipeInfo> getRecipeInfoStreamCodec() {
		return EndPackageRecipeInfo.STREAM_CODEC;
	}

	@Override
	public IPackageRecipeInfo generateRecipeInfoFromStacks(List<ItemStack> inputs, List<ItemStack> outputs, Level level) {
		return new EndPackageRecipeInfo(inputs, level);
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
		int width = 7;
		int height = 7;
		int[] slotArray = SLOTS.toIntArray();
		IntList slots = new IntArrayList(49);
		if(recipeLayoutWrapper.getRecipe() instanceof ShapedTableCraftingRecipe recipe) {
			width = recipe.getWidth();
			height = recipe.getHeight();
		}
		int widthOffset = (7-width)/2;
		int heightOffset = (7-height)/2;
		for(int i = heightOffset; i < heightOffset+height; ++i) {
			for(int j = widthOffset; j < widthOffset+width; ++j) {
				slots.add(slotArray[7*i+j]);
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
		return new ItemStack(ModBlocks.end_crafting_table.get());
	}

	@Override
	public Vec3i getSlotColor(int slot) {
		if(!SLOTS.contains(slot) && slot != 81) {
			return COLOR_DISABLED;
		}
		return COLOR;
	}
}
