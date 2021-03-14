package io.github.tivecs.orionskill.skill;

import io.github.tivecs.orionskill.OrionSkill;
import io.github.tivecs.orionskill.config.GlobalConfig;
import io.github.tivecs.storage.StorageAbstract;
import io.github.tivecs.utils.StringUtils;

import java.io.File;
import java.util.HashMap;

public class SkillManager {

    private final static OrionSkill plugin = OrionSkill.getPlugin(OrionSkill.class);
    private final static File folder = new File(plugin.getDataFolder(), "skills");

    private StorageAbstract storage;

    private HashMap<String, Skill> skills = new HashMap<>();

    public SkillManager(){
        if (!folder.exists()){
            folder.mkdir();

            plugin.saveResource("skills/exampleSkill.yml", false);
        }

        readData();
    }

    public void readData(){
        GlobalConfig config = plugin.getGlobalConfig();

        scanSkillData();
    }

    public void scanSkillData(){
        GlobalConfig config = plugin.getGlobalConfig();
        File[] skillsFiles = folder.listFiles();

        skills.clear();

        if (config.getGlobalStorageType().equals(GlobalConfig.StorageType.YML) && skillsFiles != null){
            scanSkillYmlFiles(skillsFiles);
        }

        plugin.getLogger().info(StringUtils.colored("Successfully load " + getSkills().size() + " skill(s)"));
    }

    public void scanSkillYmlFiles(File[] files){
        for (File file : files) {
            String[] name = file.getName().split("[.]");
            if (name[1].endsWith("yml")) {
                getSkills().put(name[0], new Skill(this, name[0], GlobalConfig.StorageType.YML));
            }
        }
    }

    public HashMap<String, Skill> getSkills() {
        return skills;
    }
}
