package io.github.tivecs.orionskill.config;

import io.github.tivecs.orionskill.OrionSkill;
import io.github.tivecs.orionskill.execution.ExecutionGroup;
import io.github.tivecs.orionskill.execution.ExecutionPattern;

import java.util.HashMap;
import java.util.HashSet;

public class SkillExecutionConfig {

    private final OrionSkill plugin = OrionSkill.getPlugin(OrionSkill.class);

    private int patternSize;
    private HashSet<ExecutionPattern> patternTypes;

    private ExecutionGroup defaultExecutionGroup;
    private HashMap<String, ExecutionGroup> executionGroups;

    public SkillExecutionConfig(){
        loadData();
    }

    public void loadData(){
        this.patternSize = plugin.getConfig().getInt("skill-execution.pattern-size");
        this.patternTypes = readPatternTypes();

        this.executionGroups = readExecutionGroups();
        this.defaultExecutionGroup = getExecutionGroups().getOrDefault(plugin.getConfig().getString("skill-execution.default-execution-group"), null);

    }

    public HashSet<ExecutionPattern> readPatternTypes(){
        HashSet<ExecutionPattern> pattern = new HashSet<>();
        for (String pt : plugin.getConfig().getStringList("skill-execution.pattern-types")){
            ExecutionPattern ep = ExecutionPattern.valueOf(pt.toUpperCase());
            pattern.add(ep);
        }
        return pattern;
    }

    public HashMap<String, ExecutionGroup> readExecutionGroups(){
        HashMap<String, ExecutionGroup> groups = new HashMap<>();
        String path = "skill-execution.execution-groups";

        for (String group : plugin.getConfig().getConfigurationSection(path).getKeys(false)){
            ExecutionGroup executionGroup = new ExecutionGroup(group, plugin.getConfig().getConfigurationSection(path + "." + group));
            groups.put(group, executionGroup);
        }

        return groups;
    }

    public boolean isExecutionGroupExists(String group){
        return getExecutionGroups().containsKey(group);
    }

    public ExecutionGroup getDefaultExecutionGroup() {
        return defaultExecutionGroup;
    }

    public HashMap<String, ExecutionGroup> getExecutionGroups() {
        return executionGroups;
    }

    public HashSet<ExecutionPattern> getPatternTypes() {
        return patternTypes;
    }

    public int getPatternSize() {
        return patternSize;
    }


}
