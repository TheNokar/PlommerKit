package net.plommer.PlommerKits.Commands;

import net.plommer.PlommerKits.PlommerKits;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandHandler implements CommandExecutor {
	private PlommerKits plugin;
	public CommandHandler(PlommerKits plugin) {
		this.plugin = plugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {
		if (args.length == 0)
			args = new String[]{"help"};
		outer:
		for (BaseCommand command : plugin.commands.toArray(new BaseCommand[0])) {
			String[] cmds = command.name.split(" ");
			for (int i = 0; i < cmds.length; i++)
				if (i >= args.length || !cmds[i].equalsIgnoreCase(args[i])) continue outer;
			return command.run(plugin, sender, commandLabel, args);
		}
		return true;
	}
}
