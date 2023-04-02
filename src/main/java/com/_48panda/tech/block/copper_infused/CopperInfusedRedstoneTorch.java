package com._48panda.tech.block.copper_infused;

import com._48panda.tech.init.PandaTechBlocks;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.math.Vector3f;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.*;

public class CopperInfusedRedstoneTorch extends TorchBlock {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    private static final Map<BlockGetter, List<CopperInfusedRedstoneTorch.Toggle>> RECENT_TOGGLES = new WeakHashMap<>();
    public static final int RECENT_TOGGLE_TIMER = 60;
    public static final int MAX_RECENT_TOGGLES = 8;
    public static final int RESTART_DELAY = 160;
    private static final int TOGGLE_DELAY = 2;

    public CopperInfusedRedstoneTorch() {
        super(BlockBehaviour.Properties.copy(Blocks.REDSTONE_TORCH), DustParticleOptions.REDSTONE);
        this.registerDefaultState(this.stateDefinition.any().setValue(LIT, Boolean.valueOf(true)));
    }

    public void onPlace(BlockState p_55724_, Level p_55725_, BlockPos p_55726_, BlockState p_55727_, boolean p_55728_) {
        for (Direction direction : Direction.values()) {
            p_55725_.updateNeighborsAt(p_55726_.relative(direction), this);
        }

    }

    public void onRemove(BlockState p_55706_, Level p_55707_, BlockPos p_55708_, BlockState p_55709_, boolean p_55710_) {
        if (!p_55710_) {
            for (Direction direction : Direction.values()) {
                p_55707_.updateNeighborsAt(p_55708_.relative(direction), this);
            }

        }
    }

    public int getSignal(BlockState p_55694_, BlockGetter p_55695_, BlockPos p_55696_, Direction p_55697_) {
        return p_55694_.getValue(LIT) && Direction.UP != p_55697_ ? 63 : 0;
    }

    protected boolean hasNeighborSignal(Level p_55681_, BlockPos p_55682_, BlockState p_55683_) {
        return p_55681_.hasSignal(p_55682_.below(), Direction.DOWN);
    }

    public void tick(BlockState p_55689_, ServerLevel p_55690_, BlockPos p_55691_, Random p_55692_) {
        boolean flag = this.hasNeighborSignal(p_55690_, p_55691_, p_55689_);
        List<CopperInfusedRedstoneTorch.Toggle> list = RECENT_TOGGLES.get(p_55690_);

        while (list != null && !list.isEmpty() && p_55690_.getGameTime() - (list.get(0)).when > 60L) {
            list.remove(0);
        }

        if (p_55689_.getValue(LIT)) {
            if (flag) {
                p_55690_.setBlock(p_55691_, p_55689_.setValue(LIT, Boolean.valueOf(false)), 3);
                if (isToggledTooFrequently(p_55690_, p_55691_, true)) {
                    p_55690_.levelEvent(1502, p_55691_, 0);
                    p_55690_.scheduleTick(p_55691_, p_55690_.getBlockState(p_55691_).getBlock(), 160);
                }
            }
        } else if (!flag && !isToggledTooFrequently(p_55690_, p_55691_, false)) {
            p_55690_.setBlock(p_55691_, p_55689_.setValue(LIT, Boolean.valueOf(true)), 3);
        }

    }

    public void neighborChanged(BlockState p_55699_, Level p_55700_, BlockPos p_55701_, Block p_55702_, BlockPos p_55703_, boolean p_55704_) {
        if (p_55699_.getValue(LIT) == this.hasNeighborSignal(p_55700_, p_55701_, p_55699_) && !p_55700_.getBlockTicks().willTickThisTick(p_55701_, this)) {
            p_55700_.scheduleTick(p_55701_, this, 2);
        }

    }

    public int getDirectSignal(BlockState p_55719_, BlockGetter p_55720_, BlockPos p_55721_, Direction p_55722_) {
        return p_55722_ == Direction.DOWN ? p_55719_.getSignal(p_55720_, p_55721_, p_55722_) : 0;
    }

    public boolean isSignalSource(BlockState p_55730_) {
        return true;
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, Random random) {
        if (state.getValue(LIT)) {
            double d0 = (double) pos.getX() + 0.5D + (random.nextDouble() - 0.5D) * 0.2D;
            double d1 = (double) pos.getY() + 0.7D + (random.nextDouble() - 0.5D) * 0.2D;
            double d2 = (double) pos.getZ() + 0.5D + (random.nextDouble() - 0.5D) * 0.2D;
            level.addParticle(this.flameParticle, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_55717_) {
        p_55717_.add(LIT);
    }

    private static boolean isToggledTooFrequently(Level level, BlockPos pos, boolean bool) {
        List<CopperInfusedRedstoneTorch.Toggle> list = RECENT_TOGGLES.computeIfAbsent(level, ($0) -> {
            return Lists.newArrayList();
        });
        if (bool) {
            list.add(new CopperInfusedRedstoneTorch.Toggle(pos.immutable(), level.getGameTime()));
        }

        int i = 0;

        for (int j = 0; j < list.size(); ++j) {
            CopperInfusedRedstoneTorch.Toggle toggle = list.get(j);
            if (toggle.pos.equals(pos)) {
                ++i;
                if (i >= 8) {
                    return true;
                }
            }
        }

        return false;
    }

    public static class Toggle {
        final BlockPos pos;
        final long when;

        public Toggle(BlockPos p_55734_, long p_55735_) {
            this.pos = p_55734_;
            this.when = p_55735_;
        }
    }
}
