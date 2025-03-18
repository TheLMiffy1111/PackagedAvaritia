package thelm.packagedavaritia.recipe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.yuo.endless.Recipe.ExtremeCraftShpaelessManager;
import com.yuo.endless.Recipe.ExtremeCraftingManager;
import com.yuo.endless.Recipe.IExtremeCraftRecipe;
import com.yuo.endless.Recipe.ModRecipeManager;
import com.yuo.endless.Recipe.RecipeTypeRegistry;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import thelm.packagedauto.api.IPackagePattern;
import thelm.packagedauto.api.IPackageRecipeType;
import thelm.packagedauto.util.MiscHelper;
import thelm.packagedauto.util.PackagePattern;

public class ExtremePackageRecipeInfo implements IExtremePackageRecipeInfo {

	IExtremeCraftRecipe recipe;
	List<ItemStack> input = new ArrayList<>();
	IInventory matrix = new Inventory(81);
	ItemStack output = ItemStack.EMPTY;
	List<IPackagePattern> patterns = new ArrayList<>();

	@Override
	public void read(CompoundNBT nbt) {
		input.clear();
		output = ItemStack.EMPTY;
		patterns.clear();
		IRecipe<?> recipe = getRecipe(new ResourceLocation(nbt.getString("Recipe")));
		List<ItemStack> matrixList = new ArrayList<>();
		MiscHelper.INSTANCE.loadAllItems(nbt.getList("Matrix", 10), matrixList);
		for(int i = 0; i < 81 && i < matrixList.size(); ++i) {
			matrix.setItem(i, matrixList.get(i));
		}
		if(recipe instanceof IExtremeCraftRecipe) {
			this.recipe = (IExtremeCraftRecipe)recipe;
			output = this.recipe.assemble(matrix).copy();
		}
		input.addAll(MiscHelper.INSTANCE.condenseStacks(matrix));
		for(int i = 0; i*9 < input.size(); ++i) {
			patterns.add(new PackagePattern(this, i));
		}
	}

	@Override
	public CompoundNBT write(CompoundNBT nbt) {
		if(recipe != null) {
			nbt.putString("Recipe", recipe.getId().toString());
		}
		List<ItemStack> matrixList = new ArrayList<>();
		for(int i = 0; i < 81; ++i) {
			matrixList.add(matrix.getItem(i));
		}
		ListNBT matrixTag = MiscHelper.INSTANCE.saveAllItems(new ListNBT(), matrixList);
		nbt.put("Matrix", matrixTag);
		return nbt;
	}

	@Override
	public IPackageRecipeType getRecipeType() {
		return ExtremePackageRecipeType.INSTANCE;
	}

	@Override
	public boolean isValid() {
		return recipe != null;
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
	public IExtremeCraftRecipe getRecipe() {
		return recipe;
	}

	@Override
	public IInventory getMatrix() {
		return matrix;
	}

	@Override
	public List<ItemStack> getRemainingItems() {
		return recipe.getRemainingItems(matrix);
	}

	@Override
	public void generateFromStacks(List<ItemStack> input, List<ItemStack> output, World world) {
		recipe = null;
		this.input.clear();
		patterns.clear();
		for(int i = 0; i < 81; ++i) {
			ItemStack toSet = input.get(i);
			toSet.setCount(1);
			matrix.setItem(i, toSet.copy());
		}
		IExtremeCraftRecipe recipe = getRecipe(matrix, world);
		if(recipe != null) {
			this.recipe = recipe;
			this.input.addAll(MiscHelper.INSTANCE.condenseStacks(matrix));
			this.output = recipe.assemble(matrix).copy();
			for(int i = 0; i*9 < this.input.size(); ++i) {
				patterns.add(new PackagePattern(this, i));
			}
			return;
		}
		matrix.clearContent();
	}

	@Override
	public Int2ObjectMap<ItemStack> getEncoderStacks() {
		Int2ObjectMap<ItemStack> map = new Int2ObjectOpenHashMap<>();
		for(int i = 0; i < 81; ++i) {
			map.put(i, matrix.getItem(i));
		}
		return map;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ExtremePackageRecipeInfo) {
			ExtremePackageRecipeInfo other = (ExtremePackageRecipeInfo)obj;
			return MiscHelper.INSTANCE.recipeEquals(this, recipe, other, other.recipe);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return MiscHelper.INSTANCE.recipeHashCode(this, recipe);
	}

	// What are you doing
	public static IExtremeCraftRecipe getRecipe(ResourceLocation id) {
		RecipeManager recipeManager = MiscHelper.INSTANCE.getRecipeManager();
		IRecipe<?> recipe = null;
		{
			recipe = recipeManager.byKey(id).orElse(null);
		}
		if(recipe == null) {
			try {
				Field[] fields = ModRecipeManager.class.getFields();
				for(Field field : fields) {
					if(IRecipe.class.isAssignableFrom(field.getType())) {
						IRecipe<?> fieldRecipe = (IRecipe<?>)field.get(null);
						if(fieldRecipe.getId().equals(id)) {
							recipe = fieldRecipe;
						}
					}
				}
			}
			catch(Exception e) {}
		}
		if(recipe == null) {
			recipe = ExtremeCraftingManager.getInstance().getRecipeList().stream().
					filter(r->r.getId().equals(id)).findFirst().orElse(null);
		}
		if(recipe == null) {
			recipe = ExtremeCraftShpaelessManager.getInstance().getRecipeList().stream().
					filter(r->r.getId().equals(id)).findFirst().orElse(null);
		}
		if(recipe instanceof IExtremeCraftRecipe) {
			return (IExtremeCraftRecipe)recipe;
		}
		return null;
	}

	// What are you doing
	public static IExtremeCraftRecipe getRecipe(IInventory matrix, World world) {
		RecipeManager recipeManager = MiscHelper.INSTANCE.getRecipeManager();
		IExtremeCraftRecipe recipe = null;
		{
			recipe = recipeManager.getRecipeFor(RecipeTypeRegistry.EXTREME_CRAFT_RECIPE, matrix, world).orElse(null);
		}
		if(recipe == null) {
			recipe = recipeManager.getRecipeFor(RecipeTypeRegistry.EXTREME_CRAFT_SHAPE_RECIPE, matrix, world).orElse(null);
		}
		if(recipe == null) {
			recipe = ModRecipeManager.matchesRecipe(matrix, world);
		}
		if(recipe == null) {
			recipe = ExtremeCraftingManager.getInstance().getRecipeList().stream().
					filter(r->r.matches(matrix, world)).findFirst().orElse(null);
		}
		if(recipe == null) {
			recipe = ExtremeCraftShpaelessManager.getInstance().getRecipeList().stream().
					filter(r->r.matches(matrix, world)).findFirst().orElse(null);
		}
		return recipe;
	}
}
