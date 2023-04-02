package com._48panda.tech.mixin;

import com._48panda.tech.block.copper_infused.CopperInfusedRedstoneWire;
import com._48panda.tech.init.PandaTechBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(DiodeBlock.class)
public abstract class DiodeBlockMixin {
    @ModifyConstant(method = "getInputSignal(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)I",
    constant = @Constant(intValue = 15))
    public int inject(int constant) {
        return 63;
    }

    @Inject(method = "getInputSignal(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)I",
            at = @At(value = "RETURN", ordinal = 1), locals = LocalCapture.CAPTURE_FAILHARD)
    public void inject2(Level p_52544_, BlockPos p_52545_, BlockState p_52546_, CallbackInfoReturnable<Integer> cir, Direction direction, BlockPos blockpos, int i, BlockState blockstate) {
        int ret = cir.getReturnValueI();
        int newret = Math.max(ret, blockstate.is(PandaTechBlocks.COPPER_INFUSED_REDSTONE_WIRE.get()) ? blockstate.getValue(CopperInfusedRedstoneWire.POWER) : 0);
        if (ret != newret) {
            cir.setReturnValue(newret);
        }
    }
    
    /**
     * @author 48panda
     * @reason Full rewrite needed for copper infused redstone
     */
    @Overwrite()
    protected int getAlternateSignalAt(LevelReader p_52552_, BlockPos p_52553_, Direction p_52554_) {
        BlockState blockstate = p_52552_.getBlockState(p_52553_);
        if (this.isAlternateInput(blockstate)) {
            if (blockstate.is(Blocks.REDSTONE_BLOCK)) {
                return 15;
            } else if (blockstate.is(PandaTechBlocks.COPPER_INFUSED_REDSTONE_BLOCK.get())) {
                return 63;
            } else {
                return blockstate.is(Blocks.REDSTONE_WIRE) ? blockstate.getValue(RedStoneWireBlock.POWER) : 
                       blockstate.is(PandaTechBlocks.COPPER_INFUSED_REDSTONE_WIRE.get()) ? blockstate.getValue(CopperInfusedRedstoneWire.POWER): 
                               p_52552_.getDirectSignal(p_52553_, p_52554_);
            }
        } else {
            return 0;
        }
    }
    @Shadow
    abstract boolean isAlternateInput(BlockState blockstate);
}
