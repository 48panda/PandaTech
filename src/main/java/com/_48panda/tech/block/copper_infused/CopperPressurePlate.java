package com._48panda.tech.block.copper_infused;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class CopperPressurePlate extends PressurePlateBlock {
    public CopperPressurePlate() {
        super(Sensitivity.MOBS, Properties.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE));
    }
    
    @Override
    protected void playOnSound(LevelAccessor p_55256_, BlockPos p_55257_) {
        p_55256_.playSound((Player)null, p_55257_, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundSource.BLOCKS, 0.3F, 0.8F);
    }
    @Override
    protected void playOffSound(LevelAccessor p_55267_, BlockPos p_55268_) {
            p_55267_.playSound((Player)null, p_55268_, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundSource.BLOCKS, 0.3F, 0.7F);
    }
    @Override    
    protected int getSignalStrength(Level level, BlockPos pos) {
        net.minecraft.world.phys.AABB aabb = TOUCH_AABB.move(pos);
        List<? extends Entity> list = level.getEntitiesOfClass(LivingEntity.class, aabb);
        if (!list.isEmpty()) {
            for(Entity entity : list) {
                if (!entity.isIgnoringBlockTriggers()) {
                    return 63;
                }
            }
        }
        return 0;
    }
    @Override
    protected int getSignalForState(BlockState p_55270_) {
        return p_55270_.getValue(POWERED) ? 63 : 0;
    }
}
