package com._48panda.tech.network.packet;

import com._48panda.tech.block.entity.IMinionBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MinionUpdateS2CPacket {
    private final BlockPos pos, mainPos;

    public MinionUpdateS2CPacket(BlockPos pos, BlockPos mainPos) {
        this.pos = pos;
        this.mainPos = mainPos;
    }

    public MinionUpdateS2CPacket(FriendlyByteBuf buf) {
        pos = buf.readBlockPos();
        mainPos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
        buf.writeBlockPos(mainPos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            assert Minecraft.getInstance().level != null; // Again, if this happens we have bigger problems but this shuts up the warnings so cool.
            if (Minecraft.getInstance().level.getBlockEntity(pos) instanceof IMinionBlockEntity blockEntity) {
                blockEntity.attachMain(mainPos);
            }
        });
        return true;
    }
}
