package me.mineland.spigot;

import lombok.Getter;
import lombok.Setter;
import me.mineland.core.Fields;
import me.mineland.core.General;
import me.mineland.core.instances.PluginInstances;
import me.mineland.core.server.ServerType;
import me.mineland.spigot.api.builder.ActionItemListener;
import me.mineland.spigot.api.inventories.MenuListener;
import me.mineland.spigot.commands.SpigotCommands;
import me.mineland.spigot.listeners.ListenerLoader;
import me.mineland.spigot.listeners.list.ChatListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotMain extends JavaPlugin {

    private PluginManager pluginManager;

    @Getter
    @Setter
    private static ServerType serverType;

    @Getter
    @Setter
    private static int serverID;

    private static SpigotMain instance;

    @Override
    public void onLoad() {
        setServerType(ServerType.getServer(getConfig().getString("Servidor")));
        setServerID(getConfig().getInt("ServidorID"));
        saveDefaultConfig();
        General.setPluginInstance(PluginInstances.SPIGOT);
    }

    @Override
    public void onEnable() {
        instance = this;
        setup();
        pluginManager = Bukkit.getPluginManager();
        SpigotCommands.setupCommands();
        MenuListener.registerListeners();

        new ListenerLoader(this).load("me.mineland.spigot.api.builder");
        new ListenerLoader(this).load("me.mineland.spigot.listeners.list");
        Bukkit.getConsoleSender().sendMessage(Fields.PREFIX_PLUGIN + "§7Plugin §bessencialmente §7carregado com sucesso.");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(Fields.PREFIX_PLUGIN + "§7Plugin §bessencialmente §7descarregado.");
    }

    public void setup() {
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    public static SpigotMain getInstance() {
        return instance;
    }

    public static void console(String msg) {
        Bukkit.getConsoleSender().sendMessage("[Commons] " + msg);
    }

    public static void runAsync(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(getInstance(), runnable);
    }

    public static void runLater(Runnable runnable) {
        runLater(runnable, 5);
    }

    public static void runLater(Runnable runnable, long ticks) {
        Bukkit.getScheduler().runTaskLater(getInstance(), runnable, ticks);
    }

    public static void runSync(Runnable runnable) {
        if (getInstance().isEnabled() && !getInstance().getServer().isPrimaryThread()) {
            getInstance().getServer().getScheduler().runTask(getInstance(), runnable);
        } else {
            runnable.run();
        }
    }

}
