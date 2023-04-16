package me.mineland.spigot.update;

import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
public class SpigotUpdateEvent extends Event {

    public static final HandlerList handlers = new HandlerList();
    private final UpdateType type;
    private final long currentTick;

    public SpigotUpdateEvent(UpdateType type) {
        this(type, -1);
    }

    public SpigotUpdateEvent(UpdateType type, long currentTick) {
        this.type = type;
        this.currentTick = currentTick;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public enum UpdateType {
        TICK, SEGUNDO, MINUTO, HORA
    }
}