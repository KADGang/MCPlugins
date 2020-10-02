package catchgame.catchgame;

import catchgame.catchgame.basicClass.catcher;
import catchgame.catchgame.basicClass.desperado;
import catchgame.catchgame.basicClass.gamePlayer;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public void initGame() {
        if (!roomIsFull()) {
            plugin.getServer().broadcastMessage("房间" + roomName + "人数未满, 无法开始游戏.");
            return;
        }

        Random random = new Random(1);
        String broadcastMsg = "本次游戏中";
        int desperadoIndex = random.nextInt(5);

        for (int i = 0; i < 5; i++) {//TODO:记得修改回5
            if (i == desperadoIndex) {
                playerList.set(i, new desperado(playerList.get(i)));
                broadcastMsg += ("玩家 " + playerList.get(i).getPlayerName() + " 被设置为逃亡者。");
                BukkitTask task = new calculateDis(plugin).runTaskTimer(plugin, 0, 20L);
            } else {
                playerList.set(i, new catcher(playerList.get(i)));
            }
        }
        plugin.getServer().broadcastMessage(broadcastMsg);
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
            if (playerList.size() == 5) {//TODO:记得修改回5
                plugin.getServer().broadcastMessage("房间" + roomName + "已满, 输入 /startGame [房间名] 即可开始游戏.");
            }
        } else {
            plugin.getServer().broadcastMessage("游戏房间号为：" + roomName + "的房间人数已满，请不要再加入该房间。");
            plugin.getLogger().info("房间中玩家有：" + playerList.get(0).getPlayerName() + ", " + playerList.get(1).getPlayerName() + ", " + playerList.get(2).getPlayerName() + ", " + playerList.get(3).getPlayerName() + ", " + playerList.get(4).getPlayerName());
        }
    }

    public boolean roomIsFull() {
        return playerList.size() >= 5; //TODO:记得修改回5
    }
}
