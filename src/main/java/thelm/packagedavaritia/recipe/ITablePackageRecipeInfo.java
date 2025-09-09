package thelm.packagedavaritia.recipe;

import java.util.List;

import committee.nova.mods.avaritia.common.crafting.recipe.ITierCraftingRecipe;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import thelm.packagedauto.api.IPackageRecipeInfo;

public interface ITablePackageRecipeInfo extends IPackageRecipeInfo {

	int getTier();

	ItemStack getOutput();

	ITierCraftingRecipe getRecipe();

	Container getMatrix();

	List<ItemStack> getRemainingItems();

	@Override
	default List<ItemStack> getOutputs() {
		ItemStack output = getOutput();
		return output.isEmpty() ? List.of() : List.of(output);
	}
}
