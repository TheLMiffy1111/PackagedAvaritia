package thelm.packagedavaritia;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thelm.packagedavaritia.block.BlockExtremeCrafter;
import thelm.packagedavaritia.proxy.CommonProxy;

@Mod(
		modid = PackagedAvaritia.MOD_ID,
		name = PackagedAvaritia.NAME,
		version = PackagedAvaritia.VERSION,
		dependencies = PackagedAvaritia.DEPENDENCIES,
		guiFactory = PackagedAvaritia.GUI_FACTORY
		)
public class PackagedAvaritia {

	public static final String MOD_ID = "packagedavaritia";
	public static final String NAME = "PackagedAvaritia";
	public static final String VERSION = "1.12.2-1.0.2.5";
	public static final String DEPENDENCIES = "required-after:packagedauto@[1.12.2-1.0.5.17,1.12.2-2.0.0.0);required-after:avaritia;";
	public static final String GUI_FACTORY = "thelm.packagedavaritia.client.gui.GuiPackagedAvaritiaConfigFactory";
	public static final CreativeTabs CREATIVE_TAB = new CreativeTabs("packagedavaritia") {
		@SideOnly(Side.CLIENT)
		@Override
		public ItemStack createIcon() {
			return new ItemStack(BlockExtremeCrafter.INSTANCE);
		}
	};
	@Instance
	public static PackagedAvaritia instance;
	@SidedProxy(clientSide = "thelm.packagedavaritia.proxy.ClientProxy", serverSide = "thelm.packagedavaritia.proxy.CommonProxy", modId = PackagedAvaritia.MOD_ID)
	public static CommonProxy proxy;
	public static ModMetadata metadata;

	@EventHandler
	public void firstMovement(FMLPreInitializationEvent event) {
		metadata = event.getModMetadata();
		metadata.autogenerated = false;
		metadata.version = VERSION;
		metadata.authorList.add("TheLMiffy1111");
		metadata.description = "A PackagedAuto addon that adds Avaritia autocrafting.";

		proxy.register(event);
	}
}
