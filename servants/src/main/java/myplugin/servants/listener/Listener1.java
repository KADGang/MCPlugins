package myplugin.servants.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Listener1 implements Listener {

    @EventHandler
    public void caseEvent(PlayerJoinEvent evt){
        Player p = evt.getPlayer();
        Bukkit.broadcastMessage("欢迎玩家" + p.getName()+"加入服务器");
    }
}