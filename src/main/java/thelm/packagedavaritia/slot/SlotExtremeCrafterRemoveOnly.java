package thelm.packagedavaritia.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thelm.packagedauto.slot.SlotBase;
import thelm.packagedavaritia.tile.TileExtremeCrafter;

//Code from CoFHCore
public class SlotExtremeCrafterRemoveOnly extends SlotBase {

	public final TileExtremeCrafter tile;

	public SlotExtremeCrafterRemoveOnly(TileExtremeCrafter tile, int index, int x, int y) {
		super(tile.getInventory(), index, x, y);
		this.tile = tile;
	}

	@Override
	public boolean canTakeStack(EntityPlayer playerIn) {
		return !tile.isWorking;
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return false;
	}
}