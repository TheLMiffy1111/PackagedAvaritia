package thelm.packagedavaritia.menu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.items.SlotItemHandler;
import thelm.packagedauto.menu.BaseMenu;
import thelm.packagedauto.menu.factory.PositionalBlockEntityMenuFactory;
import thelm.packagedauto.slot.RemoveOnlySlot;
import thelm.packagedavaritia.block.entity.NetherCrafterBlockEntity;
import thelm.packagedavaritia.slot.NetherCrafterRemoveOnlySlot;

public class NetherCrafterMenu extends BaseMenu<NetherCrafterBlockEntity> {

	public static final MenuType<NetherCrafterMenu> TYPE_INSTANCE = IForgeMenuType.create(new PositionalBlockEntityMenuFactory<>(NetherCrafterMenu::new));

	public NetherCrafterMenu(int windowId, Inventory inventory, NetherCrafterBlockEntity blockEntity) {
		super(TYPE_INSTANCE, windowId, inventory, blockEntity);
		addSlot(new SlotItemHandler(itemHandler, 26, 8, 71));
		for(int i = 0; i < 5; ++i) {
			for(int j = 0; j < 5; ++j) {
				addSlot(new NetherCrafterRemoveOnlySlot(blockEntity, i*5+j, 44+j*18, 17+i*18));
			}
		}
		addSlot(new RemoveOnlySlot(itemHandler, 25, 170, 53));
		setupPlayerInventory();
	}

	@Override
	public int getPlayerInvX() {
		return 19;
	}

	@Override
	public int getPlayerInvY() {
		return 120;
	}
}
