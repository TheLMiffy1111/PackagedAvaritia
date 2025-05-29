package thelm.packagedavaritia.slot;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.SlotItemHandler;
import thelm.packagedavaritia.block.entity.NetherCrafterBlockEntity;

//Code from CoFHCore
public class NetherCrafterRemoveOnlySlot extends SlotItemHandler {

	public final NetherCrafterBlockEntity blockEntity;

	public NetherCrafterRemoveOnlySlot(NetherCrafterBlockEntity blockEntity, int index, int x, int y) {
		super(blockEntity.getItemHandler(), index, x, y);
		this.blockEntity = blockEntity;
	}

	@Override
	public boolean mayPickup(Player player) {
		return !blockEntity.isWorking;
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return false;
	}
}
