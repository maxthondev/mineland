package me.mineland.spigot.commands.list;

import me.mineland.core.packets.PacketController;
import me.mineland.core.player.ProfilePlayer;
import me.mineland.core.player.roles.RoleController;
import me.mineland.spigot.api.inventories.MenuListener;
import me.mineland.spigot.commands.SpigotCommands;
import me.mineland.spigot.inventories.ProfileInventory;
import me.mineland.spigot.inventories.profile.StatisticInventory;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ProfileCommand extends SpigotCommands {

    public ProfileCommand() {
        super("profile", "conta", "account", "acc");
    }

    @Override
    public void perform(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cEste comando é somente para jogadores.");
            return;
        }

        if (!MenuListener.isOpenMenus()) {
            sender.sendMessage("§cVocê não pode executar isto agora.");
            return;
        }

        Player player = (Player) sender;
        final String nick = player.getName();
        final String nickViewer = String.valueOf(ProfilePlayer.getByPlayer(player));
        if (args.length == 0) {
            new ProfileInventory(nickViewer, nick).open(player);
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("statistic")) {
                new StatisticInventory(nickViewer, nick).open(player);
            } else {
                player.sendMessage("§cInventário não encontrado.");
            }
        }

    }
}
