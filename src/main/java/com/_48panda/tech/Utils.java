package com._48panda.tech;

import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import net.minecraft.world.level.block.Rotation;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.minecraft.network.chat.ChatType;
import net.minecraft.Util;
import net.minecraft.network.chat.TextComponent;

import java.io.IOException;



public class Utils {
	public Utils() {
	}
	public static Rotation getRotationFromNorth(Direction to) {
		return switch (to) {
			case SOUTH -> Rotation.CLOCKWISE_180;
			case EAST -> Rotation.CLOCKWISE_90;
			case WEST -> Rotation.COUNTERCLOCKWISE_90;
			default -> Rotation.NONE;
		};
	}
	public static Rotation getOpposite(Rotation toInvert) {
		if (toInvert == Rotation.CLOCKWISE_90 || toInvert == Rotation.COUNTERCLOCKWISE_90) {
			return toInvert.getRotated(Rotation.CLOCKWISE_180);
		}
		return toInvert;
	}
	public static Rotation getRotation(Direction from, Direction to) {
		Rotation from_rot = getRotationFromNorth(from);
		Rotation to_rot = getRotationFromNorth(to);
		return from_rot.getRotated(getOpposite(to_rot));
	}
	public static int getEnergy(BlockEntity _ent) {
		AtomicInteger _retval = new AtomicInteger(0);
		if (_ent != null)
			_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> _retval.set(capability.getEnergyStored()));
		return _retval.get();
	}
	public static void transfer(LevelAccessor world, BlockEntity from, BlockPos dest, double amount) {
		double a;
		double b;
		double num;
		double max;
		max = amount;
		a = new Object() {
			public int extractEnergySimulate(BlockEntity _ent, int _amount) {
				AtomicInteger _retval = new AtomicInteger(0);
				if (_ent != null)
					_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> _retval.set(capability.extractEnergy(_amount, true)));
				return _retval.get();
			}
		}.extractEnergySimulate(from, (int) max);
		b = new Object() {
			public int receiveEnergySimulate(LevelAccessor level, BlockPos pos, int _amount) {
				AtomicInteger _retval = new AtomicInteger(0);
				BlockEntity _ent = level.getBlockEntity(pos);
				if (_ent != null)
					_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
				return _retval.get();
			}
		}.receiveEnergySimulate(world, dest, (int) max);
		num = Math.min(a,b);
		{
			int _amount = (int) num;
			if (from != null)
				from.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
		}
		{
			BlockEntity _ent = world.getBlockEntity(dest);
			int _amount = (int) num;
			if (_ent != null)
				_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.receiveEnergy(_amount, false));
		}
	}
	public static String decompress(String compressedString) throws IOException {
        byte[] byteCompressed = Base64.getDecoder().decode(compressedString);
        final StringBuilder outStr = new StringBuilder();
        if ((byteCompressed == null) || (byteCompressed.length == 0)) {
            return "";
        }
        if (isCompressed(byteCompressed)) {
            final GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(byteCompressed));
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(gis, StandardCharsets.UTF_8));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                outStr.append(line);
            }
        } else {
            outStr.append(Arrays.toString(byteCompressed));
        }
        return outStr.toString();
    }
    public static boolean isCompressed(final byte[] compressed) {
        return (compressed[0] == (byte) (GZIPInputStream.GZIP_MAGIC)) && (compressed[1] == (byte) (GZIPInputStream.GZIP_MAGIC >> 8));
    }
    public static void chat(LevelAccessor world, String message) {
		if (!world.isClientSide()) {
			MinecraftServer _mcserv = ServerLifecycleHooks.getCurrentServer();
			if (_mcserv != null)
				_mcserv.getPlayerList().broadcastMessage(new TextComponent(message), ChatType.SYSTEM, Util.NIL_UUID);
		}
	}


}
