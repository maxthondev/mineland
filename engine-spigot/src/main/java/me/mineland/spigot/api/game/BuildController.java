package me.mineland.spigot.api.game;

public class BuildController {


    public static boolean buildMode = false;

    public static boolean isBuildMode() {
        return buildMode;
    }

    public static void setBuildMode(boolean buildMode) {
        BuildController.buildMode = buildMode;
    }

}
