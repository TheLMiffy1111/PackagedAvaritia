package thelm.packagedavaritia.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockReader;
import thelm.packagedauto.block.BaseBlock;
import thelm.packagedavaritia.PackagedAvaritia;
import thelm.packagedavaritia.tile.ExtremeCrafterTile;

public class ExtremeCrafterBlock extends BaseBlock {

	public static final ExtremeCrafterBlock INSTANCE = new ExtremeCrafterBlock();
	public static final Item ITEM_INSTANCE = new BlockItem(INSTANCE, new Item.Properties().tab(PackagedAvaritia.ITEM_GROUP)).setRegistryName("packagedavaritia:extreme_crafter");

	public ExtremeCrafterBlock() {
		super(AbstractBlock.Properties.of(Material.METAL).strength(10F, 15F).sound(SoundType.METAL));
		setRegistryName("packagedavaritia:extreme_crafter");
	}

	@Override
	public ExtremeCrafterTile createTileEntity(BlockState state, IBlockReader worldIn) {
		return ExtremeCrafterTile.TYPE_INSTANCE.create();
	}
}
