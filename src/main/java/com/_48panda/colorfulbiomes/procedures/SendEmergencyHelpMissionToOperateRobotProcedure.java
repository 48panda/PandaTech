package com._48panda.colorfulbiomes.procedures;

import net.minecraft.world.entity.Entity;

public class SendEmergencyHelpMissionToOperateRobotProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			Entity _ent = entity;
			if (!_ent.level.isClientSide() && _ent.getServer() != null)
				_ent.getServer().getCommands().performCommand(_ent.createCommandSourceStack().withSuppressedOutput().withPermission(4),
						"tellraw @s [\"\",{\"text\":\"Robot Help\",\"bold\":true,\"underlined\":true},{\"text\":\"\\nFirst, \"},{\"text\":\"[click here]\",\"color\":\"green\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"48panda.github.io/robot\"}},{\"text\":\" and design a program.\\nThen, Click the Generate program button in the top-left.\\nCopy the string of characters generated.\\nNow, paste the string of characters into the text input on the robot.\\nFinally, Press submit. The robot will now carry out your commands.\"}]");
		}
	}
}
