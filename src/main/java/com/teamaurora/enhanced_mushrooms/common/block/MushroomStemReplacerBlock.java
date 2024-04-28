package com.teamaurora.enhanced_mushrooms.common.block;

import com.teamaurora.enhanced_mushrooms.core.registry.EMBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;

public class MushroomStemReplacerBlock extends HugeMushroomBlock {

    public MushroomStemReplacerBlock(Properties p_54136_) {
        super(p_54136_);
        this.registerDefaultState(EMBlocks.MUSHROOM_STEM.get().defaultBlockState());
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
        level.setBlock(pos, EMBlocks.MUSHROOM_STEM.get().defaultBlockState(), 3);
        level.scheduleTick(pos, state.getBlock(), 1);
    }


    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        level.scheduleTick(pos, state.getBlock(), 1);
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, level, pos, block, fromPos, isMoving);
        level.scheduleTick(pos, state.getBlock(), 1);
    }
}
