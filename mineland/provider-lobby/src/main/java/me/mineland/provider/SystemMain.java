package me.mineland.provider;

import lombok.Getter;
import lombok.Setter;
import me.mineland.core.Fields;
import me.mineland.core.instances.PluginInstances;
import me.mineland.provider.controller.LobbyNPC;
import me.mineland.provider.events.InventoryListeners;
import me.mineland.provider.inventories.InventoryManager;
import me.mineland.spigot.SpigotMain;
import me.mineland.spigot.listeners.ListenerLoader;
import net.jitse.npclib.NPCLib;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
@Setter
public class SystemMain extends JavaPlugin {

    private PluginManager pluginManager;
    @Getter
    @Setter
    private static SystemMain instance;

    private NPCLib library;

    @Getter
    @Setter
    private static PluginInstances pluginInstances;

    @Getter
    @Setter
    private static String lobbyId;

    @Override
    public void onLoad() {
        setInstance(this);
        saveDefaultConfig();

        Bukkit.getConsoleSender().sendMessage(Fields.PREFIX_PLUGIN + "§7Instancia §bserver-lobby §7gerada com sucesso.");
    }
    @Override
    public void onEnable() {
        setup();
        LobbyNPC.setup();
        InventoryManager.update();
        lobbyId = this.getConfig().getString("serverId");
        pluginManager = Bukkit.getPluginManager();

        new ListenerLoader(this).load("me.mineland.provider.controller");
        new ListenerLoader(this).load("me.mineland.provider.events");
        pluginManager.registerEvents(new InventoryListeners(), this);

        Bukkit.getConsoleSender().sendMessage(Fields.PREFIX_PLUGIN + "§7Plugin §bserver-lobby §7carregado com sucesso.");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(Fields.PREFIX_PLUGIN + "§7Plugin §bserver-lobby §7descarregado.");
    }

    public void setup() {
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    public static void console(String msg) {
        Bukkit.getConsoleSender().sendMessage("[Lobby] " + msg);
    }

    public static void runAsync(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(getInstance(), runnable);
    }

    public static void runLater(Runnable runnable) {
        Bukkit.getScheduler().runTaskLater(getInstance(), runnable, 5L);
    }

    public static void runLater(Runnable runnable, long ticks) {
        Bukkit.getScheduler().runTaskLater(getInstance(), runnable, ticks);
    }
}
