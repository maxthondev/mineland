package me.mineland.spigot.commands;

import me.mineland.spigot.SpigotMain;
import me.mineland.spigot.commands.list.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;

import java.util.Arrays;
import java.util.logging.Level;

public abstract class SpigotCommands extends Command {

    public SpigotCommands(String name, String... aliases) {
        super(name);
        this.setAliases(Arrays.asList(aliases));

        try {
            SimpleCommandMap simpleCommandMap = (SimpleCommandMap) Bukkit.getServer().getClass().getDeclaredMethod("getCommandMap").invoke(Bukkit.getServer());
            simpleCommandMap.register(this.getName(), "engine-spigot", this);
        } catch (ReflectiveOperationException ex) {
            SpigotMain.getInstance().getLogger().log(Level.SEVERE, "Cannot register command: ", ex);
        }
    }

    public static void setupCommands() {
        new BuildCommand();
        new FlyCommand();
        new PingCommand();
        new ProfileCommand();
    }

    public abstract void perform(CommandSender sender, String label, String[] args);

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        this.perform(sender, commandLabel, args);
        return true;
    }
}

