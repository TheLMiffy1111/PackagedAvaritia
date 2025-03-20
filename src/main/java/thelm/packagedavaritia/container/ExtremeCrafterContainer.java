package thelm.packagedavaritia.container;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.items.SlotItemHandler;
import thelm.packagedauto.container.BaseContainer;
import thelm.packagedauto.container.factory.PositionalTileContainerFactory;
import thelm.packagedauto.slot.RemoveOnlySlot;
import thelm.packagedavaritia.slot.ExtremeCrafterRemoveOnlySlot;
import thelm.packagedavaritia.tile.ExtremeCrafterTile;

public class ExtremeCrafterContainer extends BaseContainer<ExtremeCrafterTile> {

	public static final ContainerType<ExtremeCrafterContainer> TYPE_INSTANCE = (ContainerType<ExtremeCrafterContainer>)IForgeContainerType.
			create(new PositionalTileContainerFactory<>(ExtremeCrafterContainer::new)).
			setRegistryName("packagedavaritia:extreme_crafter");

	public ExtremeCrafterContainer(int windowId, PlayerInventory playerInventory, ExtremeCrafterTile tile) {
		super(TYPE_INSTANCE, windowId, playerInventory, tile);
		addSlot(new SlotItemHandler(itemHandler, 82, 8, 107));
		for(int i = 0; i < 9; ++i) {
			for(int j = 0; j < 9; ++j) {
				addSlot(new ExtremeCrafterRemoveOnlySlot(tile, i*9+j, 44+j*18, 17+i*18));
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
