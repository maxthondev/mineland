package me.mineland.proxy.commands.list;

import me.mineland.core.Fields;
import me.mineland.proxy.commands.ProxyCommands;
import me.mineland.proxy.controllers.FakeController;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FakeCommand extends ProxyCommands {

    public FakeCommand() {
        super("fake", "nick", "fakename", "nomefalso");
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(TextComponent.fromLegacyText("§cEste comando é somente para jogadores."));
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (!player.hasPermission(Fields.PREFIX_PERMISSION + ".cmd.fake")) {
            player.sendMessage(TextComponent.fromLegacyText(Fields.NO_PERMISSION));
            return;
        }

        if (player.getServer().getInfo().getName().contains("mega")) {
            player.sendMessage(TextComponent.fromLegacyText(Fields.NO_PERMISSION));
            return;
        }
        String fakeName = args.length > 0 ? args[0] : null;
        if (args.length == 0) {
            List<String> list = FakeController.getRandomNicks().stream().filter(FakeController::isUsable).collect(Collectors.toList());
            Collections.shuffle(list);
            fakeName = list.stream().findFirst().orElse(null);
            if (fakeName == null) {
                player.sendMessage(TextComponent.fromLegacyText(" \n §cNenhum nickname aleatório está disponível no momento.\n §cVocê pode utilizar um nome diferente através do comando /fake [nome]\n "));
                return;
            }
        }

        if (!FakeController.isUsable(fakeName)) {
            player.sendMessage(TextComponent.fromLegacyText("§cEste nickname falso não está disponível para uso."));
            return;
        }

        if (fakeName.length() > 16 || fakeName.length() < 4) {
            player.sendMessage(TextComponent.fromLegacyText("§cO nickname falso precisa conter de 4 a 16 caracteres."));
            return;
        }

        FakeController.applyFake(player, fakeName);
        player.sendMessage(TextComponent.fromLegacyText("§eVocê está disfarçado como: §f" + fakeName));

    }


}
