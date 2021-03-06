package me.yarocks.ESigns.Events;

import me.yarocks.ESigns.Main;
import me.yarocks.ESigns.Util.GetTool;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by User Name on 9/27/2014.
 */
public class Efficiency implements Listener {
	public Main c;

	public Efficiency(Main c){
		this.c=c;
	}
	@EventHandler
	public void onSignChange(SignChangeEvent e) {
		Player p = e.getPlayer();
		if (e.getLine(0).equalsIgnoreCase("[bbeff]")) { //Capitalization matters for this.
			if (p.hasPermission("bb.admin.signmake")) { //If the player has that permission, it'll set the line, else, it won't, that will be good so people can't exploit a glitch, although,
//a colored signs plugin ex: Essentials can conflict if you use it, and give the permission to reg players, I suggest not to.
				e.setLine(0, ChatColor.BLACK + "[" + ChatColor.DARK_RED + "ESigns" + ChatColor.BLACK + "]"); //Capitalization matters for this too.=
				e.setLine(1, ChatColor.GOLD + "Efficiency");
				e.setLine(2, "1");
				e.setLine(3, "$100");
				p.sendMessage(" Successfully made sign!");
			}
		}
	}


	@EventHandler
	public void SignClick(PlayerInteractEvent e) {
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			final Player p = e.getPlayer();
			Block b = e.getClickedBlock();
			Material clicked = b.getType();
			if (clicked == Material.WALL_SIGN || clicked == Material.SIGN_POST) {
				Sign sign = (Sign) b.getState();
				if (sign.getLine(0).equalsIgnoreCase(ChatColor.BLACK + "[" + ChatColor.DARK_RED + "ESigns" + ChatColor.BLACK + "]")) {
					if (sign.getLine(1).equalsIgnoreCase(ChatColor.GOLD + "Efficiency")) {
						int levelToIncrease = 1;

						int multiplier = c.getConfig().getInt("Multiplier");

						ItemStack item = p.getItemInHand();


						if (item != null) {
							double money = 0;
							try {
								money = Double.parseDouble(sign.getLine(3).replace('$', ' '));
								if (multiplier != 0) {
									money = money * (item.getEnchantmentLevel(Enchantment.DIG_SPEED) * multiplier);
								}
							} catch (NumberFormatException exception) {
								exception.printStackTrace();
							}
							try {
								levelToIncrease = Integer.parseInt(sign.getLine(2));
								p.getLocation().getBlock().getTemperature();
							} catch (NumberFormatException exception) {
								exception.printStackTrace();
								p.sendMessage("§4An error ocurred, please ");
							} // So you don't get errors in the console about NullErrorException

							if (new GetTool(c).isEnchantable(p)) {
								EconomyResponse transaction = Main.economy.withdrawPlayer(p, money);
								p.sendMessage(item.getType().toString());
								if (!item.containsEnchantment(Enchantment.DIG_SPEED)) {
									if (transaction.transactionSuccess()) {
										item.addUnsafeEnchantment(Enchantment.DIG_SPEED, levelToIncrease);
										p.updateInventory();
										p.sendMessage("§c$" + money + " has been taken from your account");

									} else {
										p.sendMessage("§4Get more money!");
									}
								} else {
									if (transaction.transactionSuccess()) {
										item.addUnsafeEnchantment(Enchantment.DIG_SPEED, item.getEnchantmentLevel(Enchantment.DIG_SPEED) + levelToIncrease);
										p.updateInventory();
										p.sendMessage("§c$" + money + " has been taken from your account");

									}
								}

							} else {
								p.sendMessage("That Item is not enchantable!");
							}
						}
					}
				}
			}
		}
	}
}