package me.mineland.spigot.inventories.profile;

import me.mineland.core.player.ProfilePlayer;
import me.mineland.core.player.roles.RoleController;
import me.mineland.spigot.api.builder.ItemBuilder;
import me.mineland.spigot.api.inventories.ClickType;
import me.mineland.spigot.api.inventories.MenuClickHandler;
import me.mineland.spigot.api.inventories.MenuInventory;
import me.mineland.spigot.inventories.ProfileInventory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class StatisticInventory extends MenuInventory {

    public StatisticInventory(String nick) {
        this(nick, nick);
    }

    public StatisticInventory(final String nickViewer, final String nickAlvo) {
        super("Minhas estatísticas", 3);

        String s = nickAlvo.equalsIgnoreCase(nickViewer) ? nickViewer : nickAlvo;

        RoleController roleController = RoleController.getRolePlayer(Bukkit.getPlayer(s));

        ProfilePlayer profilePlayer = ProfilePlayer.getByPlayer(Bukkit.getPlayer(s));

        ItemBuilder itemBuilder = new ItemBuilder();

        setItem(10, itemBuilder.type(Material.DIAMOND_SWORD).name("§aKit PvP").lore(
                "§eClique para ver mais.").build());

        setItem(11, itemBuilder.type(Material.BED).name("§aBed Wars").lore(
                "§eClique para ver mais.").build());

        setItem(22, itemBuilder.type(Material.ARROW).name("§aVoltar").lore(
                "§7para o menu principal.").build(), new MenuClickHandler() {
            @Override
            public void onClick(Player player, Inventory inventory, ClickType type, ItemStack itemStack, int slot) {
                player.closeInventory();

                new ProfileInventory(nickViewer, player.getName()).open(player);
            }
        });
    }

}
