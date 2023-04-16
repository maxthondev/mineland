package me.mineland.core;

import lombok.Getter;
import lombok.Setter;
import me.mineland.core.instances.PluginInstances;
import java.util.concurrent.TimeUnit;

@Getter
public class General {

    public static final boolean LOBBY_HG = false;

    private static PluginInstances pluginInstances = PluginInstances.NONE;

    public static PluginInstances getPluginInstance() {
        return pluginInstances;
    }

    public static void setPluginInstance(PluginInstances pluginInstance) {
        General.pluginInstances = pluginInstance;
    }

}
