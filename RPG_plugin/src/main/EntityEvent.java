package main;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityEvent implements Listener {
	@EventHandler
	public void onHit(EntityDamageByEntityEvent event) { // 데미지 이벤트
		Entity damager = event.getDamager();

		if (!(damager instanceof Arrow))
			return;

		event.setDamage(5.0);
	}
}
