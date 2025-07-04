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
import thelm.packagedavaritia.tile.EnderCrafterTile;

public class EnderCrafterBlock extends BaseBlock {

	public static final EnderCrafterBlock INSTANCE = new EnderCrafterBlock();
	public static final Item ITEM_INSTANCE = new BlockItem(INSTANCE, new Item.Properties().tab(PackagedAvaritia.ITEM_GROUP)).setRegistryName("packagedavaritia:ender_crafter");

	public EnderCrafterBlock() {
		super(AbstractBlock.Properties.of(Material.METAL).strength(10F, 15F).sound(SoundType.METAL));
		setRegistryName("packagedavaritia:ender_crafter");
	}

	@Override
	public EnderCrafterTile createTileEntity(BlockState state, IBlockReader worldIn) {
		return EnderCrafterTile.TYPE_INSTANCE.create();
	}
}
