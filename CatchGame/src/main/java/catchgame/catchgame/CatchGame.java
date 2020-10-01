package catchgame.catchgame;

import catchgame.catchgame.basicClass.gamePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class CatchGame extends JavaPlugin {

    Map<String, gameRoom> roomStringMap = new HashMap<>();
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("CatchGame 抓人游戏插件 已经加载完成。");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("CatchGame 抓人游戏插件 已经卸载完成。");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            if (command.getName().equalsIgnoreCase("setRoom") && sender instanceof Player) {
                String roomName = args[0];
                gameRoom newRoom = new gameRoom(roomName, this);
                newRoom.addPlayer(new gamePlayer((Player) sender));

                roomStringMap.put(roomName, newRoom);
                return true;
            } else if (command.getName().equalsIgnoreCase("joinRoom") && sender instanceof Player) {
                String roomName = args[0];
                if (roomStringMap.containsKey(roomName)) {
                    gameRoom room = (gameRoom) roomStringMap.get(roomName);
                    room.addPlayer(new gamePlayer((Player) sender));
                } else {
                    getServer().broadcastMessage("没有找到名为" + roomName + "的房间。");
                }
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw e;
        }

        return false;
    }
}
