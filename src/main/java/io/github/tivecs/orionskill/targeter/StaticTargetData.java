package io.github.tivecs.orionskill.targeter;

import io.github.tivecs.orionskill.player.PlayerTargetData;

import java.util.HashMap;

public class StaticTargetData {

    private PlayerTargetData playerTargetData;

    private HashMap<StaticTargetType, Object> targets;

    public StaticTargetData(PlayerTargetData playerTargetData){
        this.playerTargetData = playerTargetData;
        this.targets = new HashMap<>();

        initData();
    }

    public void initData(){
        getTargets().put(StaticTargetType.SELF, playerTargetData.getExecutionData().getPlayerData().getUniqueId());
    }

    public HashMap<StaticTargetType, Object> getTargets() {
        return targets;
    }

    public PlayerTargetData getPlayerTargetData() {
        return playerTargetData;
    }
}
