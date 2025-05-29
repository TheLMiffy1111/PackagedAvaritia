package thelm.packagedavaritia.recipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import committee.nova.mods.avaritia.api.common.crafting.TierInput;
import committee.nova.mods.avaritia.common.crafting.recipe.BaseTableCraftingRecipe;
import committee.nova.mods.avaritia.init.registry.ModRecipeTypes;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import thelm.packagedauto.api.IPackagePattern;
import thelm.packagedauto.api.IPackageRecipeType;
import thelm.packagedauto.util.MiscHelper;
import thelm.packagedauto.util.PackagePattern;

public class EndPackageRecipeInfo implements ITablePackageRecipeInfo {

	public static final MapCodec<EndPackageRecipeInfo> MAP_CODEC = RecordCodecBuilder.mapCodec(instance->instance.group(
			ResourceLocation.CODEC.fieldOf("id").forGetter(EndPackageRecipeInfo::getRecipeId),
			Codec.INT.fieldOf("width").forGetter(EndPackageRecipeInfo::getMatrixWidth),
			Codec.INT.fieldOf("height").forGetter(EndPackageRecipeInfo::getMatrixHeight),
			ItemStack.OPTIONAL_CODEC.orElse(ItemStack.EMPTY).sizeLimitedListOf(49).fieldOf("input").forGetter(EndPackageRecipeInfo::getMatrixAsList)).
			apply(instance, EndPackageRecipeInfo::new));
	public static final Codec<EndPackageRecipeInfo> CODEC = MAP_CODEC.codec();
	public static final StreamCodec<RegistryFriendlyByteBuf, EndPackageRecipeInfo> STREAM_CODEC = StreamCodec.composite(
			ResourceLocation.STREAM_CODEC, EndPackageRecipeInfo::getRecipeId,
			ByteBufCodecs.INT, EndPackageRecipeInfo::getMatrixWidth,
			ByteBufCodecs.INT, EndPackageRecipeInfo::getMatrixHeight,
			ItemStack.OPTIONAL_LIST_STREAM_CODEC, EndPackageRecipeInfo::getMatrixAsList,
			EndPackageRecipeInfo::new);

	private final ResourceLocation id;
	private final BaseTableCraftingRecipe recipe;
	private final List<ItemStack> input;
	private final TierInput matrix;
	private final ItemStack output;
	private final List<IPackagePattern> patterns = new ArrayList<>();

	public EndPackageRecipeInfo(ResourceLocation id, int width, int height, List<ItemStack> matrixSer) {
		this.id = id;
		matrix = TierInput.of(width, height, matrixSer, 3);
		input = MiscHelper.INSTANCE.condenseStacks(matrix.items());
		for(int i = 0; i*9 < input.size(); ++i) {
			patterns.add(new PackagePattern(this, i));
		}
		Recipe<?> recipeSer = MiscHelper.INSTANCE.getRecipeManager().byKey(id).map(RecipeHolder::value).orElse(null);
		if(recipeSer instanceof BaseTableCraftingRecipe tableRecipe) {
			recipe = tableRecipe;
			output = recipe.assemble(matrix, MiscHelper.INSTANCE.getRegistryAccess()).copy();
		}
		else {
			recipe = null;
			output = ItemStack.EMPTY;
		}
	}

	public EndPackageRecipeInfo(List<ItemStack> inputs, Level level) {
		NonNullList<ItemStack> matrixList = NonNullList.withSize(49, ItemStack.EMPTY);
		int[] slotArray = EndPackageRecipeType.SLOTS.toIntArray();
		for(int i = 0; i < 49; ++i) {
			ItemStack toSet = inputs.get(slotArray[i]);
			toSet.setCount(1);
			matrixList.set(i, toSet.copy());
		}
		matrix = TierInput.of(7, 7, matrixList, 3);
		RecipeHolder<BaseTableCraftingRecipe> recipeHolder = MiscHelper.INSTANCE.getRecipeManager().getRecipeFor(ModRecipeTypes.CRAFTING_TABLE_RECIPE.get(), matrix, level).orElse(null);
		if(recipeHolder != null) {
			id = recipeHolder.id();
			recipe = recipeHolder.value();
			output = recipe.assemble(matrix, level.registryAccess()).copy();
		}
		else {
			id = null;
			recipe = null;
			output = null;
		}
		input = MiscHelper.INSTANCE.condenseStacks(matrix.items());
		for(int i = 0; i*9 < input.size(); ++i) {
			this.patterns.add(new PackagePattern(this, i));
		}
	}

	@Override
	public IPackageRecipeType getRecipeType() {
		return EndPackageRecipeType.INSTANCE;
	}

	@Override
	public int getTier() {
		return 3;
	}

	@Override
	public boolean isValid() {
		return id != null && recipe != null;
	}

	@Override
	public List<IPackagePattern> getPatterns() {
		return Collections.unmodifiableList(patterns);
	}

	@Override
	public List<ItemStack> getInputs() {
		return Collections.unmodifiableList(input);
	}

	@Override
	public ItemStack getOutput() {
		return output.copy();
	}

	@Override
	public BaseTableCraftingRecipe getRecipe() {
		return recipe;
	}

	public ResourceLocation getRecipeId() {
		return id;
	}

	@Override
	public TierInput getMatrix() {
		return matrix;
	}

	public List<ItemStack> getMatrixAsList() {
		return Collections.unmodifiableList(matrix.items());
	}

	public int getMatrixWidth() {
		return matrix.width();
	}

	public int getMatrixHeight() {
		return matrix.height();
	}

	@Override
	public List<ItemStack> getRemainingItems() {
		return recipe.getRemainingItems(matrix);
	}

	@Override
	public Int2ObjectMap<ItemStack> getEncoderStacks() {
		Int2ObjectMap<ItemStack> map = new Int2ObjectOpenHashMap<>();
		int[] slotArray = EndPackageRecipeType.SLOTS.toIntArray();
		for(int i = 0; i < 49; ++i) {
			map.put(slotArray[i], matrix.getItem(i));
		}
		return map;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof EndPackageRecipeInfo other) {
			return MiscHelper.INSTANCE.recipeEquals(this, recipe, other, other.recipe);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return MiscHelper.INSTANCE.recipeHashCode(this, recipe);
	}
}
