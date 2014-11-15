package me.yarocks.ESigns.Commands;

import me.yarocks.ESigns.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by User Name on 9/27/2014.
 */
public class InfoCommand implements CommandExecutor {
	public InfoCommand(Main c) {
		this.c = c;
	}

	private Main c;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		if (command.getName().equalsIgnoreCase("Esigns")){
			sender.sendMessage("§e" + c.getDescription().getFullName()
					+ "§b was made by " + c.getDescription().getAuthors()
					+ "§c the function of this plugin is "
					+c.getDescription().getDescription());
		}
		return false;
	}
}
