package net.plommer.PlommerKits.Commands;

import java.util.ArrayList;
import java.util.List;

import net.plommer.PlommerKits.PlommerKits;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class BaseCommand {
	public PlommerKits plugin;
	public CommandSender sender;
	public String cmd;
	public String name;
	
	public List<String> args = new ArrayList<String>();
	public Player player;
	public int argLength = 0;
	public String usage;
	public boolean bePlayer = true;
	public String usedCmd;
	public String permission;
	
	public boolean run(PlommerKits k, CommandSender sender, String cmd, String[] preArgs) {
		
		this.plugin = k;
		this.sender = sender;
		this.usedCmd = cmd;
		
		args.clear();
		for(String arg : preArgs) {
			args.add(arg);
		}
		
        for (int i = 0; i < name.split(" ").length && i < args.size(); i++) {
            args.remove(0);	
        }

		
		if(argLength > args.size()) {
			sendUsage();
			return false;
		}
		
		if(!(sender instanceof Player) && bePlayer) {
			return false;
		}
		if(sender instanceof Player) {
			this.player = (Player)sender;
		}
		
		if(!sender.hasPermission(permission)) {
			//Utils.msg(sender, "&cYou don't have permission to do that!");
			return false;
		}
		
		return execute();		
	}
	
	public abstract boolean execute();
	
	public void sendUsage() {
		//Utils.msg(sender, "&c/" + this.usedCmd + " " +this.usage);
	}
	
	public String permission() {
		return "thepit." + this.permission;
	}
}
