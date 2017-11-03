package tech.teslex.mpes.rsc;

import cn.nukkit.plugin.PluginBase;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.google.gson.Gson;
import tech.teslex.mpes.rsc.config.Config;
import tech.teslex.mpes.rsc.socketio.Server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class Main extends PluginBase {

	private Server s;

	private Config config;

	private String sign = new Random().doubles().toString();

	@Override
	public void onEnable() {
		config = loadCfg();

		s = new Server(getServer().getIp(), config.getPort(), this);
		s.start();

		try {
			Algorithm algorithm = Algorithm.HMAC256(sign);
			String token = JWT.create()
					.withClaim("host", config.getHost())
					.withClaim("port", config.getPort())
					.withClaim("secret", config.getSecret())
					.sign(algorithm);
			getLogger().info("§eYOUR TOKEN: §a" + token);
		} catch (UnsupportedEncodingException | JWTCreationException exception){
			exception.printStackTrace();
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

}
