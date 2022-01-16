package main;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockEvent implements Listener {
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if (Main.Plugin_On) {
			Player p = e.getPlayer();
			if (!(p.isOp())) {
				e.setCancelled(true);
				p.sendMessage(ChatColor.RED + "블럭 파괴는 허용되지 않습니다.");
			}
		}
	}

	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if (Main.Plugin_On) {
			Player p = e.getPlayer();
			if (!(p.isOp())) {
				e.setCancelled(true);
				p.sendMessage(ChatColor.RED + "블럭 설치는 허용되지 않습니다.");
			}
		}
	}

	@EventHandler
	public void onBlockBurn(BlockBurnEvent e) { // 불 이벤트
		if (Main.Plugin_On) {
			e.setCancelled(true);
		}
	}

	@EventHandler // 작동 안하는듯
	public void onBlockExplode(BlockExplodeEvent e) { //폭발 이벹느
		if (Main.Plugin_On) {
			System.out.println("asdf");
			e.setCancelled(true);
		}
	}
}