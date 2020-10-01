package catchgame.catchalarm;

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

                double ans = Math.sqrt((mx - tx) * (mx - tx) + (mz - tz) * (mx - tz));
                if (ans < 15) {
                    if (!player.isGlowing())
                        player.setGlowing(true);
                    if (!mainPlayer.isGlowing())
                        mainPlayer.setGlowing(true);
                    plugin.getServer().broadcastMessage("有玩家距离在水平方向15格子之内了，距离为" + ans);
                } else {
                    player.setGlowing(false);
                    mainPlayer.setGlowing(false);
                }
            }
        }
    }
}
