package me.mineland.spigot.commands.list;
import me.mineland.core.Fields;
import me.mineland.core.player.ProfilePlayer;
import me.mineland.spigot.commands.SpigotCommands;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand extends SpigotCommands {
    public FlyCommand() {
        super("fly", "voar");
    }

    @Override
    public void perform(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cEste comando é somente para jogadores.");
            return;
        }
        Player player = (Player) sender;
        if (!player.hasPermission(Fields.PREFIX_PERMISSION + ".cmd.fly")) {
            player.sendMessage(Fields.NO_PERMISSION);
            return;
        }
        //if () { Fazer quando estiver get de estar jogando.
       //     player.sendMessage("§cVocê só pode ativar/desativar o fly caso estaja em algum lobby.");
       //     return;
       // }
        if (player.getAllowFlight()) {
            player.setAllowFlight(false);
            player.sendMessage("§eAgora você já não pode mais voar.");
        } else {
            player.setAllowFlight(true);
            player.sendMessage("§eAgora você pode voar.");
        }
    }

}
