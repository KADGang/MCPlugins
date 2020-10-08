package catchgame.catchgame.gameTools;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class guideBall extends BukkitRunnable {

    private final JavaPlugin plugin;
    private final List<String> IDList;
    String desperadoID = "";

    Player My, target;
    final int COUNT = 5;

    public guideBall(String targetID, List<String> IDList, JavaPlugin plugin) {
        this.desperadoID = targetID;
        this.IDList = IDList;
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (String playerID : IDList) {
            if (!playerID.equals(desperadoID)) {
                setGuide(playerID, desperadoID);
            }
        }
    }

    private void setGuide(String catcherID, String targetID) {
        My = plugin.getServer().getPlayer(catcherID);
        target = plugin.getServer().getPlayer(targetID);

        Location a = My.getLocation();
        Location b = target.getLocation();
        World.Environment WE = target.getWorld().getEnvironment();
        Color ColoR = Color.YELLOW;
        double distance = Math.sqrt((a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY()) + (a.getZ() - b.getZ()) * (a.getZ() - b.getZ()));
        double disX = a.getX() + 7 * (b.getX() - a.getX()) / distance,
                disY = 1.5 + a.getY() + 7 * (b.getY() - a.getY()) / distance,
                disZ = a.getZ() + 7 * (b.getZ() - a.getZ()) / distance;
        switch (WE) {
            case NORMAL:
                ColoR = Color.WHITE;
                break;
            case NETHER:
                ColoR = Color.RED;
                break;
            case THE_END:
                ColoR = Color.PURPLE;
                break;
        }
        Location spawnLoc = new Location(My.getWorld(), disX, disY, disZ);
        if (distance > 15)
            My.getWorld().spawnParticle(Particle.REDSTONE, spawnLoc, COUNT, 0, 0, 0, new Particle.DustOptions(ColoR, 5));
    }
}
