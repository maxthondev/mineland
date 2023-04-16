package me.mineland.proxy.commands.list;

import me.mineland.core.Fields;
import me.mineland.core.utils.StringUtils;
import me.mineland.proxy.commands.ProxyCommands;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class BroadcastCommand extends ProxyCommands {

    public BroadcastCommand() {
        super("aviso", "bc");
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(TextComponent.fromLegacyText("§cEste comando é somente para jogadores."));
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (!player.hasPermission(Fields.PREFIX_PERMISSION + ".cmd.warning")) {
            player.sendMessage(TextComponent.fromLegacyText(Fields.NO_PERMISSION));
            return;
        }
        if (args.length == 0) {
            player.sendMessage(TextComponent.fromLegacyText("§cUtilize de forma corretamente: /aviso (mensagem)"));
            return;
        }
        String msg = StringUtils.join(args, " ");
        BungeeCord.getInstance().getPlayers().forEach(ppr -> {
            ppr.sendMessage(TextComponent.fromLegacyText(""));
            ppr.sendMessage(TextComponent.fromLegacyText(" §a§lMINE LAND §f➟ " + StringUtils.formatColors(msg)));
            ppr.sendMessage(TextComponent.fromLegacyText(""));

        });
    }
}

