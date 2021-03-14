package io.github.tivecs.orionskill.player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerManager {

    private HashMap<UUID, PlayerData> playersData;

    public PlayerManager(){
        this.playersData = new HashMap<>();
    }

    public PlayerData register(UUID uniqueId){
        PlayerData pd = new PlayerData(uniqueId);

        getPlayersData().put(uniqueId, pd);
        return pd;
    }

    public PlayerData registerIfNotExists(UUID uniqueId){
        if (!getPlayersData().containsKey(uniqueId)){
            return register(uniqueId);
        }
        return getPlayerData(uniqueId);
    }

    public PlayerData getPlayerData(UUID uniqueId){
        return getPlayersData().getOrDefault(uniqueId, register(uniqueId));
    }

    private HashMap<UUID, PlayerData> getPlayersData() {
        return playersData;
    }

}
