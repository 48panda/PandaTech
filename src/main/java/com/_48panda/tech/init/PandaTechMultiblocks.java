package com._48panda.tech.init;

import com._48panda.tech.Constants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import vazkii.patchouli.api.IMultiblock;
import vazkii.patchouli.api.IStateMatcher;
import vazkii.patchouli.api.PatchouliAPI;
import vazkii.patchouli.api.TriPredicate;

import java.util.List;
import java.util.Objects;

public class PandaTechMultiblocks {
    @NotNull
    public static IStateMatcher tagMatcher(@NotNull TagKey<Block> tag) {
        return new IStateMatcher() {
            @NotNull
            @Override
            public BlockState getDisplayedState(long ticks) {
                List<Block> t = Objects.requireNonNull(ForgeRegistries.BLOCKS.tags()).getTag(tag).stream().toList();
                int idx = (int) ((ticks / 20) % t.size());
                return t.get(idx).defaultBlockState();
            }

            @NotNull
            @Override
            public TriPredicate<BlockGetter, BlockPos, BlockState> getStatePredicate() {
                return (w, p, s) -> s.is(tag);
            }
        };
    }
    public static PatchouliAPI.IPatchouliAPI getAPI() {return PatchouliAPI.get();}
    public static final TagKey<Block> FRAME_BLOCK = BlockTags.create(new ResourceLocation(Constants.ID, "frame"));
    public static IMultiblock PULVERISER = null;
    public static IMultiblock CENTRIFUGE = null;
    
    public static void init() {
        PULVERISER = PatchouliAPI.get().registerMultiblock(new ResourceLocation(Constants.ID, "pulveriser"), PatchouliAPI.get().makeMultiblock(new String[][]{
                        { "fff", "mpf", "fff" },
                        { "fff", "f g", "fff" },
                        { "fff", "ff0", "fff" }
                }, 
                'f', tagMatcher(FRAME_BLOCK),
                '0', getAPI().propertyMatcher(PandaTechBlocks.PULVERISER.get().defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH), BlockStateProperties.HORIZONTAL_FACING),
                'g', getAPI().looseBlockMatcher(PandaTechBlocks.HIGH_DENSITY_GLASS.get()),
                'p', getAPI().looseBlockMatcher(PandaTechBlocks.MECHANICAL_PISTON.get()),
                'm', getAPI().propertyMatcher(PandaTechBlocks.BASIC_MOTOR.get().defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH), BlockStateProperties.HORIZONTAL_FACING)
        ));
        CENTRIFUGE = PatchouliAPI.get().registerMultiblock(new ResourceLocation(Constants.ID, "centrifuge"), PatchouliAPI.get().makeMultiblock(new String[][]{
                        {"ggg", "ggg", "ggg"},
                        {"fff", "ff0", "fff"}
                }, 
                'f', tagMatcher(FRAME_BLOCK),
                '0', getAPI().propertyMatcher(PandaTechBlocks.CENTRIFUGE.get().defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH), BlockStateProperties.HORIZONTAL_FACING),
                'g', getAPI().looseBlockMatcher(PandaTechBlocks.HIGH_DENSITY_GLASS.get()),
                'p', getAPI().looseBlockMatcher(PandaTechBlocks.MECHANICAL_PISTON.get()),
                'm', getAPI().propertyMatcher(PandaTechBlocks.BASIC_MOTOR.get().defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH), BlockStateProperties.HORIZONTAL_FACING)
        ));
    }
}
