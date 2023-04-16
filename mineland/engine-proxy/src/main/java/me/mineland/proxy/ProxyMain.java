package me.mineland.proxy;

import com.mojang.brigadier.Message;
import lombok.Getter;
import lombok.Setter;
import me.mineland.core.Fields;
import me.mineland.proxy.commands.ProxyCommands;
import me.mineland.proxy.listeners.ListenerLoader;
import me.mineland.proxy.listeners.list.MessagesListener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.config.Configuration;

public class ProxyMain extends Plugin {

    @Getter
    @Setter
    private static ProxyMain instance;

    @Getter
    @Setter
    private Configuration configuration;

    @Getter
    private static PluginManager pluginManager;

    @Override
    public void onLoad() {
        setInstance(this);
        getProxy().registerChannel("engine-core");
    }

    @Override
    public void onEnable() {
        ProxyCommands.setupCommands();
        MessagesListener.setup();
        new ListenerLoader(this).load("me.mineland.proxy.listeners.list");
        getLogger().info(Fields.PREFIX_PLUGIN + "§7Plugin §bessencialmente §7carregado com sucesso.");
    }

    @Override
    public void onDisable() {
        getLogger().info(Fields.PREFIX_PLUGIN + "§7Plugin §bessencialmente §7descarregado com sucesso.");
    }

}
