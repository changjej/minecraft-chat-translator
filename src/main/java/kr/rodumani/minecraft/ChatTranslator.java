package kr.rodumani.minecraft;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatTranslator extends JavaPlugin {

    @Override
    public void onDisable() {
        getLogger().info("onDisable has been invoked!");
        HandlerList.unregisterAll();
    }

    @Override
    public void onEnable() {
        getLogger().info("onEnable has been invoked!");
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
    }
}
