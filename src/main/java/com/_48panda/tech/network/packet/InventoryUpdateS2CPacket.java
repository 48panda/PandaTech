package com._48panda.tech.network.packet;

import com._48panda.tech.block.entity.IInventoryUpdatable;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class InventoryUpdateS2CPacket {
    private final BlockPos pos;
    private final int slot;
    private final ItemStack setTo;
    private final boolean isOutput;

    public InventoryUpdateS2CPacket(BlockPos pos, int slot, ItemStack setTo,  boolean isOutput) {
        this.pos = pos;
        this.slot = slot;
        this.setTo = setTo;
        this.isOutput = isOutput;
    }

    public InventoryUpdateS2CPacket(FriendlyByteBuf buf) {
        pos = buf.readBlockPos();
        slot = buf.readInt();
        setTo = buf.readItem();
        isOutput = buf.readBoolean();
    }
    
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
        buf.writeInt(slot);
        buf.writeItem(setTo);
        buf.writeBoolean(isOutput);
    }
    
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            assert Minecraft.getInstance().level != null; // Again, if this happens we have bigger problems but this shuts up the warnings so cool.
            if (Minecraft.getInstance().level.getBlockEntity(pos) instanceof IInventoryUpdatable blockEntity) {
                blockEntity.setItem(slot, setTo, isOutput);
            }
        });
        return true;
    }
}
