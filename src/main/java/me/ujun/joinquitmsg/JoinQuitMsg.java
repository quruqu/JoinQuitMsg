package me.ujun.joinquitmsg;

import me.ujun.joinquitmsg.commands.ReloadCMD;
import me.ujun.joinquitmsg.config.ConfigHandler;
import me.ujun.joinquitmsg.listeners.PlayerJoinListener;
import me.ujun.joinquitmsg.listeners.PlayerQuitListener;
import me.ujun.joinquitmsg.saving.DataFile;
import org.bukkit.plugin.java.JavaPlugin;

public final class JoinQuitMsg extends JavaPlugin {
    private DataFile dataFile;

    @Override
    public void onEnable() {
        dataFile = new DataFile(getDataFolder());
        dataFile.loadData();

        saveDefaultConfig();
        ConfigHandler.init(this);

        registerListeners();
        registerCommands();
    }

    @Override
    public void onDisable() {
        dataFile.saveData();
    }

    private void registerCommands() {
        this.getCommand("joinquitmsg-reload").setExecutor(new ReloadCMD());
    }

    private void registerListeners() {
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
    }

}
