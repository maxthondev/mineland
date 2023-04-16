package me.mineland.provider.events;

import me.mineland.provider.inventories.InventoryManager;
import me.mineland.spigot.api.builder.ActionItemStack;
import me.mineland.spigot.api.builder.ItemBuilder;
import me.mineland.spigot.inventories.ProfileInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.PlayerInventory;

public class InventoryListeners implements Listener {
    private boolean registered = false;

    private void register() {
        if (registered)
            return;
        registered = true;
        ActionItemStack.register("§aMenu de jogos", gamesModeHandler);
        ActionItemStack.register("§aMinhas informações", myInformHandler);
    }

    @EventHandler
    public void onJoinItems(PlayerJoinEvent event) {
        register();
        Player player = event.getPlayer();
        PlayerInventory playerInventory = player.getInventory();

        playerInventory.clear();
        playerInventory.setArmorContents(null);
        ItemBuilder itemBuilder = new ItemBuilder();

        playerInventory.setItem(0, itemBuilder.type(Material.COMPASS).name("§aMenu de jogos").lore("§7Listagem de jogos disponíveis em MineLand.").build());
        playerInventory.setItem(1,
                itemBuilder.type(Material.SKULL_ITEM).skin(player.getName()).name("§aMinhas informações").lore("§7Veja suas informações e estatísticas referentes\n§7a sua jogatina.").build());

        playerInventory.setItem(7, itemBuilder.type(Material.INK_SACK).durability(10)
                .name("§fJogadores: §aON").build());
        playerInventory.setItem(8, itemBuilder.type(Material.NETHER_STAR).name("§aMenu de lobbies").lore("§7Listagem de lobbies disponíveis em MineLand.").build());

        player.updateInventory();

    }

    private ProfileInventory profileInventory;
    private final ActionItemStack.InteractHandler myInformHandler = (player, itemStack, itemAction, clickedBlock) -> {
        if (!itemAction.name().contains("RIGHT"))
            return true;
        player.chat("/profile");
        return true;
    };

    private final ActionItemStack.InteractHandler gamesModeHandler = (player, itemStack, itemAction, clickedBlock) -> {
        if (!itemAction.name().contains("RIGHT"))
            return true;
        InventoryManager.getGamesInventory().open(player);
        return true;
    };

}
