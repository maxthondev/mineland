package me.mineland.spigot.api.inventories;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface MenuClickHandler {

    void onClick(Player player, Inventory inventory, ClickType type, ItemStack itemStack, int slot);
}
