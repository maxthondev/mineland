package me.mineland.provider.inventories.list;

import me.mineland.provider.inventories.InventoryManager;
import me.mineland.spigot.api.builder.ItemBuilder;
import me.mineland.spigot.api.inventories.MenuInventory;
import org.bukkit.Material;

public class GamesInventory extends MenuInventory {

    public GamesInventory() {
        super("Jogos disponiveis em MineLand", 4);

        update();
    }

    public void update() {
        ItemBuilder itemBuilder = new ItemBuilder();

        setItem(10, itemBuilder.type(Material.BOOKSHELF).name("§6Lobby Principal").lore("§eClique para entrar!").build(), InventoryManager.getDefaultClickHandler());

        setItem(12, itemBuilder.type(Material.DIAMOND_SWORD).name("§aKit PvP").lore(
                "§7",
                "  §7Nada como um bom treinamento para melhorar",
                "  §7suas habilidades no pvp.",
                "",
                "§aClique para jogar!",
                "§70 jogando.").build(), InventoryManager.getDefaultClickHandler());

        setItem(13, itemBuilder.type(Material.BED).name("§aBed Wars").lore(
                "§7",
                "  §7Este jogo é de tirar o sono! O objetivo",
                "  §7é simples e curioso, destruir a cama de",
                "  §7seus inimigos e proteger a sua.",
                "",
                "§a§l • §fSolo",
                "§a§l • §fDupla",
                "§a§l • §fQuarteto",
                "",
                "§aClique para jogar!",
                "§70 jogando.").build(), InventoryManager.getDefaultClickHandler());

        setItem(14, itemBuilder.type(Material.DIAMOND_PICKAXE).name("§aRankUP").lore(
                "§7",
                "  §7Um dos modos de jogo mais popular da história",
                "  §7do Minecraft, uma experiência inovadora.",
                "",
                "§a§l • §fEventos",
                "§a§l • §fEncantamentos especiais",
                "§a§l • §fSpawners",
                "§a§l • §fCrates",
                "",
                "§aClique para jogar!",
                "§70 jogando.").build(), InventoryManager.getDefaultClickHandler());

    }
}
