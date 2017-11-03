package tech.teslex.mpes.rsc.socketio.models;

public class RunCommand {

	private String command;

	public RunCommand() {
	}

	public RunCommand(String command) {
		this.command = command;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}
}
