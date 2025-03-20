package thelm.packagedavaritia.container;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.items.SlotItemHandler;
import thelm.packagedauto.container.BaseContainer;
import thelm.packagedauto.container.factory.PositionalTileContainerFactory;
import thelm.packagedauto.slot.RemoveOnlySlot;
import thelm.packagedavaritia.slot.EnderCrafterRemoveOnlySlot;
import thelm.packagedavaritia.tile.EnderCrafterTile;

public class EnderCrafterContainer extends BaseContainer<EnderCrafterTile> {

	public static final ContainerType<EnderCrafterContainer> TYPE_INSTANCE = (ContainerType<EnderCrafterContainer>)IForgeContainerType.
			create(new PositionalTileContainerFactory<>(EnderCrafterContainer::new)).
			setRegistryName("packagedavaritia:ender_crafter");

	public EnderCrafterContainer(int windowId, PlayerInventory playerInventory, EnderCrafterTile tile) {
		super(TYPE_INSTANCE, windowId, playerInventory, tile);
		addSlot(new SlotItemHandler(itemHandler, 50, 8, 89));
		for(int i = 0; i < 7; ++i) {
			for(int j = 0; j < 7; ++j) {
				addSlot(new EnderCrafterRemoveOnlySlot(tile, i*7+j, 44+j*18, 17+i*18));
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
