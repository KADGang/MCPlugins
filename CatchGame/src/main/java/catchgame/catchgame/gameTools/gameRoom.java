package catchgame.catchgame.gameTools;

import catchgame.catchgame.basicClass.catcher;
import catchgame.catchgame.basicClass.desperado;
import catchgame.catchgame.basicClass.gamePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class gameRoom {
    //TODO:加入房间退出功能。

    String roomName;
    List<gamePlayer> playerList;
    List<String> playerNameList;
    JavaPlugin plugin;

    public gameRoom(String roomName, JavaPlugin plugin) {
        playerList = new ArrayList<>();
        playerNameList = new ArrayList<>();
        this.plugin = plugin;
        this.roomName = roomName;

        plugin.getServer().broadcastMessage("§e游戏房间" + roomName + "已经创建");
    }

    public void initGame() {
        Random random = new Random(1);
        String broadcastMsg = "本次游戏中";
        int desperadoIndex = random.nextInt(playerList.size());
        String desperadoID = "";

        for (int i = 0; i < playerList.size(); i++) {
            if (i == desperadoIndex) {
                playerList.set(i, new desperado(playerList.get(i)));
                desperadoID = playerList.get(i).getPlayerName();
                broadcastMsg += ("玩家 " + playerList.get(i).getPlayerName() + " 被设置为逃亡者。");
            } else {
                playerList.set(i, new catcher(playerList.get(i)));
            }
        }

        for (String playerID : playerNameList) {
            Player player = plugin.getServer().getPlayer(playerID);
            assert player != null;

            player.sendMessage(broadcastMsg);
            player.sendMessage("游戏开始！将在30秒后开始追捕逃亡者！");
        }

        BukkitTask task = new perspectiveTool(plugin, playerNameList).runTaskTimer(plugin, 30 * 20L, 10L);
        BukkitTask guideTask = new guideBall(desperadoID, playerNameList, plugin).runTaskTimer(plugin, 30 * 20L, 10L);
    }

    public void addPlayer(gamePlayer player) {
        Player worldPlayer = plugin.getServer().getPlayer(player.getPlayerName());
        assert worldPlayer != null;

        if (playerNameList.contains(player.getPlayerName())) {
            worldPlayer.sendMessage("玩家" + player.getPlayerName() + "已经在房间" + roomName + "中，请勿重复加入。");
            player = null;
            return;
        }

        if (!roomIsFull()) {
            playerList.add(player);
            playerNameList.add(player.getPlayerName());

            for (String playerID: playerNameList) {
                Player temp = plugin.getServer().getPlayer(playerID);
                worldPlayer.sendMessage("玩家" + player.getPlayerName() + "加入房间" + roomName + ",人数为(" + playerList.size() + "/5).");
            }
            if (playerList.size() == 5) {
                worldPlayer.sendMessage("房间" + roomName + "已满, 输入 /startGame [房间名] 即可开始游戏.");
            }
        } else {
            worldPlayer.sendMessage("游戏房间号为：" + roomName + "的房间人数已满，请不要再加入该房间。");
        }
    }

    public boolean roomIsFull() {
        return playerList.size() >= 5;
    }
}
