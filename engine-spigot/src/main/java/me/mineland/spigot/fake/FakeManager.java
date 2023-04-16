package me.mineland.spigot.fake;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import me.mineland.core.controllers.GlobalController;
import me.mineland.core.player.roles.RoleController;
import me.mineland.core.player.roles.controllers.TagController;
import me.mineland.spigot.controllers.FakeController;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class FakeManager implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] data) {
        if (channel.equals("engine-core")) {
            ByteArrayDataInput in = ByteStreams.newDataInput(data);
            String subChannel = in.readUTF();
            if (subChannel.equals("FAKE")) {
                Player fakePlayer = Bukkit.getPlayerExact(in.readUTF());
                if (fakePlayer != null) {
                    String name = in.readUTF();
                    FakeController.apply(player, name);
                    player.setDisplayName(RoleController.MEMBRO.getPrefix() + name);
                    TagController.remove(player);
                    TagController tag = TagController.create(player);
                    tag.setOrder("Z");
                    tag.setSuffix(RoleController.MEMBRO.getColoredName());
                    tag.update();
                    player.setAllowFlight(false);
                    player.setFlying(false);

                    GlobalController.refreshPlayer(fakePlayer);
                }
            }
        }
    }

}
