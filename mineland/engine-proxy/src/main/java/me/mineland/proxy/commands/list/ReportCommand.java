package me.mineland.proxy.commands.list;

import com.google.common.collect.Lists;
import me.mineland.proxy.controllers.ReportController;
import me.mineland.proxy.commands.ProxyCommands;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ReportCommand extends ProxyCommands {

    public static final Map<UUID, Long> DELAY = new HashMap<>();
    private static final SimpleDateFormat DF = new SimpleDateFormat("mm:ss");

    public ReportCommand() {
        super("report", "rp", "denunciar", "denuncia", "reportar");
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(TextComponent.fromLegacyText("§cEste comando é somente para jogadores."));
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;
        long current = DELAY.getOrDefault(player.getUniqueId(), 0L);
        if (current > System.currentTimeMillis()) {
            player.sendMessage(
                    TextComponent.fromLegacyText("§cVocê precisa aguardar " + (current- System.currentTimeMillis()) / 1000 + " segundos para enviar uma nova denúncia."));
            return;
        }

        DELAY.remove(player.getUniqueId());
        if (args.length == 0) {
            player.sendMessage(TextComponent.fromLegacyText(
                    " \n   §eAjuda do comando §f'" + "report" + "'\n" +
                            "\n  §e- §f/report (jogador) §7Reporte um jogador, sem a existência de provas visuais." +
                            "\n  §e- §f/report (jogador) (prova) §7Reporte um jogador, com a existência de provas visuais, ou seja, imagens e vídeos.\n "
            ));
            return;
        }
        String regex = "(https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9]+\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]+\\.[^\\s]{2,})";
        boolean needsProof = args.length == 2;
        ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
        if (target ==null) {
            player.sendMessage(TextComponent.fromLegacyText("§f" + args[0] +" §cnão está online da rede."));
            return;
        }
        TextComponent textC = new TextComponent("\n       §e* §7Você irá reportar o jogador §f" + target.getName() + "" +
                "\n §8(Escolha um motivo da categoria " + (needsProof ?"'Exige prova'": "'Não exige prova'")+")\n\n");
        if (needsProof) {
            if (!args[1].matches(regex)) {
                player.sendMessage(TextComponent.fromLegacyText("§cA prova deve ser um link válido."));
                return;
            }
            Lists.newArrayList( ReportController.values()).stream().filter(ReportController::isRequiresProof).forEach(result -> {
                TextComponent hover = new TextComponent(" §e- §7" + result.getName() + "\n");
                hover.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§eClique para reportar §f" + target.getName() + " §epor §f§n" + result.getName() )));
                hover.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/sendr " + target.getName() + " " + result.toString() + " " + args[1]));
                textC.addExtra(hover);
            });
        }else {
            Lists.newArrayList( ReportController.values()).stream().filter(result -> !result.isRequiresProof()).forEach(result -> {
                TextComponent hover = new TextComponent(" §e- §7" + result.getName() + " \n");
                hover.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§eClique para reportar §f" + target.getName() + " §epor §f§n" + result.getName() )));
                hover.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/sendr " + target.getName() + " " + result.toString() ));
                textC.addExtra(hover);
            });
        }
        player.sendMessage(textC);


    }
}