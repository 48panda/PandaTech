package com._48panda.tech.client.block.render;

import com._48panda.tech.block.multiblock.CentrifugeMultiblockControllerBlockEntity;
import com._48panda.tech.block.multiblock.MultiBlockDisplay;
import com._48panda.tech.block.multiblock.MultiblockDisplayBlock;
import com._48panda.tech.init.PandaTechBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.data.EmptyModelData;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class CentrifugeRenderer implements BlockEntityRenderer<CentrifugeMultiblockControllerBlockEntity> {
    private final BlockEntityRendererProvider.Context context;
    public CentrifugeRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }
    @Override
    public void render(CentrifugeMultiblockControllerBlockEntity be, float partialTicks, @NotNull PoseStack stack, @NotNull MultiBufferSource buffer, int combinedLightIn, int combinedOverlayIn) {
        if (!be.isAttached()) {
            return;
        }
        final BlockRenderDispatcher dispatcher = context.getBlockRenderDispatcher();
        final ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        Direction dir = be.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING).getOpposite();
        stack.pushPose();
        stack.translate(0.5,0, 0.5);
        stack.mulPose(Vector3f.YN.rotationDegrees(dir.get2DDataValue() * 90));
        stack.translate(-0.5, 0, 0.5);
        
        dispatcher.renderSingleBlock(PandaTechBlocks.DISPLAY_MULTIBLOCK.get().defaultBlockState()
                .setValue(MultiblockDisplayBlock.DISPLAY, MultiBlockDisplay.CENTRIFUGE),
                stack, buffer, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
        
        if (be.prevPartialTicks > partialTicks) {
            be.rotation += be.speed;
            //System.out.println("E");
        }
        float angle = (float)be.rotation + ((float)be.speed) * ((partialTicks - be.prevPartialTicks) % 1);
        //System.out.println(angle);
        //System.out.println(be.rotation);
        //System.out.println((partialTicks - be.prevPartialTicks) % 1);
        be.rotation = angle % 360;
        be.prevPartialTicks = partialTicks;
        //angle = 0;
        stack.translate(0.5, 0, 0.5);
        stack.mulPose(Vector3f.YN.rotationDegrees(angle));
        stack.translate(-0.5, 0, -0.5);
        //stack.translate(-0.5, 0, 0.5);
        dispatcher.renderSingleBlock(PandaTechBlocks.DISPLAY_MULTIBLOCK.get().defaultBlockState()
                        .setValue(MultiblockDisplayBlock.DISPLAY, MultiBlockDisplay.CENTRIFUGE_ARM),
                stack, buffer, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);

        ItemStack toDraw = be.inventory.getStackInSlot(0);
        stack.translate(0.5f, 1.1875, 1.625f);
        stack.scale(0.4f, 0.4f, 0.4f);
        if (itemRenderer.getModel(toDraw, Minecraft.getInstance().level, Minecraft.getInstance().player, 0).isGui3d()) {
            stack.translate(0, 0.25, 0);
        } else {
            stack.mulPose(Vector3f.XP.rotationDegrees(90));
        }
        itemRenderer.renderStatic(toDraw, ItemTransforms.TransformType.FIXED, combinedLightIn, combinedOverlayIn, stack, buffer, 0);
        
        stack.popPose();
        
        
        
    }
}
