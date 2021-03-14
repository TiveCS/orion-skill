package io.github.tivecs.orionskill.execution;

import io.github.tivecs.utils.XMaterial;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashSet;

public class ExecutionGroup {

    private String name;
    private ConfigurationSection section;

    private HashSet<Material> executionItemMaterial;

    public ExecutionGroup(String groupName, ConfigurationSection section){
        this.name = groupName;
        this.section = section;
        this.executionItemMaterial = new HashSet<>();

        loadData();
    }

    public void loadData(){
        for (String mat : section.getStringList("execution-items")){
            XMaterial xmat = XMaterial.matchXMaterial(mat).get();
            if (xmat != null) {
                getExecutionItemMaterial().add(xmat.parseMaterial());
            }
        }
    }

    public String getName() {
        return name;
    }

    public ConfigurationSection getSection() {
        return section;
    }

    public HashSet<Material> getExecutionItemMaterial() {
        return executionItemMaterial;
    }
}
