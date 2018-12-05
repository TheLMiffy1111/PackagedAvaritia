package thelm.packagedavaritia.recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

import morph.avaritia.recipe.AvaritiaRecipeManager;
import morph.avaritia.recipe.extreme.IExtremeRecipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.IngredientNBT;
import thelm.packagedauto.api.IPackagePattern;
import thelm.packagedauto.api.IRecipeType;
import thelm.packagedauto.api.MiscUtil;
import thelm.packagedauto.util.PatternHelper;

public class RecipeInfoExtreme implements IRecipeInfoExtreme {

	IExtremeRecipe recipe;
	List<ItemStack> input = new ArrayList<>();
	InventoryCrafting matrix = new InventoryCrafting(new Container() {@Override public boolean canInteractWith(EntityPlayer playerIn) {return false;}}, 9, 9);
	ItemStack output;
	List<IPackagePattern> patterns = new ArrayList<>();

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		input.clear();
		output = ItemStack.EMPTY;
		patterns.clear();
		recipe = AvaritiaRecipeManager.EXTREME_RECIPES.get(new ResourceLocation(nbt.getString("Recipe")));
		List<ItemStack> matrixList = new ArrayList<>();
		MiscUtil.loadAllItems(nbt.getTagList("Matrix", 10), matrixList);
		for(int i = 0; i < 81 && i < matrixList.size(); ++i) {
			matrix.setInventorySlotContents(i, matrixList.get(i));
		}
		if(recipe != null) {
			MiscUtil.loadAllItems(nbt.getTagList("Input", 10), input);
			output = recipe.getRecipeOutput().copy();
			for(int i = 0; i*9 < input.size(); ++i) {
				patterns.add(new PatternHelper(this, i));
			}
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		if(recipe != null) {
			nbt.setString("Recipe", recipe.getRegistryName().toString());
		}
		NBTTagList inputTag = MiscUtil.saveAllItems(new NBTTagList(), input);
		nbt.setTag("Input", inputTag);
		List<ItemStack> matrixList = new ArrayList<>();
		for(int i = 0; i < 81; ++i) {
			matrixList.add(matrix.getStackInSlot(i));
		}
		NBTTagList matrixTag = MiscUtil.saveAllItems(new NBTTagList(), matrixList);
		nbt.setTag("Matrix", matrixTag);
		return nbt;
	}

	@Override
	public IRecipeType getRecipeType() {
		return RecipeTypeExtreme.INSTANCE;
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
	public List<ItemStack> getOutputs() {
		return Collections.singletonList(output);
	}

	@Override
	public ItemStack getOutput() {
		return output.copy();
	}

	@Override
	public IExtremeRecipe getRecipe() {
		return recipe;
	}

	@Override
	public InventoryCrafting getMatrix() {
		return matrix;
	}

	@Override
	public void generateFromStacks(List<ItemStack> input, List<ItemStack> output) {
		recipe = null;
		this.input.clear();
		patterns.clear();
		for(int i = 0; i < 81; ++i) {
			ItemStack toSet = input.get(i);
			toSet.setCount(1);
			matrix.setInventorySlotContents(i, toSet.copy());
		}
		for(IExtremeRecipe recipe : AvaritiaRecipeManager.EXTREME_RECIPES.values()) {
			if(recipe.matches(matrix, null)) {
				this.recipe = recipe;
				this.input.addAll(MiscUtil.condenseStacks(input));
				this.output = recipe.getRecipeOutput().copy();
				for(int i = 0; i*9 < this.input.size(); ++i) {
					patterns.add(new PatternHelper(this, i));
				}
				break;
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof RecipeInfoExtreme) {
			RecipeInfoExtreme other = (RecipeInfoExtreme)obj;
			for(int i = 0; i < input.size(); ++i) {
				if(!ItemStack.areItemStacksEqualUsingNBTShareTag(input.get(i), other.input.get(i))) {
					return false;
				}
			}
			return recipe.equals(other.recipe);
		}
		return false;
	}

	@Override
	public int hashCode() {
		Object[] toHash = new Object[2];
		Object[] inputArray = new Object[input.size()];
		for(int i = 0; i < input.size(); ++i) {
			ItemStack stack = input.get(i);
			inputArray[i] = new Object[] {stack.getItem(), stack.getItemDamage(), stack.getCount(), stack.getTagCompound()};
		}
		toHash[0] = recipe;
		toHash[1] = inputArray;
		return Arrays.deepHashCode(toHash);
	}
}
