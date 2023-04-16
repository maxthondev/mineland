package me.mineland.core.utils;

import org.bukkit.Location;

public class LocationUtil {

    public static boolean isRealMovement(Location from, Location to) {
        return !(from.getX() == to.getX() && from.getY() == to.getY() && from.getZ() == to.getZ());
    }

}
