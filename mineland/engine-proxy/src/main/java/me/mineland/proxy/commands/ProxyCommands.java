package me.mineland.proxy.commands;

import me.mineland.proxy.ProxyMain;
import me.mineland.proxy.commands.list.*;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;

public abstract class ProxyCommands extends Command {

    public ProxyCommands(String name, String... aliases) {
        super(name, null, aliases);
        ProxyServer.getInstance().getPluginManager().registerCommand(ProxyMain.getInstance(), this);
    }

    public abstract void perform(CommandSender sender, String[] args);

    @Override
    public void execute(CommandSender sender, String[] args) {
        this.perform(sender, args);
    }

    public static void setupCommands() {
        new BroadcastCommand();
        new LobbyCommand();
        new ReportCommand();
        new SendReportCommand();
        new StaffChatCommand();
        new FakeCommand();
        new FakeListCommand();
    }
}

