package thelm.packagedavaritia.block;

import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class PackagedAvaritiaBlocks {

	private PackagedAvaritiaBlocks() {}

	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks("packagedavaritia");

	public static final DeferredBlock<Block> EXTREME_CRAFTER = BLOCKS.register("extreme_crafter", ExtremeCrafterBlock::new);
}
