package com._48panda.tech.mixin;

import com._48panda.tech.init.PandaTechBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.extensions.IForgeBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(IForgeBlock.class)
public class IForgeBlockMixin {
    @Inject(method = "canConnectRedstone(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;)Z",
    at = @At("HEAD"),
    remap = false)
    void inject(BlockState state, BlockGetter level, BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        System.out.println(state);
        if (state.is(PandaTechBlocks.COPPER_INFUSED_REDSTONE_WIRE.get())) {
            cir.setReturnValue(true);
        }
    }
}
