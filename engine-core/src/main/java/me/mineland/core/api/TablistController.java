package me.mineland.core.api;

import java.lang.reflect.Field;

import me.mineland.core.packets.PacketController;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;

public class TablistController {

    public static void sendTab(Player p, String Header, String Footer) {
        try {
            IChatBaseComponent header = ChatSerializer.a("{\"text\": \"" + Header + "\"}");
            IChatBaseComponent footer = ChatSerializer.a("{\"text\": \"" + Footer + "\"}");
            PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
            Field h = packet.getClass().getDeclaredField("a");
            Field f = packet.getClass().getDeclaredField("b");
            h.set(packet, header);
            f.set(packet, footer);
            h.setAccessible(true);
            f.setAccessible(true);
            PacketController.registerPacket(p, packet);
        } catch (Exception e) {

        }
    }

}

