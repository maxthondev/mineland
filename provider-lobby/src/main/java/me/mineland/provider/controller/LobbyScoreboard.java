package me.mineland.provider.controller;

import me.mineland.core.configuration.MotdConfiguration;
import me.mineland.core.player.boards.ScoreboardController;
import me.mineland.core.player.roles.RoleController;
import me.mineland.core.utils.StringUtils;
import me.mineland.core.utils.UtilsProfile;
import me.mineland.provider.SystemMain;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LobbyScoreboard implements Listener {

    @EventHandler
    public void onScoreboardLobby(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        setBoard(player);
    }

    public static void setBoard(Player player) {
        ScoreboardController SB = new ScoreboardController("§6§lWAR §8➟ §f" + Bukkit.getOnlinePlayers().size());
        RoleController roleController = RoleController.getRolePlayer(player);

        SB.addLine("§7    " + StringUtils.formatDateTwoBR(player.getLastPlayed()), 9);
        SB.addLine("§4", 8);
        SB.addLine("§fRank: §7" + roleController.getColoredTag(), 7);
        SB.addLine("§fHub: §a#" + SystemMain.getLobbyId(), 6);
        SB.addLine("§0", 5);
        SB.addLine("§fCoins: §a" + UtilsProfile.formatValue(0), 4);
        SB.addLine("§fCash: §6" + UtilsProfile.formatValue(0), 3);
        SB.addLine("§l", 2);
        SB.addLine("§ewarland.com.br", 1);

        SB.setScoreboard();

        player.setScoreboard(SB.getScoreboard());
    }

}
