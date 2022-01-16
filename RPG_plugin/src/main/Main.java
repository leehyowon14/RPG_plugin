package main;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

//import net.md_5.bungee.api.ChatMessageType;

public class Main extends JavaPlugin{
	static boolean Plugin_On = false;
	public static Main instance;
	
	@Override
	public void onEnable(){
		
		instance = this;
		Bukkit.getPluginManager().registerEvents(new JoinMsg(), this);
		Bukkit.getPluginManager().registerEvents(new BlockEvent(), this);
		Bukkit.getPluginManager().registerEvents(new EntityEvent(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerEvent(), this);
		Bukkit.getPluginManager().registerEvents(new Skill(), this);
		Bukkit.getPluginManager().registerEvents(new ClassSelec(), this);
		
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getMainScoreboard();
		
		if (board.getObjective("mp") == null) { //마나/스테미나 스코어보드 없을때 만들기
			board.registerNewObjective("mp", "dummy");
		}
		if (board.getObjective("max_mp") == null) {
			board.registerNewObjective("max_mp", "dummy");
		}
		if (board.getObjective("stamina") == null) {
			board.registerNewObjective("stamina", "dummy");
		}
		if (board.getObjective("max_stamina") == null) {
			board.registerNewObjective("max_stamina", "dummy");
		}
		
		
		if (board.getTeam("swordman") == null) { //클래스 팀 없다면 만들기
			board.registerNewTeam("swordman");
		}
		if (board.getTeam("Archer") == null) {
			board.registerNewTeam("Archer");
		}
		
		
		Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "RPG Plug-in loaded ( Developed by. sG.wolf )");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (command.getName().equalsIgnoreCase("RPG")) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED + "명령어의 형식이 알맞지 않습니다.");
			}
			
			else if (args[0].equalsIgnoreCase("start")) {
				if (!(sender.isOp())) {
					sender.sendMessage("OP전용 커맨드입니다.");
					return false;
				}
				if (Plugin_On) {
					sender.sendMessage("RPG plugin is already Enabled.");
					return false;
				}
				sender.sendMessage(ChatColor.BLUE + "RPG plug-in ON!");
				Plugin_On = true;
				
				ScoreboardManager manager = Bukkit.getScoreboardManager();
				Scoreboard board = manager.getMainScoreboard();
				
				Objective MP_obj = board.getObjective("mp"); //마나
				Objective MP_max_obj = board.getObjective("max_mp");
				
				Objective ST_obj = board.getObjective("stamina"); //스테미나
				Objective ST_max_obj = board.getObjective("max_stamina");
				
				
				Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
					
					@Override
					public void run() {
						for(Player p : Bukkit.getOnlinePlayers()) {
							if (board.getEntryTeam(p.getName()).getName().equals("Archer")) {
								Score score = MP_obj.getScore(p.getName());
								if (score.getScore() != MP_max_obj.getScore(p.getName()).getScore()) {
									score.setScore(score.getScore() + 1);
								}
								//p.sendMessage(ChatMessageType.ACTION_BAR + "MP:" + score.getScore() + ChatColor.BLUE);
							}
							if (board.getEntryTeam(p.getName()).getName().equals("swordman")) {
								Score score = ST_obj.getScore(p.getName());
								if (score.getScore() != ST_max_obj.getScore(p.getName()).getScore()) {
									score.setScore(score.getScore() + 1);
								}
								//p.sendMessage(ChatMessageType.ACTION_BAR + "Stamina:" + score.getScore() + ChatColor.BLUE);
							}
						}
						
					}
				}, 0, 10);
			}
			
			else if (args[0].equalsIgnoreCase("stop")) {
				if (!(sender.isOp())) {
					sender.sendMessage("OP전용 커맨드입니다.");
					return false;
				}
				if (!(Plugin_On)) {
					sender.sendMessage("RPG plugin is already Disabled.");
					return false;
				}
				sender.sendMessage(ChatColor.RED + "RPG plug-in OFF.");
				Plugin_On = false;
				Bukkit.getScheduler().cancelTasks(this);
			}
			
			else if (args[0].equalsIgnoreCase("info")) {
				sender.sendMessage(ChatColor.BLUE + "Developer: sG.wolf");
				sender.sendMessage(ChatColor.RED + "Version: 0.1(Beta)");
			}
			return false;
		}
		
		if (command.getName().equalsIgnoreCase("class")) {
			if (Plugin_On) {
				ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
				ItemMeta meta = item.getItemMeta(); 
				
				meta.setDisplayName(ChatColor.BLUE + "근거리");
				meta.setLore(Arrays.asList("'근거리'를 선택합니다", "", "기본템", "목검", "빵(16개)"));
				item.setItemMeta(meta);
				
				Inventory ClassSelector = Bukkit.createInventory(null, 9, "전투방식을 선택하여주세요.");
				ClassSelector.setItem(2,item);
				
				item = new ItemStack(Material.BOW);
				meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.BLUE + "장거리");
				meta.setLore(Arrays.asList("'장거리'를 선택합니다", "", "기본템", "낡은 활", "화살(32개)", "빵(16개)"));
				item.setItemMeta(meta);
				
				ClassSelector.setItem(4, item);
				Player p = (Player) sender;
				
				p.openInventory(ClassSelector);
				return false;
			}
			sender.sendMessage("플러그인이 꺼져있습니다.");
			return false;
			
		}
		if (command.getName().equalsIgnoreCase("trash")) {
			if (Plugin_On) {
				Inventory Trash = Bukkit.createInventory(null, 9, "쓰레기통");
				
				Player p = (Player) sender;
				
				p.openInventory(Trash);
				return false;
			}
			sender.sendMessage("플러그인이 꺼져있습니다.");
			return false;
		}
		return true;
	}
	
	@Override
	public void onDisable(){
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Plug-in unloaded ( Developed by. sG.wolf )");
	}
}