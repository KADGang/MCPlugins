package catchgame.catchgame.basicClass;

import org.bukkit.entity.Player;

public class catcher extends gamePlayer{

    public catcher(Player player) {
        super(player);
    }

    public catcher(gamePlayer gamePlayer) {
        super(gamePlayer.getPlayerName());
    }
}
