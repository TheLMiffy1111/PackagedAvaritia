package thelm.packagedavaritia.item;

import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import thelm.packagedavaritia.block.PackagedAvaritiaBlocks;

public class PackagedAvaritiaItems {

	private PackagedAvaritiaItems() {}

	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems("packagedavaritia");

	public static final DeferredItem<?> SCULK_CRAFTER = ITEMS.registerSimpleBlockItem(PackagedAvaritiaBlocks.SCULK_CRAFTER);
	public static final DeferredItem<?> NETHER_CRAFTER = ITEMS.registerSimpleBlockItem(PackagedAvaritiaBlocks.NETHER_CRAFTER);
	public static final DeferredItem<?> END_CRAFTER = ITEMS.registerSimpleBlockItem(PackagedAvaritiaBlocks.END_CRAFTER);
	public static final DeferredItem<?> EXTREME_CRAFTER = ITEMS.registerSimpleBlockItem(PackagedAvaritiaBlocks.EXTREME_CRAFTER);
}
