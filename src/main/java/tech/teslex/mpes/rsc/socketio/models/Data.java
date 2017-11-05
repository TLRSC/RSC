package tech.teslex.mpes.rsc.socketio.models;

import java.util.ArrayList;
import java.util.List;

public class Data {

	private List<String> onlinePlayers = new ArrayList<>();

	public Data(List<String> onlinePlayers) {
		this.onlinePlayers = onlinePlayers;
	}

	public List<String> getOnlinePlayers() {

		return onlinePlayers;
	}

	public void setOnlinePlayers(List<String> onlinePlayers) {
		this.onlinePlayers = onlinePlayers;
	}
}
