package catchgame.catchgame;

import catchgame.catchgame.basicClass.gamePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class gameRoom {
    String roomName;
    List<gamePlayer> playerList;
    List<String> playerNameList;
    JavaPlugin plugin;

    public gameRoom(String roomName, JavaPlugin plugin) {
        playerList = new ArrayList<>();
        playerNameList = new ArrayList<>();
        this.plugin = plugin;
        this.roomName = roomName;

        plugin.getServer().broadcastMessage("游戏房间" + roomName + "已经创建");
    }

    public void addPlayer(gamePlayer player) {
        if (playerNameList.contains(player.getPlayerName())) {
            plugin.getServer().broadcastMessage("玩家" + player.getPlayerName() + "已经在房间" + roomName + "中，请勿重复加入。");
            return;
        }

        if (!roomIsFull()) {
            playerList.add(player);
            playerNameList.add(player.getPlayerName());
            plugin.getServer().broadcastMessage("玩家" + player.getPlayerName() + "加入房间" + roomName + ",人数为(" + playerList.size() + "/5).");
            if (playerList.size() == 5) {
                plugin.getServer().broadcastMessage("房间" + roomName + "已满, 输入 /startGame [房间名] 即可开始游戏.");
            }
        } else {
            plugin.getServer().broadcastMessage("游戏房间号为：" + roomName + "的房间人数已满，请不要再加入该房间。");
            plugin.getLogger().info("房间中玩家有：" + playerList.get(0).getPlayerName() + ", " + playerList.get(1).getPlayerName() + ", " + playerList.get(2).getPlayerName() + ", " + playerList.get(3).getPlayerName() + ", " + playerList.get(4).getPlayerName());
        }
    }

    public boolean roomIsFull() {
        return playerList.size() >= 5;
    }
}
