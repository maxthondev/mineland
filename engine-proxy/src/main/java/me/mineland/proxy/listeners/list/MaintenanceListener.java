package me.mineland.proxy.listeners.list;

import me.mineland.core.Fields;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class MaintenanceListener implements Listener {

    @EventHandler
    public void onTentablePlayerJoin(PostLoginEvent event) {
        ProxiedPlayer proxiedPlayer = event.getPlayer();

        if (!proxiedPlayer.hasPermission(Fields.PREFIX_PERMISSION + ".staff")) {
            event.getPlayer().disconnect(TextComponent.fromLegacyText(Fields.MAINTENANCE_MESSAGE));
        }
    }

}
