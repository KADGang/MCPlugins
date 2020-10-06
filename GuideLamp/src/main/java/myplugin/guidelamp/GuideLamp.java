package myplugin.guidelamp;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class GuideLamp extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("onEnable has been invoked!");

    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable has been invoked!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && command.getName().equalsIgnoreCase("GuideLamp") || label.equalsIgnoreCase("GL")) {
            if (args[0].equalsIgnoreCase("to")) {
                Player target = Bukkit.getServer().getPlayer(args[1]);
                BukkitTask Task = new RunTask(((Player)sender).getLocation(),target.getLocation()).runTaskTimer(this, 20L, 20L);
            }
        }
        return false;
    }
}
