package me.mineland.spigot.inventories;

import me.mineland.core.player.ProfilePlayer;
import me.mineland.core.player.containers.GlobalContainer;
import me.mineland.core.player.roles.RoleController;
import me.mineland.core.utils.StringUtils;
import me.mineland.spigot.api.builder.ItemBuilder;
import me.mineland.spigot.api.inventories.ClickType;
import me.mineland.spigot.api.inventories.MenuClickHandler;
import me.mineland.spigot.api.inventories.MenuInventory;
import me.mineland.spigot.inventories.profile.StatisticInventory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ProfileInventory extends MenuInventory {

    public ProfileInventory(String nick) {
        this(nick, nick);
    }

    public ProfileInventory(final String nickViewer, final String nickAlvo) {
        super("Minhas informações", 3);

        String s = nickAlvo.equalsIgnoreCase(nickViewer) ? nickViewer : nickAlvo;

        RoleController roleController = RoleController.getRolePlayer(Bukkit.getPlayer(s));

        ProfilePlayer profilePlayer = ProfilePlayer.getByPlayer(Bukkit.getPlayer(s));

        ItemBuilder itemBuilder = new ItemBuilder();

        setItem(10, itemBuilder.type(Material.ITEM_FRAME).name("§ASkins").lore(
                "§7Veja uma listagem de skins disponíveis para você!").build());

        setItem(11, itemBuilder.type(Material.SKULL_ITEM).skin(s).name("§a" + s).lore(
                "§fGrupo: " + roleController.getColoredTag(),
                "",
                "§fPrimeiro login: §8-/-",
                "§fÚltimo login: §8-/-"
        ).build());

        setItem(12, itemBuilder.type(Material.REDSTONE_COMPARATOR).name("§aPreferências").lore(
                "§7Selecione preferências diante de sua pessoa.").build());

        setItem(14, itemBuilder.type(Material.PAPER).name("§aEstatísticas").lore(
                "§7Veja suas estatísticas de jogatina!").build(), new MenuClickHandler() {
            @Override
            public void onClick(Player player, Inventory inventory, ClickType type, ItemStack itemStack, int slot) {
                player.closeInventory();

                new StatisticInventory(nickViewer, player.getName()).open(player);
            }
        });

        setItem(15, itemBuilder.type(Material.CHEST).name("§aColetáveis").lore(
                "§7Colete cosméticos e itens aleatorios para sua pessoa.").build());

        setItem(16, itemBuilder.type(Material.getMaterial(384)).name("§aLoja de Upgrades").lore(
                "§7Faça um upgrade rápido e/ou compre um vip de tempo",
                "§7limitado para você.").build());

    }

}
