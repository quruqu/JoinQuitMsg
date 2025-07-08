package me.ujun;

import me.ujun.commands.ReloadCMD;
import me.ujun.config.ConfigHandler;
import me.ujun.listeners.PlayerJoinListener;
import me.ujun.listeners.PlayerQuitListener;
import me.ujun.saving.DataFile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

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
