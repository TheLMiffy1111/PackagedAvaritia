package thelm.packagedavaritia.recipe;

import java.util.List;

import net.byAqua3.avaritia.recipe.RecipeExtremeCrafting;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import thelm.packagedauto.api.IPackageRecipeInfo;

public interface IExtremePackageRecipeInfo extends IPackageRecipeInfo {

	ItemStack getOutput();

	RecipeExtremeCrafting getRecipe();

	CraftingInput getMatrix();

	List<ItemStack> getRemainingItems();

	@Override
	default List<ItemStack> getOutputs() {
		ItemStack output = getOutput();
		return output.isEmpty() ? List.of() : List.of(output);
	}
}
