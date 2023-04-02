/**
 * The code of this mod element is always locked.
 *
 * You can register new events in this class too.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser -> New... and make sure to make the class
 * outside com._48panda.colorfulbiomes as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
 *
 * This class will be added in the mod root package.
*/
package com._48panda.tech;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.state.BlockState;
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
	public static Boolean isConnected(Level world, BlockPos pos, Direction facing) {
		ResourceLocation myTagId = new ResourceLocation("forge", "electrocution_energy_cable_connect_any_side");
		ResourceLocation back = new ResourceLocation("forge", "electrocution_energy_cable_connect_back");
		BlockState toCompare = world.getBlockState(pos.relative(facing));
		if (toCompare.is(BlockTags.create(myTagId))) {
			return true;
		}else if (toCompare.is(BlockTags.create(back)) && toCompare.getValue(BlockStateProperties.HORIZONTAL_FACING) == facing) {
			return true;
		}
		return false;
	}
	public static int getEnergy(Level level, BlockPos pos) {
		AtomicInteger _retval = new AtomicInteger(0);
		BlockEntity _ent = level.getBlockEntity(pos);
		if (_ent != null)
			_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> _retval.set(capability.getEnergyStored()));
		return _retval.get();
	}
	public static int getEnergy(BlockEntity _ent) {
		AtomicInteger _retval = new AtomicInteger(0);
		if (_ent != null)
			_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> _retval.set(capability.getEnergyStored()));
		return _retval.get();
	}
	public static void takeEnergy(Level world, BlockPos pos, int amount) {
		BlockEntity _ent = world.getBlockEntity(pos);
		int _amount = (int) amount;
		if (_ent != null)
			_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
	}
	public static void takeEnergy(BlockEntity _ent, int amount) {
		int _amount = (int) amount;
		if (_ent != null)
			_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
	}
	public static void sendEnergy(Level world, BlockPos pos, int amount) {
		BlockEntity _ent = world.getBlockEntity(pos);
		int _amount = (int) amount;
		if (_ent != null)
			_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.receiveEnergy(_amount, false));
	}
	public static void sendEnergy(BlockEntity _ent, int amount) {
		int _amount = (int) amount;
		if (_ent != null)
			_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.receiveEnergy(_amount, false));
	}
	public static void transfer(LevelAccessor world, BlockPos from, BlockPos dest, double amount) {
		double a = 0;
		double b = 0;
		double num = 0;
		double max = 0;
		max = amount;
		a = new Object() {
			public int extractEnergySimulate(LevelAccessor level, BlockPos pos, int _amount) {
				AtomicInteger _retval = new AtomicInteger(0);
				BlockEntity _ent = level.getBlockEntity(pos);
				if (_ent != null)
					_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> _retval.set(capability.extractEnergy(_amount, true)));
				return _retval.get();
			}
		}.extractEnergySimulate(world, from, (int) max);
		b = new Object() {
			public int receiveEnergySimulate(LevelAccessor level, BlockPos pos, int _amount) {
				AtomicInteger _retval = new AtomicInteger(0);
				BlockEntity _ent = level.getBlockEntity(pos);
				if (_ent != null)
					_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
				return _retval.get();
			}
		}.receiveEnergySimulate(world, dest, (int) max);
		if (a < b) {
			num = a;
		} else {
			num = b;
		}
		{
			BlockEntity _ent = world.getBlockEntity(from);
			int _amount = (int) num;
			if (_ent != null)
				_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
		}
		{
			BlockEntity _ent = world.getBlockEntity(dest);
			int _amount = (int) num;
			if (_ent != null)
				_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.receiveEnergy(_amount, false));
		}
	}
	public static void transfer(LevelAccessor world, BlockEntity from, BlockPos dest, double amount) {
		double a = 0;
		double b = 0;
		double num = 0;
		double max = 0;
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
		if (a < b) {
			num = a;
		} else {
			num = b;
		}
		{
			BlockEntity _ent = from;
			int _amount = (int) num;
			if (_ent != null)
				_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
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
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(gis, "UTF-8"));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                outStr.append(line);
            }
        } else {
            outStr.append(byteCompressed);
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
