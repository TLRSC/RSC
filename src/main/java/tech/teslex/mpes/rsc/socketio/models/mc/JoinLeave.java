package tech.teslex.mpes.rsc.socketio.models.mc;

public class JoinLeave {

	private String name;

	public JoinLeave(String name) {
		this.name = name;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
