package me.mineland.proxy.listeners.list;

import me.mineland.core.Fields;
import net.md_5.bungee.UserConnection;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class BlockCmdListener implements Listener {

    @EventHandler
    public void onPlayerChatBlockedCommand(ChatEvent event) {
        if (event.getSender() instanceof ProxiedPlayer) {
            if (event.isCommand()) {
                ProxiedPlayer player = (UserConnection) event.getSender();
                String[] args = event.getMessage().replace("/", "").split(" ");
                String command = args[0];
                if ((command.equalsIgnoreCase("lp")
                        || command.equalsIgnoreCase("luckperms")
                        || command.equalsIgnoreCase("perms")
                        || command.equalsIgnoreCase("permissions")
                        || command.equalsIgnoreCase("perm")
                        || command.equalsIgnoreCase("luckypermsbungee")
                        || command.equalsIgnoreCase("lpb")
                        || command.equalsIgnoreCase("antibot")
                        || command.equalsIgnoreCase("ab")
                        || command.equalsIgnoreCase("sr")
                        || command.equalsIgnoreCase("help")
                ) && !player.hasPermission("*")) {
                    event.setCancelled(true);
                    player.sendMessage(TextComponent.fromLegacyText("§fComando desconhecido."));
                }
            }
        }
    }

}
