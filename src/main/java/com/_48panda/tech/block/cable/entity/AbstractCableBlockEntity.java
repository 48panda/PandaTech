package com._48panda.tech.block.cable.entity;

import com._48panda.tech.block.cable.AbstractCableBlock;
import com._48panda.tech.block.cable.Connection;
import com._48panda.tech.block.entity.InventoryBlockEntity;
import com._48panda.tech.block.entity.TickableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public abstract class AbstractCableBlockEntity extends InventoryBlockEntity implements TickableBlockEntity {
    public final Component title;
    protected List<Connection> connectionCache;
    private Map<Direction, Boolean> isLocked;
    public AbstractCableBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, Component title) {
        super(type, pos, state, 1);
        this.title = title;
        isLocked = new HashMap<>();
        Arrays.stream(Direction.values()).forEach(d->isLocked.put(d, false));
    }
    
    public abstract void sendTo(Direction dir, List<Connection> connections);

    @Override
    public void tick() {
        if (level.isClientSide()) {
            return;
        }
        if (isExtracting() && getConnections().size()>0) {
            for (Direction d: Direction.values()) {
                if (isExtracting(d)) {
                    sendTo(d, getConnections().stream().filter(x->!x.getPos().equals(worldPosition.relative(d))).toList());
                }
            }
        }
    }
    public static void markCablesDirty(Level world, BlockPos pos) {
        List<BlockPos> travelPositions = new ArrayList<>();
        LinkedList<BlockPos> queue = new LinkedList<>();
        Block block = world.getBlockState(pos).getBlock();
        if (!(block instanceof AbstractCableBlock)) {
            return;
        }
        AbstractCableBlock pipeBlock = (AbstractCableBlock) block;
        travelPositions.add(pos);
        addToDirtyList(world, pos, pipeBlock, travelPositions, queue);
        while (queue.size() > 0) {
            BlockPos blockPos = queue.removeFirst();
            block = world.getBlockState(blockPos).getBlock();
            if (block instanceof AbstractCableBlock) {
                addToDirtyList(world, blockPos, (AbstractCableBlock) block, travelPositions, queue);
            }
        }
        for (BlockPos p : travelPositions) {
            BlockEntity te = world.getBlockEntity(p);
            if (!(te instanceof AbstractCableBlockEntity)) {
                continue;
            }
            AbstractCableBlockEntity pipe = (AbstractCableBlockEntity) te;
            pipe.connectionCache = null;
        }
    }
    public boolean isExtracting(Direction d) {
        return ((AbstractCableBlock)(getBlockState().getBlock())).isExtracting(level, worldPosition, d);
    } 
    
    public boolean isExtracting() {
        for (Direction d: Direction.values()) {
            if (isExtracting(d)) return true;
        }
        return false;
    }

    private static void addToDirtyList(Level world, BlockPos pos, AbstractCableBlock pipeBlock, List<BlockPos> travelPositions, LinkedList<BlockPos> queue) {
        for (Direction direction : Direction.values()) {
            if (pipeBlock.isConnected(world, pos, direction)) {
                BlockPos p = pos.relative(direction);
                if (!travelPositions.contains(p) && !queue.contains(p)) {
                    travelPositions.add(p);
                    queue.add(p);
                }
            }
        }
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, Direction direction) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return LazyOptional.empty();
        }
        return super.getCapability(cap, direction);
    }

    public List<Connection> getConnections() {
        if (level == null) return new ArrayList<>();
        if (connectionCache != null) return connectionCache;
        updateConnectionCache();
        if (connectionCache != null) return connectionCache;
        return new ArrayList<>();
    }

    public void updateConnectionCache() {
        BlockState blockState = getBlockState();
        if (!(blockState.getBlock() instanceof AbstractCableBlock)) {
            connectionCache = null;
            return;
        }
        
        Map<Connection, Integer> connections = new HashMap<>();
        Map<BlockPos, Integer> queue = new HashMap<>();
        List<BlockPos> visited = new ArrayList<>();
        
        enqueue(queue, getBlockPos(), visited, 1, connections);
        
        while (!queue.isEmpty()) {
            Map.Entry<BlockPos, Integer> entry = queue.entrySet().stream().findAny().get(); // Find any entry
            enqueue(queue, entry.getKey(), visited, entry.getValue(), connections);
            visited.add(entry.getKey());
            queue.remove(entry.getKey());
        }
        
        connectionCache = connections.entrySet().stream().map(x->x.getKey().with(x.getValue())).toList();
    }
    public void enqueue(Map<BlockPos, Integer> queue, BlockPos pos, List<BlockPos> visited, int distance, Map<Connection, Integer> connections) {
        Block block = level.getBlockState(pos).getBlock();
        if (block instanceof AbstractCableBlock cableBlock) {
            for (Direction d: Direction.values()) {
                if (cableBlock.isConnected(level, pos, d) && !cableBlock.isExtracting(level, pos, d)) {
                    BlockPos p = pos.relative(d);
                    Connection con = new Connection(p, d.getOpposite(), cableBlock.getConnection(level, pos, d), 0);
                    if (level.getBlockState(p).getBlock() instanceof AbstractCableBlock) {
                        if (!visited.contains(p) && !queue.containsKey(p)) {
                            queue.put(p, distance + 1);
                        }
                    } else {
                        if (!connections.containsKey(con)) {
                            connections.put(con, distance);
                        } else if (connections.get(con) > distance){
                            connections.put(con, distance);
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        CompoundTag isLockedStore = new CompoundTag();
        isLockedStore.putBoolean("north",isLocked.get(Direction.NORTH));
        isLockedStore.putBoolean("south",isLocked.get(Direction.SOUTH));
        isLockedStore.putBoolean("east" ,isLocked.get(Direction.EAST ));
        isLockedStore.putBoolean("west" ,isLocked.get(Direction.WEST ));
        isLockedStore.putBoolean("down" ,isLocked.get(Direction.DOWN ));
        isLockedStore.putBoolean("up"   ,isLocked.get(Direction.UP   ));
        tag.put("isLocked", isLockedStore);
    }
    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        CompoundTag isLockedStore = tag.getCompound("isLocked");
        isLocked.put(Direction.NORTH, isLockedStore.getBoolean("north"));
        isLocked.put(Direction.SOUTH, isLockedStore.getBoolean("south"));
        isLocked.put(Direction.EAST , isLockedStore.getBoolean("east" ));
        isLocked.put(Direction.WEST , isLockedStore.getBoolean("west" ));
        isLocked.put(Direction.DOWN , isLockedStore.getBoolean("down" ));
        isLocked.put(Direction.UP   , isLockedStore.getBoolean("up"   ));
    }

    public boolean getIsLocked(Direction dir) {
        return isLocked.get(dir);
    }

    public void setLocked(Direction dir, boolean toLock) {
        if (!level.isClientSide()) {
            isLocked.put(dir, toLock);
        }
    }

    public abstract MenuConstructor getServerContainer(BlockPos pos, Direction face);
}
