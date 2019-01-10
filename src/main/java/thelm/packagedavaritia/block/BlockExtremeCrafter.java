package thelm.packagedavaritia.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thelm.packagedauto.block.BlockBase;
import thelm.packagedauto.tile.TileBase;
import thelm.packagedavaritia.PackagedAvaritia;
import thelm.packagedavaritia.tile.TileExtremeCrafter;

public class BlockExtremeCrafter extends BlockBase {

	public static final BlockExtremeCrafter INSTANCE = new BlockExtremeCrafter();
	public static final Item ITEM_INSTANCE = new ItemBlock(INSTANCE).setRegistryName("packagedavaritia:extreme_crafter");
	public static final ModelResourceLocation MODEL_LOCATION = new ModelResourceLocation("packagedavaritia:extreme_crafter#normal");

	public BlockExtremeCrafter() {
		super(Material.IRON);
		setHardness(15F);
		setResistance(25F);
		setSoundType(SoundType.METAL);
		setUnlocalizedName("packagedavaritia.extreme_crafter");
		setRegistryName("packagedavaritia:extreme_crafter");
		setCreativeTab(PackagedAvaritia.CREATIVE_TAB);
	}

	@Override
	public TileBase createNewTileEntity(World worldIn, int meta) {
		return new TileExtremeCrafter();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels() {
		ModelLoader.setCustomModelResourceLocation(ITEM_INSTANCE, 0, MODEL_LOCATION);
	}
}
