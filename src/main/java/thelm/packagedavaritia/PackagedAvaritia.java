package thelm.packagedavaritia;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thelm.packagedavaritia.block.BlockExtremeCrafter;
import thelm.packagedavaritia.event.CommonEventHandler;

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
	public static final String VERSION = "1.12.2-0@VERSION@";
	public static final String DEPENDENCIES = "required-after:packagedauto@[1.12.2-1.0.17,);required-after:avaritia;";
	public static final String GUI_FACTORY = "thelm.packagedavaritia.client.gui.GuiPackagedAvaritiaConfigFactory";
	public static final CreativeTabs CREATIVE_TAB = new CreativeTabs("packagedavaritia") {
		@SideOnly(Side.CLIENT)
		@Override
		public ItemStack createIcon() {
			return new ItemStack(BlockExtremeCrafter.INSTANCE);
		}
	};
	@SidedProxy(
			clientSide = "thelm.packagedavaritia.client.event.ClientEventHandler",
			serverSide = "thelm.packagedavaritia.event.CommonEventHandler",
			modId = PackagedAvaritia.MOD_ID)
	public static CommonEventHandler proxy;

	@EventHandler
	public void onPreInit(FMLPreInitializationEvent event) {
		proxy.onPreInit(event);
	}
}
