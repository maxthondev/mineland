package me.mineland.core.instances;

public enum PluginInstances {

    PROXY, SPIGOT, NONE;

    public boolean isSpigot() {
        return this == SPIGOT;
    }

    public boolean isProxy() {
        return this == PROXY;
    }

}
