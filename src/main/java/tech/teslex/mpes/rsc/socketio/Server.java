package tech.teslex.mpes.rsc.socketio;

import cn.nukkit.command.ConsoleCommandSender;
import cn.nukkit.plugin.Plugin;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import tech.teslex.mpes.rsc.console.events.ConsoleUpdate;
import tech.teslex.mpes.rsc.socketio.models.RunCommand;

public class Server {

	private String host;
	private int port;

	private Plugin plugin;

	private SocketIOServer server;

	public Server(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public Server(String host, int port, Plugin plugin) {
		this.host = host;
		this.port = port;
		this.plugin = plugin;
	}

	public void start() {
		Configuration config = new Configuration();
		config.setHostname(host);
		config.setPort(port);

		server = new SocketIOServer(config);

		server.addConnectListener(socketIOClient -> {
			if (socketIOClient.getHandshakeData().getUrlParams().containsKey("test"))
				plugin.getLogger().info("Connected: " + socketIOClient.getRemoteAddress());
			else
				socketIOClient.disconnect();
		});

		server.addDisconnectListener(socketIOClient -> {
			plugin.getLogger().info("Disconnected: " + socketIOClient.getRemoteAddress());
		});

		server.addEventListener("command", RunCommand.class, (socketIOClient, runCommand, ackRequest) -> {
			cn.nukkit.Server.getInstance().getScheduler().scheduleTask(new Runnable() {
				@Override
				public void run() {
					cn.nukkit.Server.getInstance().dispatchCommand(new ConsoleCommandSender(), runCommand.getCommand());
				}
			});

		});

		new ConsoleUpdate(text -> {
			server.getBroadcastOperations().sendEvent("console", text);
		}).start();

		plugin.getLogger().info("Starting ws server..");
		server.startAsync();
	}

	public void stop() {
		plugin.getLogger().info("Stopping ws server..");
		server.stop();
	}

	public SocketIOServer getServer() {
		return server;
	}
}
