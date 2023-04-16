package me.mineland.proxy.listeners.list;

import me.mineland.proxy.ProxyMain;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Listener;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MessagesListener implements Listener {

    private static List<String> messages = Arrays.asList(
            " §a§lWAR LAND §f➟ Nossos servidores se encontram em fase §1Beta §fpodendo haver bug's",
            " §a§lWAR LAND §f➟ Encontrou um jogador utilizando algo ilicito? Reporte-o utilizando §d/report (jogador)",
            " §a§lWAR LAND §f➟ Adquira §aVIP §fou §6CASH §facessando nossa loja: §bhttps://warland.net/shop"
    );

    private static int index = 0;

    public static void setup() {

        ProxyMain.getInstance().getProxy().getScheduler().schedule(ProxyMain.getInstance(), () -> {
            if (index >= messages.size())
                index = 0;
            ProxyMain.getInstance().getProxy().broadcast(TextComponent
                    .fromLegacyText(messages.get(index)));
            ++index;
        }, 2, 2, TimeUnit.MINUTES);

    }

}
