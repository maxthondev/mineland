package me.mineland.provider.controller;

import me.mineland.core.configuration.PluginConfiguration;
import me.mineland.provider.SystemMain;
import net.jitse.npclib.NPCLib;
import net.jitse.npclib.api.NPC;
import net.jitse.npclib.api.events.NPCInteractEvent;
import net.jitse.npclib.api.skin.MineSkinFetcher;
import net.jitse.npclib.api.skin.Skin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class LobbyNPC implements Listener {

    private static NPCLib library;
    private final SortedSet<String> ids = new TreeSet<>();

    public static void setup() {
        library = new NPCLib(SystemMain.getInstance());
    }

    private List<String> kitPvP = Arrays.asList(
            "§b§lKIT PVP",
            "§e0 jogando agora!");

    private List<String> bedWars = Arrays.asList(
            "§b§lBED WARS",
            "§e0 jogando agora!");

    @EventHandler
    public void onNpcEvent(PlayerJoinEvent event) {

        int skinId = 188100;
        MineSkinFetcher.fetchSkinFromIdAsync(skinId, skin -> {
            NPC pvp = library.createNPC(kitPvP);
            pvp.setLocation(new Location(Bukkit.getWorld("Lobby"), -242.0, 75.0, 197.0, -90, 0));
            pvp.setSkin(skin);
            ids.add(pvp.getId());
            pvp.create();
            Bukkit.getScheduler().runTask(SystemMain.getInstance(), () -> pvp.show(event.getPlayer()));
        });

        MineSkinFetcher.fetchSkinFromIdAsync(skinId, skin -> {
            NPC bed = library.createNPC(bedWars);
            bed.setLocation(new Location(Bukkit.getWorld("Lobby"), -243.0, 79.0, 200.0, -90, 0));
            bed.setSkin(skin);
            ids.add(bed.getId());
            bed.create();
            Bukkit.getScheduler().runTask(SystemMain.getInstance(), () -> bed.show(event.getPlayer()));
        });
    }

    @EventHandler
    public void onNPCInteract(NPCInteractEvent event) {
        String pvpId = ids.first();
        if (event.getNPC().getId().equals(pvpId)) {
            event.getWhoClicked().sendMessage("§cEste servidor não está disponivel.");
        }
        String bedId = ids.last();
        if (event.getNPC().getId().equals(bedId)) {
            event.getWhoClicked().sendMessage("§cEste servidor não está disponivel.");
        }
    }
}
