package catchgame.catchalarm;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class CatchAlarm extends JavaPlugin {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("setGlowing")) {
            getServer().getPlayer(args[0]).setGlowing(true);
            return true;
        }
        return false;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("CatchAlarm 插件已经加载。");
        BukkitTask task = new calculateDis(this).runTaskTimer(this, 0, 20L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("CatchAlarm 插件已经卸载。");
    }
}
