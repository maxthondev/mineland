package me.mineland.spigot.listeners;

import lombok.RequiredArgsConstructor;
import me.mineland.core.Fields;
import me.mineland.core.api.getter.ClassGetter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

@RequiredArgsConstructor
public class ListenerLoader {
    private final JavaPlugin plugin;

    public void load(String pkgName) {
        for (Class<?> classes : ClassGetter.getClassesForPackage(plugin, pkgName)) {
            if (Listener.class.isAssignableFrom(classes)) {
                try {
                    Listener listener = (Listener) classes.newInstance();
                    Bukkit.getPluginManager().registerEvents(listener, plugin);

                    System.out.println("> Event " + classes.getSimpleName() + " has been successfully loaded as a Listener!");
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();

                    System.out.println("> An error occurred while loading the " + classes.getSimpleName() + " event!");
                }
            }
        }
    }
}
