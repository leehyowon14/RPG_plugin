package main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerEvent implements Listener {
	@EventHandler
	public void onThrowItem(PlayerDropItemEvent e) { // 아이템 떨구기
		if (Main.Plugin_On) {
			Player p = e.getPlayer();
			if (!(p.isOp())) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPlayerArmorStandManipulate(PlayerArmorStandManipulateEvent e) { // 아머스텐드 이벤트
		if (Main.Plugin_On) {
			Player p = e.getPlayer();
			if (!(p.isOp())) {
				e.setCancelled(true);
			}
		}
	}
}
