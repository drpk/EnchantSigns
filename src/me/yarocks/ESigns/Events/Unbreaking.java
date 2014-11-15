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
public class Unbreaking implements Listener {


	private Main c;

	public Unbreaking(Main c){
		this.c = c;
	}

	@EventHandler
	public void onSignChange(SignChangeEvent e) {
		Player p = e.getPlayer();
		if (e.getLine(0).equalsIgnoreCase("[bbunb]")) { //Capitalization matters for this.
			if (p.hasPermission("bb.admin.signmake")) { //If the player has that permission, it'll set the line, else, it won't, that will be good so people can't exploit a glitch, although,
//a colored signs plugin ex: Essentials can conflict if you use it, and give the permission to reg players, I suggest not to.
				e.setLine(0, ChatColor.BLACK + "[" + ChatColor.DARK_RED + "ESigns" + ChatColor.BLACK + "]"); //Capitalization matters for this too.=
				e.setLine(1, ChatColor.GOLD + "Unbreaking");
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
					if (sign.getLine(1).equalsIgnoreCase(ChatColor.GOLD + "Unbreaking")) {
						int levelToIncrease = 0;

						int multiplier = c.getConfig().getInt("Multiplier");

						ItemStack item = p.getItemInHand();


						if (item != null) {
							double money = 1;
							try {
								p.sendMessage(String.valueOf(money));
								money = Double.parseDouble(sign.getLine(3).replace('$', ' '));
								p.sendMessage(String.valueOf(money));
								if (multiplier != 0) {
									money = money * (item.getEnchantmentLevel(Enchantment.DURABILITY) * multiplier);
									p.sendMessage(String.valueOf(money));
								}
							} catch (NumberFormatException exception) {
								exception.printStackTrace();
							}
							try {
								levelToIncrease = Integer.parseInt(sign.getLine(2));
								p.sendMessage(String.valueOf(money));
							} catch (NumberFormatException exception) {
								exception.printStackTrace();
								p.sendMessage("§4An error ocurred, please ");
							}


							if (item != null) { // So you don't get errors in the console about NullErrorException
								if (new GetTool(c).isEnchantable(p)) {
									p.sendMessage(String.valueOf(money));

									EconomyResponse transaction = Main.economy.withdrawPlayer(p, money);

									if (!item.containsEnchantment(Enchantment.DURABILITY)) {
										p.sendMessage(String.valueOf(money));
										if (transaction.transactionSuccess()) {
											p.sendMessage("§c$" + money + " has been taken from your account");
											p.sendMessage(String.valueOf(money));

											item.addUnsafeEnchantment(Enchantment.DURABILITY, levelToIncrease);
											p.updateInventory();
										} else {
											p.sendMessage("§4Get more money!");
										}
									} else {
										p.sendMessage(String.valueOf(money));
										if (transaction.transactionSuccess()) {
											p.sendMessage(String.valueOf(money));
											item.addUnsafeEnchantment(Enchantment.DURABILITY, item.getEnchantmentLevel(Enchantment.DURABILITY) + levelToIncrease);
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
}
