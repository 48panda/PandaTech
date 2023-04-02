package com._48panda.tech.mixin;

import com._48panda.tech.block.copper_infused.CopperInfusedRedstoneWire;
import com._48panda.tech.block.copper_infused.RedstoneEnabler;
import com._48panda.tech.init.PandaTechBlocks;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.RedstoneSide;
import net.minecraft.world.phys.Vec3;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RedStoneWireBlock.class)
public abstract class RedStoneWireBlockMixin {
    @Shadow
    @Final
    public static final Vec3[] COLORS = Util.make(new Vec3[17], (p_154319_) -> {
        for(int i = 0; i <= 16; ++i) {
            float f = (float)i / 16.0F;
            float f1 = f * 0.6F + (f > 0.0F ? 0.4F : 0.3F);
            float f2 = Mth.clamp(f * f * 0.7F - 0.5F, 0.0F, 1.0F);
            float f3 = Mth.clamp(f * f * 0.6F - 0.7F, 0.0F, 1.0F);
            p_154319_[i] = new Vec3((double)f1, (double)f2, (double)f3);
        }

    });
    
    @Shadow boolean shouldSignal;

    public boolean isShouldSignal() {
        return shouldSignal;
    }

    @Redirect(method = "calculateTargetStrength(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)I",
            at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/block/RedStoneWireBlock;shouldSignal:Z", opcode = Opcodes.PUTFIELD))
    private void injected(RedStoneWireBlock instance, boolean value) {
        RedstoneEnabler.shouldSignal = value;
    }

    public void setShouldSignal(boolean shouldSignal) {
        this.shouldSignal = shouldSignal;
    }

    /**
     * @author 48panda
     * @reason one line
     */
    @Overwrite()
    private int getWireSignal(BlockState state) {
        System.out.println("epic2");
        return state.is(PandaTechBlocks.COPPER_INFUSED_REDSTONE_WIRE.get()) ? state.getValue(CopperInfusedRedstoneWire.POWER) : state.is(Blocks.REDSTONE_WIRE) ? state.getValue(BlockStateProperties.POWER) : 0;
    }
    
    @Redirect(method = "getDirectSignal(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;)I",
    at = @At(value="FIELD", target = "Lnet/minecraft/world/level/block/RedStoneWireBlock;shouldSignal:Z", opcode = Opcodes.GETFIELD))
    private boolean injected2(RedStoneWireBlock instance) {
        return RedstoneEnabler.shouldSignal;
    }

    @Redirect(method = "getSignal(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;)I",
            at = @At(value="FIELD", target = "Lnet/minecraft/world/level/block/RedStoneWireBlock;shouldSignal:Z", opcode = Opcodes.GETFIELD))
    private boolean injected3(RedStoneWireBlock instance) {
        return RedstoneEnabler.shouldSignal;
    }
    
    @Redirect(method = "isSignalSource(Lnet/minecraft/world/level/block/state/BlockState;)Z",
    at =  @At(value="FIELD", target = "Lnet/minecraft/world/level/block/RedStoneWireBlock;shouldSignal:Z", opcode = Opcodes.GETFIELD))
    private boolean injected4(RedStoneWireBlock instance) {
        return RedstoneEnabler.shouldSignal;
    }
    
    @Inject(method="calculateTargetStrength(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)I", at=@At("RETURN"), cancellable = true)
    public void calculateTargetStrength(Level p_55528_, BlockPos p_55529_, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(Math.min(16, cir.getReturnValueI()));
    }
    
    @Inject(method= "shouldConnectTo(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/Direction;)Z", at=@At("HEAD"), cancellable = true)
    private static void shouldConnectTo(BlockState state, Direction dir, CallbackInfoReturnable<Boolean> cir) {
        if (state.is(PandaTechBlocks.COPPER_INFUSED_REDSTONE_WIRE.get())) {
            cir.setReturnValue(true);
        }
    }
}
