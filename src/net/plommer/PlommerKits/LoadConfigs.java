package net.plommer.PlommerKits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LoadConfigs {

	PlommerKits plugin;
	public LoadConfigs(PlommerKits plugin) {
		this.plugin = plugin;
	}
		
	@SuppressWarnings("deprecation")
	public void LoadConf(String id) {
		HashMap<Enchantment, Integer> effects = new HashMap<Enchantment, Integer>();
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		FileConfiguration config = plugin.getConfig();
		for(String item : config.getStringList("kits." +id + ".items")) {
			String[] iteml = item.split(" ");
			if(iteml[0].equalsIgnoreCase("book")) {
				items.add(plugin.br.loadBook(iteml[1]));
			} else {
				ItemStack iteme = new ItemStack(Material.getMaterial(Integer.parseInt(iteml[0])), Integer.parseInt(iteml[1]));
				ItemMeta meta = iteme.getItemMeta();
				for(String itemn : iteml) {
					if(itemn.contains(":")) {
						String[] name = itemn.split(":");
						if(name[0].equalsIgnoreCase("name")) {
							meta.setDisplayName(Utils.buildString(name[1], new HashMap<String, String>()));
						} else {
							try {
								Enchantment test = Enchantment.getByName(name[0].toUpperCase());
								effects.put(test, Integer.parseInt(name[1]));
							} catch(NumberFormatException e) {
								e.printStackTrace();
								plugin.getLogger().log(Level.WARNING, "Error wrong enchantment");
							}
						}
						iteme.setItemMeta(meta);
					}
				}
				if(effects.size() != 0) {
					iteme.addUnsafeEnchantments(effects);
					items.add(iteme);
				}
			}
			plugin.kitsList.put(id, new Kits(items, id));
		}
	}
	
}
