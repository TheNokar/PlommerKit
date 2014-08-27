package net.plommer.PlommerKits.Commands;

import net.plommer.PlommerKits.Kits;
import net.plommer.PlommerKits.PlommerKits;
import net.plommer.PlommerKits.Utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KitsCommand implements CommandExecutor {
	
	public PlommerKits plugin;
	public String permissions = "plommerkits.";
	public KitsCommand(PlommerKits plugin) {
		this.plugin = plugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if(args[0] != null && sender instanceof Player) {
			Player player = (Player)sender;
			plugin.getLogger().info(""+plugin.kitsList.size());
			if(plugin.checkIsKit(args[0]) == true) {
				Kits kits = plugin.kitsList.get(args[0]);
				if(!player.hasPermission(permissions + kits.getName())) {
					Utils.sendMessage(sender, plugin.getConfig().getString("msg.permissions"));
					return false;
				}
				for(ItemStack item : kits.getItems()) {
					player.getInventory().addItem(item);
				}
				Utils.sendMessage(sender, plugin.getConfig().getString("msg.getKitmessage").replace("{kit}", kits.getName()));
				return false;
			} else {
				Utils.sendMessage(sender, plugin.getConfig().getString("msg.errorKit"));	
				return false;
			}
		} else {
			return false;
		}
	}
	
}