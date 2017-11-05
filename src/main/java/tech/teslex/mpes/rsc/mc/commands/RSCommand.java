package tech.teslex.mpes.rsc.mc.commands;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

public class RSCommand extends Command {


	public RSCommand(String name, String description, String usageMessage, String[] aliases) {
		super("rsc", "Remote Server Control", "/rsc <cmd>", new String[]{"rsc"});
	}

	@Override
	public boolean execute(CommandSender commandSender, String s, String[] strings) {

		return false;
	}
}
