package me.mineland.proxy.controllers;

import com.google.common.collect.Lists;
import me.mineland.core.Fields;
import me.mineland.proxy.ProxyMain;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.*;

public class FakeController {
    private static Map<String, String> fakeNames = new HashMap<>();

    private static HashSet<String> blacklistedNames = new HashSet<>();

    public static void applyFake(ProxiedPlayer player, String fakeName) {
        fakeNames.put(player.getName(), fakeName);
        player.disconnect(TextComponent.fromLegacyText(Fields.APPLY_FAKE_MESSAGE.replace("{fakeName}", fakeName)));
    }

    public static void removeFake(ProxiedPlayer player) {
        fakeNames.remove(player.getName());
        player.disconnect(TextComponent.fromLegacyText(Fields.REMOVE_FAKE_MESSAGE));
    }

    public static String getCurrent(String playerName) {
        return isFake(playerName) ? getFake(playerName) : playerName;
    }

    public static String getFake(String playerName) {
        return fakeNames.get(playerName);
    }

    public static boolean isFake(String playerName) {
        return fakeNames.containsKey(playerName);
    }

    public static boolean isUsable(String name) {
        return !blacklistedNames.contains(name.toLowerCase()) && !fakeNames.containsKey(name) && !fakeNames.containsValue(name) && ProxyMain.getInstance().getProxy().getPlayer(name) == null;
    }


    public static HashSet<String> getBlacklistedNames() {
        return blacklistedNames;
    }

    public static void setBlacklistedNames(HashSet<String> blacklistedNames) {
        FakeController.blacklistedNames = blacklistedNames;
    }

    public static List<String> listNicked() {
        return new ArrayList<>(fakeNames.keySet());
    }

    private static List<String> randoms;

    public static void setRandoms(List<String> randoms) {
        FakeController.randoms = randoms;
    }

    public static List<String> getRandomNicks() {
        if (randoms == null) {
            randoms = Lists.newArrayList("Milenona", "PoseDoRodo", "JogadorDeValorant", "zFusca", "zRapido");
        }

        return randoms;
    }
}

