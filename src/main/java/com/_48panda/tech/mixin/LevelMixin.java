package com._48panda.tech.mixin;

import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Level.class)
public class LevelMixin {
    @ModifyConstant(method= "getBestNeighborSignal(Lnet/minecraft/core/BlockPos;)I", constant = @Constant(intValue = 15))
    public int replaceConstant(int maxSignalStrength) {
        return 63;
    }
    @ModifyConstant(method= "getDirectSignalTo(Lnet/minecraft/core/BlockPos;)I", constant = @Constant(intValue = 15))
    public int replaceConstant2(int maxSignalStrength) {
        return 63;
    }
}
