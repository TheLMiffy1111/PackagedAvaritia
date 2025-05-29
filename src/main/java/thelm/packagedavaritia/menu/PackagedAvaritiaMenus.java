package thelm.packagedavaritia.menu;

import java.util.function.Supplier;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import thelm.packagedauto.menu.factory.PositionalBlockEntityMenuFactory;

public class PackagedAvaritiaMenus {

	private PackagedAvaritiaMenus() {}

	public static <C extends AbstractContainerMenu, T extends BlockEntity> Supplier<MenuType<C>> of(PositionalBlockEntityMenuFactory.Factory<C, T> factory) {
		return ()->IMenuTypeExtension.create(new PositionalBlockEntityMenuFactory<>(factory));
	}

	public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, "packagedavaritia");

	public static final DeferredHolder<MenuType<?>, MenuType<SculkCrafterMenu>> SCULK_CRAFTER = MENUS.register("sculk_crafter", of(SculkCrafterMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<NetherCrafterMenu>> NETHER_CRAFTER = MENUS.register("nether_crafter", of(NetherCrafterMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<EndCrafterMenu>> END_CRAFTER = MENUS.register("end_crafter", of(EndCrafterMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<ExtremeCrafterMenu>> EXTREME_CRAFTER = MENUS.register("extreme_crafter", of(ExtremeCrafterMenu::new));
}
