package io.github.tivecs.orionskill.config;

import io.github.tivecs.orionskill.OrionSkill;
import io.github.tivecs.storage.StorageAbstract;
import io.github.tivecs.storage.StorageYML;

import java.io.File;

public class GlobalConfig {

    public enum StorageType{
        YML
    }

    private final static OrionSkill plugin = OrionSkill.getPlugin(OrionSkill.class);
    private final static File configYmlFile = new File(plugin.getDataFolder(), "config.yml");

    private StorageAbstract storage;

    private StorageType globalStorageType;

    public GlobalConfig(){
        this.storage = new StorageYML(configYmlFile);

        readData();
    }

    public void readData(){
        getStorage().read();

        this.globalStorageType = StorageType.valueOf(((String) getStorage().getData().getOrDefault("storage.type", "YML")).toUpperCase());
    }

    public StorageType getGlobalStorageType() {
        return globalStorageType;
    }

    public StorageAbstract getStorage() {
        return storage;
    }
}
