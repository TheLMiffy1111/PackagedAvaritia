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
import thelm.packagedavaritia.tile.NetherCrafterTile;

public class NetherCrafterBlock extends BaseBlock {

	public static final NetherCrafterBlock INSTANCE = new NetherCrafterBlock();
	public static final Item ITEM_INSTANCE = new BlockItem(INSTANCE, new Item.Properties().tab(PackagedAvaritia.ITEM_GROUP)).setRegistryName("packagedavaritia:nether_crafter");

	public NetherCrafterBlock() {
		super(AbstractBlock.Properties.of(Material.METAL).strength(15F, 25F).sound(SoundType.METAL));
		setRegistryName("packagedavaritia:nether_crafter");
	}

	@Override
	public NetherCrafterTile createTileEntity(BlockState state, IBlockReader worldIn) {
		return NetherCrafterTile.TYPE_INSTANCE.create();
	}
}
