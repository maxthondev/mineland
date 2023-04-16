package me.mineland.core.server.type;

import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;
import me.mineland.core.server.ServerType;
import me.mineland.core.server.game.GameStages;
import me.mineland.core.server.game.GameType;

@Getter
@Setter
public class GameServer extends NetworkServer {

    private int playersGaming;
    private int tempo;
    private GameStages stage;
    private String mapName;
    private GameType gameType;

    public GameServer(ServerType serverType, int serverId) {
        super(serverType, serverId);

        this.playersGaming = 0;
        this.tempo = 0;
        this.stage = GameStages.OFFLINE;
        this.mapName = "Unknown";
        this.gameType = GameType.UNKNOWN;
    }

    public void updateData(final JsonObject json) {
        update(json);

        setGameType(json.has("gameType")
                ? (json.get("gameType").getAsString().equalsIgnoreCase("SOLO") ? GameType.SOLO : GameType.DUO)
                : GameType.UNKNOWN);

        setMapName(json.has("mapName") ? json.get("mapName").getAsString() : "Unknown");
        setPlayersGaming(json.has("playersGaming") ? json.get("playersGaming").getAsInt() : 0);
        setTempo(json.has("tempo") ? json.get("tempo").getAsInt() : 0);
        setStage(json.has("stage") ? GameStages.getStage(json.get("stage").getAsString()) : GameStages.OFFLINE);
    }

    public JsonObject toJsonGame() {
        JsonObject json = toJson();

        json.addProperty("playersGaming", getPlayersGaming());
        json.addProperty("tempo", getTempo());
        json.addProperty("stage", getStage().getNome());
        json.addProperty("mapName", getMapName());
        json.addProperty("gameType", getGameType().getName());

        return json;
    }
}
