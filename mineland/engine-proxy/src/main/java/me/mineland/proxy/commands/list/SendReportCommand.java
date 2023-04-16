package me.mineland.proxy.commands.list;

import me.mineland.core.Fields;
import me.mineland.proxy.controllers.ReportController;
import me.mineland.proxy.commands.ProxyCommands;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.chat.ComponentSerializer;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class SendReportCommand extends ProxyCommands {

    public static final Map<UUID, Long> DELAY = new HashMap<>();
    private static final SimpleDateFormat DF = new SimpleDateFormat("mm:ss");

    public SendReportCommand() {
        super("sendr");
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
            player.sendMessage(TextComponent.fromLegacyText("§cVocê precisa aguardar " + (current - System.currentTimeMillis()) / 1000 + " segundos para enviar uma nova denúncia."));
            return;
        }

        DELAY.remove(player.getUniqueId());
        if (args.length == 0) {
            player.sendMessage(TextComponent.fromLegacyText("§cUtilize de forma corretamente: /report (jogador)"));
            return;
        }

        ProxiedPlayer pp = ProxyServer.getInstance().getPlayer(args[0]);
        if (pp == null) {
            player.sendMessage(TextComponent.fromLegacyText("§cUsuário não encontrado."));
            return;
        }

        //ReportType rt = ReportType.fromName(StringUtils.join(args, 1, " "));
        ReportController rt = ReportController.fromValue(args[1]);
        if (rt == null) {
            player.sendMessage(ComponentSerializer.parse(ReportController.REASONS.toString().replace("{player}", args[0])));
            return;
        }

        String proof = null;
        if (rt.isRequiresProof()) {
            proof = args[2];
        }
        boolean needsProof = proof != null;
        DELAY.put(player.getUniqueId(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(150));
        player.sendMessage(TextComponent.fromLegacyText("§aSua denúncia foi enviada para nossa Equipe."));

        String mini = null;
        TextComponent report = new TextComponent(
                "\n     §e[!] §7Nova denúncia §f" + pp.getName() + "" + "\n §8(" + (needsProof ? "Denúncia com prova" : "Denúncia sem provas visuais") + ")\n\n§7  Quem enviou §f" + player
                        .getName() + "\n§7  Motivo §f" + rt.getName() + "\n  §7Servidor §f" + (pp.getServer().getInfo().getName().contains("mega") ?
                        pp.getServer().getInfo().getName() + "-" + mini :
                        pp.getServer().getInfo().getName()));
        if (needsProof) {
            report.addExtra("\n§7  Prova §f'" + args[2] + "'");
        }
        TextComponent hover = new TextComponent("\n\n   §a§nVá até la!\n ");
        hover.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§eClique para ir até §f" + pp.getName())));

        String command = null;
        if (pp.getServer().getInfo().getName().contains("mega") && mini == null) {
            command = "/server " + pp.getServer().getInfo().getName();

        } else {
            command = "/mtp " + pp.getServer().getInfo().getName() + " " + mini;
        }
        hover.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));

        report.addExtra(hover);
        for (ProxiedPlayer players : ProxyServer.getInstance().getPlayers()) {
            if (players.hasPermission(Fields.PREFIX_PERMISSION + ".staff")) {
                players.sendMessage(report);
            }
        }
    }
}
