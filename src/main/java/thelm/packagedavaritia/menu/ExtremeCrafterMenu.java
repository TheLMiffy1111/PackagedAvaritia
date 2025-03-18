package thelm.packagedavaritia.menu;

import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.items.SlotItemHandler;
import thelm.packagedauto.menu.BaseMenu;
import thelm.packagedauto.slot.RemoveOnlySlot;
import thelm.packagedavaritia.block.entity.ExtremeCrafterBlockEntity;
import thelm.packagedavaritia.slot.ExtremeCrafterRemoveOnlySlot;

public class ExtremeCrafterMenu extends BaseMenu<ExtremeCrafterBlockEntity> {

	public ExtremeCrafterMenu(int windowId, Inventory inventory, ExtremeCrafterBlockEntity blockEntity) {
		super(PackagedAvaritiaMenus.EXTREME_CRAFTER.get(), windowId, inventory, blockEntity);
		addSlot(new SlotItemHandler(itemHandler, 82, 8, 107));
		for(int i = 0; i < 9; ++i) {
			for(int j = 0; j < 9; ++j) {
				addSlot(new ExtremeCrafterRemoveOnlySlot(blockEntity, i*9+j, 44+j*18, 17+i*18));
			}
		}
		addSlot(new RemoveOnlySlot(itemHandler, 81, 242, 89));
		setupPlayerInventory();
	}

	@Override
	public int getPlayerInvX() {
		return 55;
	}

	@Override
	public int getPlayerInvY() {
		return 192;
	}
}
