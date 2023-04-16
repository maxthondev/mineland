package me.mineland.provider.inventories;

import me.mineland.provider.inventories.list.GamesInventory;
import me.mineland.spigot.api.inventories.ClickType;
import me.mineland.spigot.api.inventories.MenuClickHandler;
import me.mineland.spigot.inventories.ProfileInventory;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryManager {

    private static GamesInventory gamesInventory = null;
    private static ProfileInventory profileInventory = null;

    private static final MenuClickHandler defaultClickHandler = new MenuClickHandler() {

        @Override
        public void onClick(Player player, Inventory inv, ClickType type, ItemStack stack, int slot) {
            player.closeInventory();

            String itemName = ChatColor.stripColor(stack.getItemMeta().getDisplayName().trim());

            if (itemName.equalsIgnoreCase("Lobby Principal")) {
                player.sendMessage("§cVocê já está conectado a este servidor.");
            } else if (itemName.equalsIgnoreCase("RankUP")) {
                player.sendMessage("§cEste servidor não está disponivel no momento.");
            } else if (itemName.equalsIgnoreCase("Kit PvP")) {
                player.sendMessage("§cEste servidor não está disponivel no momento.");
            } else if (itemName.equalsIgnoreCase("Bed Wars")) {
                player.sendMessage("§cEste servidor não está disponivel no momento.");
            }
        }
    };

    public static void update() {
        getGamesInventory().update();
    }

    public static MenuClickHandler getDefaultClickHandler() {
        return defaultClickHandler;
    }

    public static GamesInventory getGamesInventory() {
        if (gamesInventory == null) {
            gamesInventory = new GamesInventory();
        }
        return gamesInventory;
    }

}
