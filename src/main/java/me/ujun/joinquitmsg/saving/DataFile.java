package me.ujun.joinquitmsg.saving;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class DataFile {
    public static HashMap<UUID, String> storedPlayerNames = new HashMap<>();
    private final File file;
    private final FileConfiguration config;

    public DataFile(File dataFolder) {
        if (!dataFolder.exists()) {
            dataFolder.mkdirs(); // 폴더 먼저 생성
        }

        this.file = new File(dataFolder, "data.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.config = YamlConfiguration.loadConfiguration(file);
    }


    public void loadData() {
        if (config.contains("players")) {
            ConfigurationSection section = config.getConfigurationSection("players");
            for (String key : section.getKeys(false)) {
                UUID uuid = UUID.fromString(key);
                String name = section.getString(key);
                storedPlayerNames.put(uuid, name);
            }
        }
    }

    public void saveData() {
        FileConfiguration config = new YamlConfiguration();
        for (Map.Entry<UUID, String> entry : storedPlayerNames.entrySet()) {
            config.set("players." + entry.getKey().toString(), entry.getValue());
        }

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
