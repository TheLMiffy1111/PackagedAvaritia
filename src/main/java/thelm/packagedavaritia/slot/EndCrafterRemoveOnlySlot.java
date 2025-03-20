package thelm.packagedavaritia.slot;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;
import thelm.packagedavaritia.block.entity.EndCrafterBlockEntity;

//Code from CoFHCore
public class EndCrafterRemoveOnlySlot extends SlotItemHandler {

	public final EndCrafterBlockEntity blockEntity;

	public EndCrafterRemoveOnlySlot(EndCrafterBlockEntity blockEntity, int index, int x, int y) {
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
