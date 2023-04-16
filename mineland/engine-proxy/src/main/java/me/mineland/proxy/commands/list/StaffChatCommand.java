package me.mineland.proxy.commands.list;

import me.mineland.core.Fields;
import me.mineland.core.player.roles.RoleController;
import me.mineland.core.player.roles.bukkit.RoleManager;
import me.mineland.core.utils.StringUtils;
import me.mineland.proxy.commands.ProxyCommands;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;

public class StaffChatCommand extends ProxyCommands {

    public static ArrayList<ProxiedPlayer> lixos = new ArrayList<>();
    public StaffChatCommand() {
        super("sc", "staffchat");
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(TextComponent.fromLegacyText("§cEste comando é somente para jogadores."));
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (!player.hasPermission(Fields.PREFIX_PERMISSION + ".staff")) {
            player.sendMessage(TextComponent.fromLegacyText(Fields.NO_PERMISSION));
            return;
        }
        if (player.hasPermission(Fields.PREFIX_PERMISSION + ".staff")) {
            if (args.length == 0) {
                player.sendMessage(TextComponent.fromLegacyText("§cUtilize de forma correta: /sc (mensagem)"));
                return;
            }

            String message = StringUtils.join(args, " ");
            for (ProxiedPlayer players : ProxyServer.getInstance().getPlayers()) {
                if (players.hasPermission(Fields.PREFIX_PERMISSION + ".staff")) {
                    players.sendMessage("§e§l[STAFF] " + RoleController.getProxyRolePlayer(player).getPrefix() + player.getName() + "§f: " + message);
                }
            }
        }else {
            sender.sendMessage(TextComponent.fromLegacyText("§c[Stone] §cSem §c§npermissão §cpara executar esse comando."));

        }
    }
}
