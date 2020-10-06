package myplugin.guidelamp;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.world.WorldEvent;
import org.bukkit.material.Redstone;
import org.bukkit.scheduler.BukkitRunnable;

public class RunTask extends BukkitRunnable {
    final int COUNT=2;
    Player My,target;

    public RunTask(Player my, Player target) {
        My = my;
        this.target = target;
    }

    @Override
    public void run() {
        Location a = My.getLocation();
        Location b = target.getLocation();
        World.Environment WE =target.getWorld().getEnvironment();
        Color ColoR=Color.YELLOW;
        double distance = Math.sqrt((a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY()) + (a.getZ() - b.getZ()) * (a.getZ() - b.getZ()));
        double disX=a.getX()+17*(b.getX()-a.getX())/distance,
                disY=a.getY()+17*(b.getY()-a.getY())/distance,
                disZ=a.getZ()+17*(b.getZ()-a.getZ())/distance;
        switch (WE)
        {
            case NORMAL:
                ColoR= Color.GREEN;
                break;
            case NETHER:
                ColoR= Color.RED;
                break;
            case THE_END:
                ColoR= Color.PURPLE;
                break;
        }
        Location spawnLoc = new Location(My.getWorld(),disX,disY,disZ);
        if(distance>15)My.getWorld().spawnParticle(Particle.REDSTONE, spawnLoc, COUNT,0.2,0.2,0.2,new Particle.DustOptions(ColoR,5));
        //My.sendMessage(disX+" "+disY+" "+disZ);
    }
}
