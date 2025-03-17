package thelm.packagedavaritia.recipe;

import java.util.List;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import nova.committee.avaritia.common.crafting.recipe.ICraftRecipe;
import thelm.packagedauto.api.IPackageRecipeInfo;

public interface IExtremePackageRecipeInfo extends IPackageRecipeInfo {

	ItemStack getOutput();

	ICraftRecipe getRecipe();

	Container getMatrix();

	List<ItemStack> getRemainingItems();

	@Override
	default List<ItemStack> getOutputs() {
		ItemStack output = getOutput();
		return output.isEmpty() ? List.of() : List.of(output);
	}
}
