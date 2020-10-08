package catchgame.catchgame.basicClass;

import org.bukkit.entity.Player;

public class gamePlayer {
    protected String playerID;

    public gamePlayer(Player sender) {
        this.playerID = sender.getName();
    }

    public gamePlayer(String PlayerID) {
        this.playerID = PlayerID;
    }

    public String getPlayerName() {
        return playerID;
    }

}
