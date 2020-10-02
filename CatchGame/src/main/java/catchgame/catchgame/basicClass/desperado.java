package catchgame.catchgame.basicClass;

import org.bukkit.entity.Player;

public class desperado extends gamePlayer{

    public desperado(Player player) {
        super(player);
    }

    public desperado(gamePlayer gamePlayer) {
        super(gamePlayer.player);
    }
}
