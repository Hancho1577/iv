package hancho.nukkit.plugin.iv;

import java.util.HashMap;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.event.player.PlayerInteractEvent.Action;
import cn.nukkit.item.Item;
import cn.nukkit.plugin.PluginBase;

public class iv extends PluginBase implements Listener {
	public HashMap<String, Boolean> bvList = new HashMap<String, Boolean>();

	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equals("iv")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("§l§f[ §c! §f] 인게임에서만 가능합니다.");
				return true;
			}
			Item itemhand = ((Player) sender).getInventory().getItemInHand();
			String code = itemhand.getId() + ":" + itemhand.getDamage();
			sender.sendMessage("§l§f[ §6! §f] 아이템 코드 : " + code);
			return true;
		} else if (cmd.getName().equals("bv")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("§l§f[ §c! §f] 인게임에서만 가능합니다.");
				return true;
			}
			if (this.bvList.containsKey(sender.getName())) {
				this.bvList.remove(sender.getName());
				sender.sendMessage("§l§f[ §6! §f] 블럭 아이템코드가 꺼졌습니다.");
				return true;
			} else {
				this.bvList.put(sender.getName(), true);
				sender.sendMessage("§l§f[ §6! §f] 블럭을 터치하여 아이템코드를 확인할 수 있습니다.");
				return true;
			}
		}
		return true;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent ev) {
		if (this.bvList.containsKey(ev.getPlayer().getName())) {
			if (ev.getAction().equals(Action.RIGHT_CLICK_BLOCK))
				ev.getPlayer().sendMessage("§l§f[ §6! §f] 블럭 정보 : " + ev.getBlock());
		}
	}
}