package me.mineland.proxy.commands.list;

import me.mineland.core.Fields;
import me.mineland.proxy.commands.ProxyCommands;
import me.mineland.proxy.controllers.FakeController;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.List;

public class FakeListCommand extends ProxyCommands {

    public FakeListCommand() {
        super("fakelist", "fl", "listfakes", "fakel");
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

        List<String> nicked = FakeController.listNicked();
        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < nicked.size(); index++) {
            sb.append("§c").append(FakeController.getFake(nicked.get(index))).append(" §fé na verdade ").append("§a").append(nicked.get(index)).append(index + 1 == nicked.size() ? "" : "\n");
        }

        nicked.clear();
        if (sb.length() == 0) {
            sb.append("§cNão há nenhum usuário utilizando um nickname falso.");
        }

        player.sendMessage(TextComponent.fromLegacyText(" \n§eLista de nicknames falsos:\n \n" + sb.toString() + "\n "));

    }

}
