package catchgame.catchgame.basicClass;

import org.bukkit.entity.Player;

public class gamePlayer {
    protected Player player;

    public gamePlayer(Player sender) {
        this.player = sender;
    }

    public String getPlayerName() {
        return player.getName();
    }
}
