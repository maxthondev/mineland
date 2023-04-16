package me.mineland.provider.events;

import me.mineland.core.Fields;
import me.mineland.core.api.TablistController;
import me.mineland.core.player.roles.RoleController;
import me.mineland.core.player.roles.bukkit.RoleManager;
import me.mineland.provider.inventories.InventoryManager;
import me.mineland.spigot.api.game.BuildController;
import me.mineland.spigot.api.title.TitleController;
import me.mineland.spigot.update.SpigotUpdateEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;

@SuppressWarnings("deprecation")
public class PlayerListeners implements Listener {

    @EventHandler
    public void onJoinPlayer(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.playSound(player.getLocation(), Sound.EXPLODE, 6.0F, 1.0F);
        TablistController.sendTab(player, "\n§6§lMINE§f§lLAND §8➟ §fHUB\n", "\n§eAdquira VIP ou Créditos em: §fmineland.com.br/shop\n");
        player.setHealth(1.7D);
        player.setLevel(0);
        player.setExp(0.0F);
        TitleController.sendTitle(player, "§a§lMINE LAND", "§eSeja bem-vindo!", 1, 1, 1);
        player.setExhaustion(0.0F);
        player.setMaxHealth(1.7D);
        player.setFoodLevel(20);
        player.setGameMode(GameMode.ADVENTURE);
        player.getActivePotionEffects().forEach(effect -> player.removePotionEffect(effect.getType()));

        if (player.hasPermission(Fields.PREFIX_PERMISSION + ".fly")) {
            player.setAllowFlight(true);
            player.setFlying(true);
        }

        RoleManager.setRolePlayer(player);

    }

    @EventHandler
    public void onJoinVipAnnounce(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        RoleController role = RoleController.getRolePlayer(player);
        if (player.hasPermission(Fields.PREFIX_PERMISSION + ".vip")) {
            event.setJoinMessage(
                    "§a➜ " + role.getPrefix() + player.getName() + " §aentrou neste lobby!");
        } else {
            event.setJoinMessage(null);
        }
    }

    @EventHandler
    public void onQuitPlayer(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        RoleManager.removeRolePlayer(player);
        event.setQuitMessage(null);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (BuildController.isBuildMode() == false) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (BuildController.isBuildMode() == false) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void onRegain(EntityRegainHealthEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onSpread(BlockSpreadEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        event.setCancelled(event.toWeatherState());
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        event.setCancelled(true);

        if (event.getCause() == EntityDamageEvent.DamageCause.VOID) {
            event.getEntity().teleport(Bukkit.getWorld("Lobby").getSpawnLocation());
        }
    }

    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockBurn(BlockBurnEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockFade(BlockFadeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPortal(PlayerPortalEvent event) {
        event.setCancelled(true);
    }

	/*
	@EventHandler
	public void onBlockForm(BlockFormEvent event) {
		event.setCancelled(true);
	}*/

    @EventHandler
    public void onBlockFromTo(BlockFromToEvent event) {
        event.setCancelled(true);
    }

	/*
	@EventHandler
	public void onBlockGrow(BlockGrowEvent event) {
		event.setCancelled(true);
	}*/

    @EventHandler
    public void onBlockPhysics(BlockPhysicsEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onSecond(SpigotUpdateEvent event) {
        if (event.getType() != SpigotUpdateEvent.UpdateType.SEGUNDO) {
            return;
        }
        InventoryManager.update();
    }


    @EventHandler
    public void onCraft(CraftItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onExplosion(ExplosionPrimeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onEntitySpawn(CreatureSpawnEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onItemSpawn(ItemSpawnEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPhysics(BlockPhysicsEvent event) {
        event.setCancelled(true);
    }

}
