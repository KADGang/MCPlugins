package catchgame.catchgame;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class calculateDis extends BukkitRunnable {
    private final JavaPlugin plugin;

    public calculateDis(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player mainPlayer: plugin.getServer().getOnlinePlayers()) {
            alarmDis(mainPlayer);
        }
    }

    private void alarmDis(Player mainPlayer) {
        double mx = mainPlayer.getLocation().getX();
        double mz = mainPlayer.getLocation().getZ();

        double tx;
        double tz;

        for (Player player: plugin.getServer().getOnlinePlayers()) {
            if (!mainPlayer.getName().equals(player.getName())) {
                tx = player.getLocation().getX();
                tz = player.getLocation().getZ();

                if (Math.abs(mx - tx) < 15 && Math.abs(mz - tz) < 15) {
                    if (!mainPlayer.isGlowing())
                        mainPlayer.setGlowing(true);
                } else {
                    mainPlayer.setGlowing(false);
                }
            }
        }
    }
}
