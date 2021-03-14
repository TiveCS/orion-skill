package io.github.tivecs.orionskill.player;

import io.github.tivecs.orionskill.targeter.DynamicTargetData;
import io.github.tivecs.orionskill.targeter.StaticTargetData;

public class PlayerTargetData {

    private PlayerSkillExecutionData executionData;

    private StaticTargetData staticTargets;
    private DynamicTargetData dynamicTargets;

    public PlayerTargetData(PlayerSkillExecutionData executionData){
        this.executionData = executionData;

        this.staticTargets = new StaticTargetData(this);
        this.dynamicTargets = new DynamicTargetData(this);
    }

    public StaticTargetData getStaticTargets() {
        return staticTargets;
    }

    public DynamicTargetData getDynamicTargets() {
        return dynamicTargets;
    }

    public PlayerSkillExecutionData getExecutionData() {
        return executionData;
    }
}
