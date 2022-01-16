package main;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinMsg implements Listener { //조인메세지
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		e.setJoinMessage(ChatColor.AQUA + "RPG서버에 오신것을 환영합니다.\nPlugin Developer: sG.wolf#7777");
	}
}
