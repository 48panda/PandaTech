package com._48panda.tech.block.machines.solar_panel;

import com._48panda.tech.block.machines.Machine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SolarPanelBlock extends Machine implements EntityBlock {
    public SolarPanelBlock() {
        super(Properties.of(Material.METAL).sound(SoundType.METAL).strength(1.5f, 3f));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
    }

    @Override
    public @NotNull List<ItemStack> getDrops(BlockState state, LootContext.@NotNull Builder p_60538_) {
        return super.getDrops(state, p_60538_);
    }
    

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new SolarPanelBlockEntity(pos, state);
    }
}
