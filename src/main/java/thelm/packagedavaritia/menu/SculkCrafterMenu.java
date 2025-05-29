package thelm.packagedavaritia.menu;

import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.items.SlotItemHandler;
import thelm.packagedauto.menu.BaseMenu;
import thelm.packagedauto.slot.RemoveOnlySlot;
import thelm.packagedavaritia.block.entity.SculkCrafterBlockEntity;
import thelm.packagedavaritia.slot.SculkCrafterRemoveOnlySlot;

public class SculkCrafterMenu extends BaseMenu<SculkCrafterBlockEntity> {

	public SculkCrafterMenu(int windowId, Inventory inventory, SculkCrafterBlockEntity blockEntity) {
		super(PackagedAvaritiaMenus.SCULK_CRAFTER.get(), windowId, inventory, blockEntity);
		addSlot(new SlotItemHandler(itemHandler, 10, 8, 53));
		for(int i = 0; i < 3; ++i) {
			for(int j = 0; j < 3; ++j) {
				addSlot(new SculkCrafterRemoveOnlySlot(blockEntity, i*3+j, 44+j*18, 17+i*18));
			}
		}
		addSlot(new RemoveOnlySlot(itemHandler, 9, 134, 35));
		setupPlayerInventory();
	}
}
