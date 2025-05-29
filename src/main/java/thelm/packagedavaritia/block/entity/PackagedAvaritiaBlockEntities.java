package thelm.packagedavaritia.block.entity;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import thelm.packagedauto.util.MiscHelper;
import thelm.packagedavaritia.block.PackagedAvaritiaBlocks;
import thelm.packagedavaritia.integration.appeng.blockentity.AEEndCrafterBlockEntity;
import thelm.packagedavaritia.integration.appeng.blockentity.AEExtremeCrafterBlockEntity;
import thelm.packagedavaritia.integration.appeng.blockentity.AENetherCrafterBlockEntity;
import thelm.packagedavaritia.integration.appeng.blockentity.AESculkCrafterBlockEntity;

public class PackagedAvaritiaBlockEntities {

	private PackagedAvaritiaBlockEntities() {}

	public static <T extends BlockEntity> Supplier<BlockEntityType<T>> of(BooleanSupplier condition, Supplier<Supplier<BlockEntityType.BlockEntitySupplier<? extends T>>> trueSupplier, Supplier<Supplier<BlockEntityType.BlockEntitySupplier<? extends T>>> falseSupplier, Supplier<Block>... validBlocks) {
		return ()->new BlockEntityType<>(MiscHelper.INSTANCE.conditionalSupplier(condition, trueSupplier, falseSupplier).get(), Arrays.stream(validBlocks).map(Supplier::get).filter(Objects::nonNull).collect(Collectors.toSet()), null);
	}

	public static final BooleanSupplier AE2_LOADED = ()->ModList.get().isLoaded("ae2");

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, "packagedavaritia");

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SculkCrafterBlockEntity>> SCULK_CRAFTER = BLOCK_ENTITIES.register(
			"sculk_crafter", of(AE2_LOADED, ()->()->AESculkCrafterBlockEntity::new, ()->()->SculkCrafterBlockEntity::new, PackagedAvaritiaBlocks.SCULK_CRAFTER));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NetherCrafterBlockEntity>> NETHER_CRAFTER = BLOCK_ENTITIES.register(
			"nether_crafter", of(AE2_LOADED, ()->()->AENetherCrafterBlockEntity::new, ()->()->NetherCrafterBlockEntity::new, PackagedAvaritiaBlocks.NETHER_CRAFTER));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EndCrafterBlockEntity>> END_CRAFTER = BLOCK_ENTITIES.register(
			"end_crafter", of(AE2_LOADED, ()->()->AEEndCrafterBlockEntity::new, ()->()->EndCrafterBlockEntity::new, PackagedAvaritiaBlocks.END_CRAFTER));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ExtremeCrafterBlockEntity>> EXTREME_CRAFTER = BLOCK_ENTITIES.register(
			"extreme_crafter", of(AE2_LOADED, ()->()->AEExtremeCrafterBlockEntity::new, ()->()->ExtremeCrafterBlockEntity::new, PackagedAvaritiaBlocks.EXTREME_CRAFTER));
}
