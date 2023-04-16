package me.mineland.proxy.listeners;

import lombok.RequiredArgsConstructor;
import me.mineland.core.api.getter.ClassGetter;
import me.mineland.proxy.ProxyMain;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

@RequiredArgsConstructor
public class ListenerLoader {
    private final Plugin plugin;

    public void load(String pkgName) {
        for (Class<?> classes : ClassGetter.getClassesForPackage(plugin, pkgName)) {
            if (Listener.class.isAssignableFrom(classes)) {
                try {
                    Listener listener = (Listener) classes.newInstance();
                    ProxyMain.getInstance().getProxy().getPluginManager().registerListener(plugin, listener);

                    System.out.println("> Event " + classes.getSimpleName() + " has been successfully loaded as a Listener!");
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();

                    System.out.println("> An error occurred while loading the " + classes.getSimpleName() + " event!");
                }
            }
        }
    }
}
