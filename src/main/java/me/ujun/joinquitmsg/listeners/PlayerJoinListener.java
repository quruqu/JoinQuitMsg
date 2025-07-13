package me.ujun.joinquitmsg.listeners;

import me.ujun.joinquitmsg.config.ConfigHandler;
import me.ujun.joinquitmsg.saving.DataFile;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String currentName = player.getName();
        String storedName = DataFile.storedPlayerNames.get(player.getUniqueId());
        String message;

        if (player.hasPlayedBefore()) {
            message = ConfigHandler.joinMessage.
                    replace("%player%", currentName);
            if (DataFile.storedPlayerNames.containsKey(player.getUniqueId())) {
                if (!currentName.equals(storedName)) {
                    message = ConfigHandler.nameChangeJoinMessage
                            .replace("%player%", currentName)
                            .replace("%old%", storedName);
                }
            }

        } else {
            message = ConfigHandler.firstJoinMessage.replace("%player%", currentName);
        }

        event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', message));

    }

}
