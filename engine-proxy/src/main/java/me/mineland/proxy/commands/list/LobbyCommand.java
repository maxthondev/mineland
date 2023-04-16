package me.mineland.proxy.commands.list;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.mineland.proxy.commands.ProxyCommands;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import java.util.Arrays;
public class LobbyCommand extends ProxyCommands {

    private String label;

    public LobbyCommand() {
        super("lobby", "lb", "l", "hub", "h");
    }


    @Override
    public void perform(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(TextComponent.fromLegacyText("§cEste comando é somente para jogadores."));
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;
        String serverName = player.getServer().getInfo().getName();
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("SendPlayerLobby");
        out.writeUTF(player.getName());
        out.writeUTF("lobby");
        player.getServer().sendData("engine-core", out.toByteArray());
    }


}
