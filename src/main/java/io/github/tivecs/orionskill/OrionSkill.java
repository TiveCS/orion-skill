package io.github.tivecs.orionskill;

import io.github.tivecs.orionskill.config.GlobalConfig;
import io.github.tivecs.orionskill.config.SkillExecutionConfig;
import io.github.tivecs.orionskill.listener.BasicEventListener;
import io.github.tivecs.orionskill.listener.SkillExecutionEventListener;
import io.github.tivecs.orionskill.player.PlayerManager;
import io.github.tivecs.orionskill.skill.SkillManager;
import org.bukkit.plugin.java.JavaPlugin;

public class OrionSkill extends JavaPlugin {

    private SkillExecutionConfig skillExecutionConfig;
    private GlobalConfig globalConfig;

    private PlayerManager playerManager;
    private SkillManager skillManager;

    @Override
    public void onEnable() {
        loadConfig();
        registerEventListener();

        loadManager();
    }

    public void loadManager(){
        this.playerManager = new PlayerManager();
        this.skillManager = new SkillManager();
    }

    private void loadConfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();

        this.skillExecutionConfig = new SkillExecutionConfig();
        this.globalConfig = new GlobalConfig();
    }

    private void registerEventListener(){
        getServer().getPluginManager().registerEvents(new BasicEventListener(), this);
        getServer().getPluginManager().registerEvents(new SkillExecutionEventListener(), this);
    }

    public SkillExecutionConfig getSkillExecutionConfig() {
        return skillExecutionConfig;
    }

    public GlobalConfig getGlobalConfig() {
        return globalConfig;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public SkillManager getSkillManager() {
        return skillManager;
    }
}
