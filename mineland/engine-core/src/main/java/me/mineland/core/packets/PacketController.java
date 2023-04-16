package me.mineland.core.packets;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.Packet;

public class PacketController {

    public static void registerPacket(Player p, Packet<?> packetType) {
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packetType);
    }

    public static int getPing(Player p) {
        return ((CraftPlayer) p).getHandle().ping;
    }

}
