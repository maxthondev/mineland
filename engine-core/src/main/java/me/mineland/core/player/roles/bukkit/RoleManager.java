package me.mineland.core.player.roles.bukkit;

import me.mineland.core.player.roles.RoleController;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class RoleManager {

    public static void setRolePlayer(Player player) {
        RoleController role = RoleController.getRolePlayer(player);
        Scoreboard scoreboard = player.getScoreboard();
        player.setPlayerListName(role.getPrefix() + player.getName());
        player.setDisplayName(role.getPrefix() + player.getName());

        Team team = scoreboard.getTeam(role.ordinal() + role.getName());
        if (team == null) {
            team = scoreboard.registerNewTeam(role.ordinal() + role.getName());
            team.setPrefix(role.getPrefix());
            team.addEntry(player.getName());
        }

    }

    public static void removeRolePlayer(Player player) {
        RoleController role = RoleController.getRolePlayer(player);
        Scoreboard scoreboard = player.getScoreboard();

        Team team = scoreboard.getTeam(role.ordinal() + role.getName());
        if (team != null && team.hasEntry(player.getName())) {
            team.removeEntry(player.getName());
        }
    }

}
