package me.mineland.core.server;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum ServerType {

    BUNGEECORD("BungeeCord", 2),
    LOGIN("Login", 2),
    LOBBY("Lobby", 2, "hub"),
    LOBBY_SW("LobbySW", 2, "lsw", "hubskywars", "hubsw", "LobbySW"),
    LOBBY_PVP("LobbyPvP", 2, "lpvp", "hubpvp", "LobbyPvP"),

    PVP_ARENA("Arena", 2),
    PVP_FPS("FPS", 2),
    PVP_LAVACHALLENGE("LavaChallenge", 2),

    SW_SOLO("SwSolo", 2, "swsolo", "solosw", "ss"),
    SW_DUPLA("SwDupla", 2, "swdupla", "duplasw", "sd"),

    BEDWARS("BedWars", 2, "bw"),

    UNKNOWN("Unknown");

    private final String name;
    private final List<String> aliases;
    private final int secondsToStabilize;
    private final int secondsUpdateStatus;

    ServerType(String name, int secondsToStabilize, int secondsUpdateStatus, String... aliases) {
        this.name = name;
        this.aliases = Arrays.asList(aliases);
        this.secondsToStabilize = secondsToStabilize;
        this.secondsUpdateStatus = secondsUpdateStatus;
    }

    ServerType(String name, int secondsToStabilize) {
        this(name, secondsToStabilize, 2, "Unknown");
    }

    ServerType(String name) {
        this(name, 3, 2, "Unknown");
    }

    ServerType(String name, String... aliases) {
        this(name, 3, 2, aliases);
    }

    ServerType(String name, int secondsUpdateStatus, String... aliases) {
        this(name, 3, secondsUpdateStatus, aliases);
    }

    public static ServerType getServer(String serverName) {
        serverName = serverName.replaceAll("\\d", "");

        ServerType finded = ServerType.UNKNOWN;

        for (ServerType servers : ServerType.values()) {
            if (servers.getName().equalsIgnoreCase(serverName)) {
                finded = servers;
                break;
            }
            if (servers.containsAlias(serverName)) {
                finded = servers;
                break;
            }
            if (servers.getName().startsWith(serverName)) {
                finded = servers;
                break;
            }
        }
        return finded;
    }

    public boolean useActionItem() {
        if (isLobby()) return true;
        if (isSkyWars()) return true;
        return isPvP();
    }

    public boolean useMenuListener() {
        if (isLobby()) return true;
        if (isSkyWars()) return true;
        return isPvP();
    }

    public boolean isLobby() {
        return this.getName().startsWith("Lobby");
    }

    public boolean isPvP() {
        return isPvP(false);
    }

    public boolean isPvP(boolean lobby) {
        if (lobby) {
            if (this == LOBBY_PVP) {
                return true;
            }
        }
        return this == PVP_ARENA || this == PVP_FPS || this == PVP_LAVACHALLENGE;
    }

    public boolean isSkyWars() {
        return isPvP(false);
    }

    public boolean isSkyWars(boolean lobby) {
        if (lobby) {
            if (this == LOBBY_SW) {
                return true;
            }
        }
        return this == SW_SOLO || this == SW_DUPLA;
    }

    public boolean containsAlias(String serverName) {
        if (getAliases() == null) return false;

        boolean finded = false;

        for (String alias : getAliases()) {
            if (alias.equalsIgnoreCase(serverName)) {
                finded = true;
                break;
            }
        }

        return finded;
    }

    public boolean useSuffixRank() {
        return isPvP(true);
    }
}
