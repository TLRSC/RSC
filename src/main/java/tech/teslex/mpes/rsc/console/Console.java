package tech.teslex.mpes.rsc.console;

import cn.nukkit.command.CommandSender;
import tech.teslex.mpes.rsc.console.events.ConsoleUpdate;
import tech.teslex.mpes.rsc.console.events.ConsoleUpdateEvent;

public class Console {

	private ConsoleUpdate consoleUpdate;

	public void startTailing() {
		consoleUpdate = new ConsoleUpdate("server.log");
		consoleUpdate.start();
	}

	public void addUpdateListener(ConsoleUpdateEvent consoleUpdateEvent) {
		consoleUpdate.addUpdateListener(consoleUpdateEvent);
	}

	public static void dispachCommand(CommandSender commandSender, String cmd) {
		cn.nukkit.Server.getInstance().getScheduler().scheduleTask(() -> cn.nukkit.Server.getInstance().dispatchCommand(commandSender, cmd));
	}

}
