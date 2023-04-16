package me.mineland.core.player;

import com.google.common.collect.ImmutableList;
import lombok.Getter;
import lombok.Setter;
import me.mineland.core.Fields;
import me.mineland.core.player.containers.AbstractContainer;
import me.mineland.core.player.containers.DataContainer;
import me.mineland.core.player.containers.GlobalContainer;
import me.mineland.core.player.ranking.ThePitRanking;
import me.mineland.core.player.roles.RoleController;
import me.mineland.core.player.roles.bukkit.RoleManager;
import me.mineland.core.utils.StringUtils;
import me.mineland.core.utils.UtilsProfile;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONObject;

import java.util.*;

@Getter
@Setter
@SuppressWarnings("unchecked")
public class ProfilePlayer {

    private String name;
    private Player player;
    private RoleController group;
    private ThePitRanking thePitRanking;
    private RoleManager roleManager;
    private Map<String, Map<String, DataContainer>> schemas;


    public ProfilePlayer(Player player) {
        this.player = player;
        this.name = player.getName();
        this.schemas = new HashMap<>();
        this.group = RoleController.getRolePlayer(player);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroup(RoleController group) {
        this.group = group;
    }

    public Map<String, Map<String, DataContainer>> getSchemas() {
        return schemas;
    }

    public String getName() {
        return this.name;
    }

    public Player getPlayer() {
        return this.player;
    }

    public RoleController getGroup() {
        return this.group;
    }

    public static final LinkedHashSet<UUID> STAFF = new LinkedHashSet<>();
    public static final List<UUID> INVISIBLE = new ArrayList<>();
    private static final Map<UUID, ProfilePlayer> PLAYERS = new HashMap<>();

    public void computeData(String schema, JSONObject data) {
        Map<String, DataContainer> containerMap = new LinkedHashMap<>();
        data.forEach((key, value) -> {
            containerMap.put(key.toString(), new DataContainer(value.toString()));
        });
        this.schemas.put(schema, containerMap);
    }

    public static ProfilePlayer create(Player player) {
        PLAYERS.computeIfAbsent(player.getUniqueId(), list -> new ProfilePlayer(player));
        if (!STAFF.contains(player.getUniqueId()) && player.hasPermission(Fields.PREFIX_PERMISSION + ".staff")) {
            STAFF.add(player.getUniqueId());
        }
        return getByPlayer(player);
    }

    public static ProfilePlayer remove(Player player) {
        STAFF.remove(player.getUniqueId());
        INVISIBLE.remove(player.getUniqueId());
        return PLAYERS.remove(player.getUniqueId());
    }

    public DataContainer getDataContainer(String schema, String key) {
        return this.schemas.get(schema).get(key);
    }

    public <T extends AbstractContainer> T getAbstractContainer(String schema, String key, Class<T> containerClass) {
        return this.getDataContainer(schema, key).getContainer(containerClass);
    }

    public GlobalContainer getGlobalContainer() {
        return this.getAbstractContainer("Global_Profile", "lobbys", GlobalContainer.class);
    }

    public static ProfilePlayer getByUUID(UUID uuid) {
        return PLAYERS.getOrDefault(uuid, null);
    }

    public static ProfilePlayer getByPlayer(Player player) {
        return getByUUID(player.getUniqueId());
    }

    public static Collection<ProfilePlayer> listPlayers() {
        return ImmutableList.copyOf(PLAYERS.values());
    }

}
