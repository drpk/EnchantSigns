package me.yarocks.ESigns;

import me.yarocks.ESigns.Commands.InfoCommand;
import me.yarocks.ESigns.Events.*;
import me.yarocks.ESigns.Util.GetTool;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

/**
 * Created by User Name on 9/16/2014.
 */
public class Main extends JavaPlugin {
	List<String> list = Arrays.asList("DIAMOND_PICKAXE", "GOLD_PICKAXE");
	public static Economy economy = null;
	public void onEnable() {
		new GetTool(this);
		getConfig().addDefault("unsafe-enchants", true);
		getConfig().addDefault("Multiplier", Integer.valueOf(0));
		getConfig().options().copyDefaults(true);
		PluginManager pm = Bukkit.getServer().getPluginManager();
		//TODO: REGISTER EVENTS
		// TODO: EXECUTE COMMANDS
		getCommand("Esigns").setExecutor(new InfoCommand(this));
		pm.registerEvents(new SilkTouch(this), this);
		pm.registerEvents(new Fortune(this), this);
		pm.registerEvents(new Unbreaking(this), this);
		pm.registerEvents(new Efficiency(this), this);
		getConfig().addDefault("Enchantable-Items", list);
		saveConfig();
		print();
		setupEconomy();

	}

	private Boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null) {
			economy = economyProvider.getProvider();
		}
		return true;
	}
	public void onDisable(){
		ConsoleCommandSender css = Bukkit.getConsoleSender();
		css.sendMessage("§c" + getDescription().getFullName() + " Has Been Disabled!");
	}
	public void print(){
		ConsoleCommandSender css = Bukkit.getConsoleSender();
		css.sendMessage("§a" + getDescription().getFullName() + " Has Been Enabled!");
	}

}
