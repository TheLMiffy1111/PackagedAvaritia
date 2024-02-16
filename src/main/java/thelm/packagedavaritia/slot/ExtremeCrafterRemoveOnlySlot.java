package thelm.packagedavaritia.slot;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;
import thelm.packagedavaritia.tile.ExtremeCrafterTile;

//Code from CoFHCore
public class ExtremeCrafterRemoveOnlySlot extends SlotItemHandler {

	public final ExtremeCrafterTile tile;

	public ExtremeCrafterRemoveOnlySlot(ExtremeCrafterTile tile, int index, int x, int y) {
		super(tile.getItemHandler(), index, x, y);
		this.tile = tile;
	}

	@Override
	public boolean mayPickup(PlayerEntity playerIn) {
		return !tile.isWorking;
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return false;
	}
}
