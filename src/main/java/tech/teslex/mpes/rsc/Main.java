package tech.teslex.mpes.rsc;

import cn.nukkit.plugin.PluginBase;
import com.google.gson.Gson;
import tech.teslex.mpes.rsc.config.Config;
import tech.teslex.mpes.rsc.console.Console;
import tech.teslex.mpes.rsc.socketio.Server;
import tech.teslex.mpes.rsc.utils.JWT;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main extends PluginBase {

	private Server s;

	private static Config config;

	private String sign = new Random().doubles().toString();

	@Override
	public void onEnable() {
		config = loadCfg();

		if (config.getSecret().equalsIgnoreCase("0.0.0.0") ||
				config.getSecret().equalsIgnoreCase("CHANGE IT")) {
			getLogger().info("§cERROR: CHANGE DEFAULT PARAMS IN CONFIG");
		} else {
			Console.startTailing();

			s = new Server(getServer().getIp(), config.getPort(), this, config.getSecret());
			s.start();

			Map<String, Object> data = new HashMap<>();
			data.put("host", config.getHost());
			data.put("port", config.getPort());
			data.put("secret", config.getSecret());

			try {
				getLogger().info("§eYOUR TOKEN: §a" + JWT.createToken(sign, data));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				s.stop();
			}

		}
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

	public static Config getPConfig() {
		return config;
	}
}
