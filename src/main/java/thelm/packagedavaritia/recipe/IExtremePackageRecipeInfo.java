package thelm.packagedavaritia.recipe;

import java.util.Collections;
import java.util.List;

import com.yuo.endless.Recipe.IExtremeCraftRecipe;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import thelm.packagedauto.api.IPackageRecipeInfo;

public interface IExtremePackageRecipeInfo extends IPackageRecipeInfo {

	int getTier();

	ItemStack getOutput();

	IExtremeCraftRecipe getRecipe();

	IInventory getMatrix();

	List<ItemStack> getRemainingItems();

	@Override
	default List<ItemStack> getOutputs() {
		ItemStack output = getOutput();
		return output.isEmpty() ? Collections.emptyList() : Collections.singletonList(output);
	}
}
