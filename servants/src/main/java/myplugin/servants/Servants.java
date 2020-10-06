package myplugin.servants;

import myplugin.servants.command.Command1;
import org.bukkit.plugin.java.JavaPlugin;

public final class Servants extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("[Servant]插件已经启动，你TM是我的master吗？");
        this.getCommand("creatServant").setExecutor(new Command1(this));
    }

    @Override
    public void onDisable() {
        getLogger().info("[Servant]插件已经关闭");
    }
}
