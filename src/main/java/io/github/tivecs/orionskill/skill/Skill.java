package io.github.tivecs.orionskill.skill;

import io.github.tivecs.orionskill.OrionSkill;
import io.github.tivecs.orionskill.ability.Ability;
import io.github.tivecs.orionskill.config.GlobalConfig;
import io.github.tivecs.storage.StorageAbstract;
import io.github.tivecs.storage.StorageYML;
import org.bukkit.configuration.ConfigurationSection;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Skill {

    private final static OrionSkill plugin = OrionSkill.getPlugin(OrionSkill.class);
    private final static File folder = new File(plugin.getDataFolder(), "skills");

    private SkillManager manager;
    private StorageAbstract storage;
    private GlobalConfig.StorageType storageType;

    private String id, displayName;
    private int maxLevel;
    private double attributeMultiplier;
    private HashSet<String> whitelistedGroups, blacklistedGroups;

    private HashMap<Integer, List<Ability>> abilities;
    private HashMap<Integer, Double> cooldowns;

    public Skill(SkillManager manager, String id, GlobalConfig.StorageType type){
        this.manager = manager;
        this.id = id;
        this.storageType = type;

        readSkillData();

        System.out.println(this.toString());
    }

    public void readSkillData(){
        if (getStorageType().equals(GlobalConfig.StorageType.YML)){
            this.storage = new StorageYML(new File(folder, id + ".yml"));
            getStorage().read();
        }

        HashMap<String, Object> data = getStorage().getData();

        this.displayName = (String) data.getOrDefault("display-name", id);
        this.maxLevel = (int) data.getOrDefault("max-level", 1);
        this.attributeMultiplier = (double) data.getOrDefault("attribute-multiplier", 1.0);

        this.whitelistedGroups = readListedGroups(data, true);
        this.blacklistedGroups = readListedGroups(data, false);

        this.cooldowns = readCooldownData((ConfigurationSection) data.getOrDefault("cooldowns", null));
        this.abilities = readAbilitiesData((ConfigurationSection) data.getOrDefault("abilities", null));
    }

    @SuppressWarnings("unchecked")
    private HashSet<String> readListedGroups(HashMap<String, Object> data, boolean isWhitelist){
        List<String> list = (List<String>) data.getOrDefault((isWhitelist ? "whitelisted-groups" : "blacklisted-groups"), new ArrayList<>());

        return new HashSet<>(list);
    }

    private HashMap<Integer, Double> readCooldownData(ConfigurationSection section){
        HashMap<Integer, Double> map = new HashMap<>();
        if (section != null){
            for (String keyPath : section.getKeys(false)){
                int level = Integer.parseInt(keyPath);
                double cooldown = section.getDouble(keyPath);

                map.put(level, cooldown);
            }
        }

        return map;
    }

    private HashMap<Integer, List<Ability>> readAbilitiesData(ConfigurationSection section){
        HashMap<Integer, List<Ability>> map = new HashMap<>();

        return map;
    }

    public StorageAbstract getStorage() {
        return storage;
    }

    public GlobalConfig.StorageType getStorageType() {
        return storageType;
    }

    public SkillManager getManager() {
        return manager;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public HashMap<Integer, Double> getCooldowns() {
        return cooldowns;
    }

    public double getAttributeMultiplier() {
        return attributeMultiplier;
    }

    public HashSet<String> getWhitelistedGroups() {
        return whitelistedGroups;
    }

    public HashSet<String> getBlacklistedGroups() {
        return blacklistedGroups;
    }

    public HashMap<Integer, List<Ability>> getAbilities() {
        return abilities;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "\n storageType=" + storageType +
                ",\n id='" + id + '\'' +
                ",\n displayName='" + displayName + '\'' +
                ",\n maxLevel=" + maxLevel +
                ",\n attributeMultiplier=" + attributeMultiplier +
                ",\n whitelistedGroups=" + whitelistedGroups +
                ",\n blacklistedGroups=" + blacklistedGroups +
                ",\n abilities=" + abilities +
                ",\n cooldowns=" + cooldowns +
                "\n'}'";
    }
}
