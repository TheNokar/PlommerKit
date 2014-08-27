package net.plommer.PlommerKits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.plommer.PlommerKits.Commands.BaseCommand;
import net.plommer.PlommerKits.Commands.KitsCommand;

import org.bukkit.plugin.java.JavaPlugin;

public class PlommerKits extends JavaPlugin {

    public List<BaseCommand> commands = new ArrayList<BaseCommand>();
    public LoadConfigs lc = new LoadConfigs(this);
    public BookReader br = new BookReader(this);
    public HashMap<String, Kits> kitsList = new HashMap<String, Kits>();
    
	public void onEnable() {
		getCommand("kits").setExecutor(new KitsCommand(this));
		//Generate the config
		this.getConfig().options().copyHeader(true);
		this.getConfig().options().copyDefaults(true);
		this.getConfig().options().header();
		this.saveConfig();
		for(String name : getConfig().getConfigurationSection("kits").getKeys(false)) {
			lc.LoadConf(name);
		}
	}
	
	public void onDisable() {
		//Do nothing i guess no need to log
	}
	
	public boolean checkIsKit(String test) {
		if(kitsList.containsKey(test)) {
			return true;
		}
		return false;
	}
}
