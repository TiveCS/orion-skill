package io.github.tivecs.orionskill.player;

import io.github.tivecs.orionskill.OrionSkill;
import io.github.tivecs.orionskill.config.SkillExecutionConfig;
import io.github.tivecs.orionskill.event.PlayerSkillExecutionEvent;
import io.github.tivecs.orionskill.execution.ExecutionPattern;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class PlayerSkillExecutionData {

    private final static OrionSkill plugin = OrionSkill.getPlugin(OrionSkill.class);
    private static SkillExecutionConfig config = plugin.getSkillExecutionConfig();

    private PlayerData playerData;
    private List<ExecutionPattern> currentPattern;

    private PlayerTargetData targetData;

    public PlayerSkillExecutionData(PlayerData playerData){
        this.playerData = playerData;
        this.currentPattern = new ArrayList<>();
        this.targetData = new PlayerTargetData(this);
    }

    public void addPattern(ExecutionPattern pattern){
        currentPattern.add(pattern);
        if (currentPattern.size() >= config.getPatternSize()){
            executeEquippedSkill();
        }
    }

    public void executeEquippedSkill(){
        PlayerSkillExecutionEvent executionEvent = new PlayerSkillExecutionEvent(getPlayerData());
        Bukkit.getPluginManager().callEvent(executionEvent);
        if (!executionEvent.isCancelled()) {
            // do something here
        }
    }

    public PlayerTargetData getTargetData() {
        return targetData;
    }

    public PlayerData getPlayerData() {
        return playerData;
    }

    public List<ExecutionPattern> getCurrentPattern() {
        return currentPattern;
    }
}
