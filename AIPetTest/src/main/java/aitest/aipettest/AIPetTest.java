package aitest.aipettest;

import org.bukkit.plugin.java.JavaPlugin;

public final class AIPetTest extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("AIPet v1.0已加载");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("AIPet v1.0已卸载");
    }
}
