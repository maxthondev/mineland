package me.mineland.spigot.commands.list;

import me.mineland.core.packets.PacketController;
import me.mineland.core.player.ProfilePlayer;
import me.mineland.core.player.roles.RoleController;
import me.mineland.spigot.commands.SpigotCommands;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PingCommand extends SpigotCommands {

    public PingCommand() {
        super("ping", "pg", "ms");
    }

    @Override
    public void perform(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cEste comando é somente para jogadores.");
            return;
        }
        Player player = (Player) sender;
        RoleController roleController = RoleController.getRolePlayer(player);
        ProfilePlayer profilePlayer = ProfilePlayer.getByPlayer(player);
        if (args.length == 0) {
            player.sendMessage("§eSeu ping é de: §7" + PacketController.getPing(player) + "§7ms§e.");
            return;
        }

        Player playerArg = Bukkit.getPlayer(args[0]);
        if (playerArg == null) {
            player.sendMessage("§cUsuário não encontrado.");
            return;
        }
        player.sendMessage("§eO ping de: §7" + playerArg.getName() + " §eé de: §7" + PacketController.getPing(playerArg) + "§7ms§e.");
    }

}
