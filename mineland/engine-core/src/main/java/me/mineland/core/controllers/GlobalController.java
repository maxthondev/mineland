package me.mineland.core.controllers;

import me.mineland.core.controllers.interfaces.GlobalInterface;
import org.bukkit.entity.Player;

public class GlobalController {

    private static GlobalInterface globalInterface;

    public static void refreshPlayer(Player player) {
        globalInterface.refreshPlayer(player);
    }

}
