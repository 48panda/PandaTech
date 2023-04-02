package com._48panda.tech.mixin;

import com.google.common.collect.Maps;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.state.properties.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;
import java.util.Set;

@Mixin(BlockColors.class)
public abstract class BlockColorsMixin {
    @Shadow
    private final Map<Block, Set<Property<?>>> coloringStates = Maps.newHashMap();
    
    @Inject(method = "createDefault()Lnet/minecraft/client/color/block/BlockColors;",
    at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/color/block/BlockColors;addColoringState(Lnet/minecraft/world/level/block/state/properties/Property;[Lnet/minecraft/world/level/block/Block;)V",
            ordinal = 1),
    locals = LocalCapture.CAPTURE_FAILHARD)
    private static void createDefault(CallbackInfoReturnable<BlockColors> cir, BlockColors blockcolors) {
        
    }
}
