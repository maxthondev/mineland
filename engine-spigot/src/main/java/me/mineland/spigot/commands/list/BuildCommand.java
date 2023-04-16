package me.mineland.spigot.commands.list;

import me.mineland.core.Fields;
import me.mineland.spigot.api.game.BuildController;
import me.mineland.spigot.commands.SpigotCommands;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildCommand extends SpigotCommands {

    public BuildCommand() {
        super("build", "construtor", "bm");
    }

    @Override
    public void perform(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cEste comando é somente para jogadores.");
            return;
        }

        Player player = (Player) sender;
        if (!player.hasPermission(Fields.PREFIX_PERMISSION + ".cmd.build")) {
            player.sendMessage(Fields.NO_PERMISSION);
            return;
        }

        if (BuildController.isBuildMode() == false) {
            BuildController.setBuildMode(true);
            player.sendMessage("§aModo contrutor ativado com sucesso.");
        } else {
            BuildController.setBuildMode(false);
            player.sendMessage("§cModo construtor desativado com sucesso.");
        }

    }
}
