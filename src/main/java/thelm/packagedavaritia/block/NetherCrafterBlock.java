package thelm.packagedavaritia.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import thelm.packagedauto.block.BaseBlock;
import thelm.packagedauto.block.entity.BaseBlockEntity;
import thelm.packagedavaritia.block.entity.NetherCrafterBlockEntity;
import thelm.packagedavaritia.block.entity.PackagedAvaritiaBlockEntities;

public class NetherCrafterBlock extends BaseBlock {

	public NetherCrafterBlock() {
		super(BlockBehaviour.Properties.of().strength(15F, 25F).mapColor(MapColor.METAL).sound(SoundType.METAL));
	}

	@Override
	public NetherCrafterBlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return PackagedAvaritiaBlockEntities.NETHER_CRAFTER.get().create(pos, state);
	}

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
		return BaseBlockEntity::tick;
	}
}
