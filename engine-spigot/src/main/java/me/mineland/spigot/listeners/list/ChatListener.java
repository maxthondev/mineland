package me.mineland.spigot.listeners.list;

import me.mineland.core.Fields;
import me.mineland.core.player.roles.RoleController;
import me.mineland.core.player.roles.bukkit.RoleManager;
import me.mineland.spigot.SpigotMain;
import me.mineland.spigot.controllers.FakeController;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class ChatListener implements Listener {

    private final String CHAT_COOLDOWN_TAG = "chat-cooldown";
    private final String DELAY_COMMAND_TAG = "command-cooldown";
    private final Long CHAT_COOLDOWN_TIME = 2000L;
    private final Long COMMAND_COOLDOWN_TIME = 1000L;

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        RoleController roleController = RoleController.getRolePlayer(player);

        if (player.hasPermission(RoleController.BETA.getPermission())) {
            event.setCancelled(inChatCooldown(player));
        }

        if (event.isCancelled()) {
            return;
        }

        event.setCancelled(true);

        String formatMessage = "";

        if (player.hasPermission(Fields.PREFIX_PERMISSION + ".chatcolor")) {
            formatMessage = "{playerPrefix}{playerName}§f: §f{playerMessage}";
        } else {
            formatMessage = "{playerPrefix}{playerName}§f: §7{playerMessage}";
        }

        String message = event.getMessage();

        if (player.hasPermission(Fields.PREFIX_PERMISSION + ".chatcolor")) {
            if (message.contains("&")) {
                message.replaceAll("&", "§");
            }
        }

        for (Player onlinePlayers : event.getRecipients()) {
            onlinePlayers.sendMessage(
                    formatMessage
                    .replace("{playerPrefix}", roleController.getPrefix())
                    .replace("{playerName}", player.getName())
                    .replace("{playerMessage}", message.replace("&", "§"))
            );
        }

    }

    private boolean inChatCooldown(Player player) {
        boolean cooldown = false;

        if (player.hasMetadata(CHAT_COOLDOWN_TAG)) {
            if (player.getMetadata(CHAT_COOLDOWN_TAG).get(0).asLong() + CHAT_COOLDOWN_TIME > System
                    .currentTimeMillis()) {
                player.sendMessage(Fields.COOLDOWN_MESSAGE);
                cooldown = true;
            } else {
                player.setMetadata(CHAT_COOLDOWN_TAG,
                        new FixedMetadataValue(SpigotMain.getInstance(), System.currentTimeMillis()));
            }
        } else {
            player.setMetadata(CHAT_COOLDOWN_TAG,
                    new FixedMetadataValue(SpigotMain.getInstance(), System.currentTimeMillis()));
        }

        return cooldown;
    }

}
