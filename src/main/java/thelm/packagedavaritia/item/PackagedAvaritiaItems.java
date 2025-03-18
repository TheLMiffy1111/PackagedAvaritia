package thelm.packagedavaritia.item;

import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import thelm.packagedavaritia.block.PackagedAvaritiaBlocks;

public class PackagedAvaritiaItems {

	private PackagedAvaritiaItems() {}

	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems("packagedavaritia");

	public static final DeferredItem<?> EXTREME_CRAFTER = ITEMS.registerSimpleBlockItem(PackagedAvaritiaBlocks.EXTREME_CRAFTER);
}
