package com._48panda.tech.mixin;

import net.minecraft.world.level.redstone.Redstone;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Redstone.class)
public class RedstoneMixin {
    @Shadow
    public static final int SIGNAL_MAX = 15;
}
