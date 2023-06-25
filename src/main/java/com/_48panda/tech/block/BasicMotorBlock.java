package com._48panda.tech.block;

import com._48panda.tech.block.entity.BasicMotorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class BasicMotorBlock extends HorizontalFrameBlock {
    public BasicMotorBlock() {
        super(Properties.of(Material.METAL, MaterialColor.COLOR_LIGHT_GRAY).sound(SoundType.METAL).strength(5.0f, 6.0f).noOcclusion());
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new BasicMotorBlockEntity(pos, state);
    }

    @Override
    @SuppressWarnings({"deprecation"})
    public int getLightBlock(@NotNull BlockState p_60585_, @NotNull BlockGetter p_60586_, @NotNull BlockPos p_60587_) {
        return 0;
    }
}
