package catchgame.catchgame.gameTools;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class perspectiveTool extends BukkitRunnable {
    private final JavaPlugin plugin;
    List<String> IDList;

    public perspectiveTool(JavaPlugin plugin, List<String> IDList) {
        this.plugin = plugin;
        this.IDList = IDList;
    }

    @Override
    public void run() {
        for (String playerID: IDList) {
            alarmDis(playerID);
        }
    }

    private void alarmDis(String mainPlayerID) {
        Player mainPlayer = plugin.getServer().getPlayer(mainPlayerID);

        assert mainPlayer != null;
        double mx = mainPlayer.getLocation().getX();
        double mz = mainPlayer.getLocation().getZ();

        double tx;
        double tz;

        for (String playerID: IDList) {
            Player player = plugin.getServer().getPlayer(playerID);
            assert player != null;

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
