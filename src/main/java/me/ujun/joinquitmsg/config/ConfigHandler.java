package me.ujun.joinquitmsg.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigHandler {
    private final JavaPlugin plugin;
    private static ConfigHandler instance;

    public static String joinMessage;
    public static String quitMessage;
    public static String firstJoinMessage;
    public static String nameChangeJoinMessage;


    public ConfigHandler(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public static ConfigHandler getInstance() {
        return instance;
    }

    public static void init(JavaPlugin plugin) {
        instance = new ConfigHandler(plugin);
        instance.loadConfig();
    }


    public void loadConfig() {
        plugin.reloadConfig();
        FileConfiguration config = plugin.getConfig();

        joinMessage = config.getString("join-message", "&e%player% joined the game");
        quitMessage = config.getString("quit-message", "&e%player% left the game");
        firstJoinMessage = config.getString("first-join-message", "&e%player% joined the game");
        nameChangeJoinMessage = config.getString("name-change-join-message", "&e%player% joined the game (older name: %old%)");
    }

}
