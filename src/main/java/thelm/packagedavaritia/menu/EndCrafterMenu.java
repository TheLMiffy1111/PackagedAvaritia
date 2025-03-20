package thelm.packagedavaritia.menu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.items.SlotItemHandler;
import thelm.packagedauto.menu.BaseMenu;
import thelm.packagedauto.menu.factory.PositionalBlockEntityMenuFactory;
import thelm.packagedauto.slot.RemoveOnlySlot;
import thelm.packagedavaritia.block.entity.EndCrafterBlockEntity;
import thelm.packagedavaritia.slot.EndCrafterRemoveOnlySlot;

public class EndCrafterMenu extends BaseMenu<EndCrafterBlockEntity> {

	public static final MenuType<EndCrafterMenu> TYPE_INSTANCE = IForgeMenuType.create(new PositionalBlockEntityMenuFactory<>(EndCrafterMenu::new));

	public EndCrafterMenu(int windowId, Inventory inventory, EndCrafterBlockEntity blockEntity) {
		super(TYPE_INSTANCE, windowId, inventory, blockEntity);
		addSlot(new SlotItemHandler(itemHandler, 50, 8, 89));
		for(int i = 0; i < 7; ++i) {
			for(int j = 0; j < 7; ++j) {
				addSlot(new EndCrafterRemoveOnlySlot(blockEntity, i*7+j, 44+j*18, 17+i*18));
			}
		}
		addSlot(new RemoveOnlySlot(itemHandler, 49, 206, 71));
		setupPlayerInventory();
	}

	@Override
	public int getPlayerInvX() {
		return 37;
	}

	@Override
	public int getPlayerInvY() {
		return 156;
	}
}
