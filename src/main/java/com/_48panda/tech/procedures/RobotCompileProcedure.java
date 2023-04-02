package com._48panda.tech.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.client.gui.components.EditBox;

import com._48panda.tech.Utils;
import java.util.HashMap;
import java.io.IOException;

import com.google.gson.JsonParser;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.Iterator;

import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.StringTag;

public class RobotCompileProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, HashMap guistate) {
		if (guistate == null)
			return;
		String inputString = "";
		BlockState me = Blocks.AIR.defaultBlockState();
		inputString = guistate.containsKey("text:program") ? ((EditBox) guistate.get("text:program")).getValue() : "";
		BlockPos pos = new BlockPos(x, y, z);
		me = (world.getBlockState(pos));
		try {
			String output = 
Utils.decompress(inputString);
			JsonArray jsonFull = JsonParser.parseString(output).getAsJsonArray();
			JsonArray json = jsonFull.get(1).getAsJsonArray();
			JsonArray strings = jsonFull.get(0).getAsJsonArray();
			BlockEntity self = world.getBlockEntity(pos);
			CompoundTag data = self.getTileData();
			ListTag list = new ListTag();
			Iterator<JsonElement> json_iterator1 = json.iterator();
			while (json_iterator1.hasNext()) {
				JsonArray instruction = json_iterator1.next().getAsJsonArray();
				ListTag InnerArray = new ListTag();
				Iterator<JsonElement> json_iterator2 = instruction.iterator();
				while (json_iterator2.hasNext()) {
					InnerArray.add(DoubleTag.valueOf(json_iterator2.next().getAsDouble()));
				}
				list.add(InnerArray);
			}
			ListTag stringMap = new ListTag();
			Iterator<JsonElement> json_iterator3 = strings.iterator();
			while(json_iterator3.hasNext()) {
				String item = json_iterator3.next().getAsString();
				stringMap.add(StringTag.valueOf(item));
			}
			data.put("strings", stringMap);
			data.put("program", list);
			data.putInt("program_counter", 0);
			data.putInt("cooldown", 0);
			data.putBoolean("isRunning", true);
			System.out.print(output);
		} catch(IOException e) {
			Utils.chat(world, "There was an error procesing the data");
		} catch(IllegalArgumentException e) {
			Utils.chat(world, "There was an error procesing the data");
		} catch(Exception e) {
		Utils.chat(world, "The JSON was malformed.");
		}
		
		
	}
}
