package tech.teslex.mpes.rsc.mc.events;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import tech.teslex.mpes.rsc.Main;
import tech.teslex.mpes.rsc.socketio.models.mc.JoinLeave;
import tech.teslex.mpes.rsc.socketio.models.mc.Message;

public class EventListener implements Listener {

	@EventHandler
	private void onChat(PlayerChatEvent e) {
		Main.getIOServer().getServer().getBroadcastOperations().sendEvent(
				"chat",
				new Message(e.getPlayer().getName(), e.getMessage())
		);
	}

	@EventHandler
	private void onJoin(PlayerJoinEvent e) {
		Main.getIOServer().getServer().getBroadcastOperations().sendEvent(
				"player_join",
				new JoinLeave(e.getPlayer().getName())
		);
	}

	@EventHandler
	private void onLeave(PlayerQuitEvent e) {
		Main.getIOServer().getServer().getBroadcastOperations().sendEvent(
				"player_quit",
				new JoinLeave(e.getPlayer().getName())
		);
	}

}
