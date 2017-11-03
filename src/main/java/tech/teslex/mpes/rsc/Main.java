package tech.teslex.mpes.rsc;

import cn.nukkit.plugin.PluginBase;
import com.google.gson.Gson;
import tech.teslex.mpes.rsc.config.Config;
import tech.teslex.mpes.rsc.socketio.Server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main extends PluginBase {

	private Server s;
	
	private Config config;

	@Override
	public void onEnable() {
		config = loadCfg();

		s = new Server(config.getHost(), config.getPort(), this);
		s.start();
	}

	@Override
	public void onDisable() {
		s.stop();
	}


	private Config loadCfg() {
		saveResource("config.json");
		Gson gson = new Gson();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(getDataFolder() + "/config.json"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return gson.fromJson(br, Config.class);
	}

}
