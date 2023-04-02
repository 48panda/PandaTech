package com._48panda.tech.mixin;

import net.minecraft.Util;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockStateProperties.class)
public class BlockStatePropertiesMixin {
    @Shadow
    public static final IntegerProperty POWER = IntegerProperty.create("power", 0, 16);
}