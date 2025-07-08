package me.ujun.listeners;

import me.ujun.config.ConfigHandler;
import me.ujun.saving.DataFile;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;


public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        String message = ConfigHandler.quitMessage
                    .replace("%player%", playerName);


        DataFile.storedPlayerNames.put(player.getUniqueId(), playerName);
        event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
