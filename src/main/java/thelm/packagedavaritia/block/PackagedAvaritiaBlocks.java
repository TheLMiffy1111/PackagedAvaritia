package thelm.packagedavaritia.block;

import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class PackagedAvaritiaBlocks {

	private PackagedAvaritiaBlocks() {}

	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks("packagedavaritia");

	public static final DeferredBlock<Block> SCULK_CRAFTER = BLOCKS.register("sculk_crafter", SculkCrafterBlock::new);
	public static final DeferredBlock<Block> NETHER_CRAFTER = BLOCKS.register("nether_crafter", NetherCrafterBlock::new);
	public static final DeferredBlock<Block> END_CRAFTER = BLOCKS.register("end_crafter", EndCrafterBlock::new);
	public static final DeferredBlock<Block> EXTREME_CRAFTER = BLOCKS.register("extreme_crafter", ExtremeCrafterBlock::new);
}
