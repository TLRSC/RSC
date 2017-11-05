package tech.teslex.mpes.rsc.console;

import cn.nukkit.command.CommandSender;
import tech.teslex.mpes.rsc.console.events.console.ConsoleUpdate;
import tech.teslex.mpes.rsc.console.events.console.ConsoleUpdateEvent;

public class Console {

	private static ConsoleUpdate consoleUpdate;

	public static void startTailing() {
		consoleUpdate = new ConsoleUpdate("server.log");
		consoleUpdate.start();
	}

	public static void addUpdateListener(ConsoleUpdateEvent consoleUpdateEvent) {
		consoleUpdate.addUpdateListener(consoleUpdateEvent);
	}

	public static void dispachCommand(CommandSender commandSender, String cmd) {
		cn.nukkit.Server.getInstance().getScheduler().scheduleTask(() -> cn.nukkit.Server.getInstance().dispatchCommand(commandSender, cmd));
	}

}
