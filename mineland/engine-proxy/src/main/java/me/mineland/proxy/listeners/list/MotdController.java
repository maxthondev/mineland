package me.mineland.proxy.listeners.list;

import me.mineland.core.configuration.MotdConfiguration;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.HashMap;

public class MotdController implements Listener {

    public static HashMap<String, Integer> ping = new HashMap();

    @EventHandler(
            priority = -64
    )
    public void onProxyPing(ProxyPingEvent event) {
        String ipAddress = event.getConnection().getAddress().getHostString();
        if (ping.containsKey(ipAddress)) {
            ping.put(ipAddress, (Integer) ping.get(ipAddress) + 1);
        } else {
            ping.put(ipAddress, 1);
        }

        if ((Integer) ping.get(ipAddress) > 20) {
            event.setResponse((ServerPing) null);
        }
        event.setResponse(new ServerPing(event.getResponse().getVersion(), new ServerPing.Players(100, BungeeCord.getInstance().getOnlineCount(), new ServerPing.PlayerInfo[]{new ServerPing.PlayerInfo("", "")}), MotdConfiguration.makeCenteredMotd("§a§lMINE LAND §f➟ www.mineland.com.br") + "\n" + MotdConfiguration.makeCenteredMotd("§6§lBED WARS §f➟ Adicionado novos cosméticos!"), event.getResponse().getFaviconObject()));
    }

}
