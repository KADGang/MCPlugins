package aitest.aipettest;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Cow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.plugin.java.JavaPlugin;

public final class AIPetTest extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("AIPet v1.0已加载");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("AIPet v1.0已卸载");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("callPet")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("这个指令只能让玩家使用。");
            } else {
                Player cmdSender = (Player) sender;
                World world = cmdSender.getWorld();
                Wolf wolf = (Wolf) world.spawnEntity(cmdSender.getLocation(), EntityType.WOLF);
                wolf.setOwner(cmdSender);
                wolf.setCustomNameVisible(true);
                wolf.setCustomName(cmdSender.getName() + "的狼");
            }
            return true;
        }
        return false;
    }
}
