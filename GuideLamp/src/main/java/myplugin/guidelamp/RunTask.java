package myplugin.guidelamp;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.Particle.DustOptions

public class RunTask extends BukkitRunnable {
    final int LENTH=10;
    Location My,target;

    public RunTask(Location my, Location target) {
        My = my;
        this.target = target;
    }

    @Override
    public void run() {
        ParticleEffect.REDSTONE.display(new ParticleEffect.OrdinaryColor(Color.RED), loc, 50);
    }
}
