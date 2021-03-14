package io.github.tivecs.orionskill.player;

import io.github.tivecs.orionskill.OrionSkill;
import io.github.tivecs.storage.StorageAbstract;
import io.github.tivecs.storage.StorageYML;

import java.io.File;
import java.util.UUID;

public class PlayerData {

    private final static OrionSkill plugin = OrionSkill.getPlugin(OrionSkill.class);

    private UUID uniqueId;

    private StorageAbstract storage;
    private File ymlStorageFile;

    private PlayerSkillExecutionData executionData;

    public PlayerData(UUID uniqueId){
        this.uniqueId = uniqueId;

        this.ymlStorageFile = new File(plugin.getDataFolder() + "/player-data", uniqueId.toString() + ".yml");
        this.storage = new StorageYML(this.ymlStorageFile);

        this.executionData = new PlayerSkillExecutionData(this);

        initStorageData();
        readSkillInventory();
    }

    public void initStorageData(){
        if (getStorage() instanceof StorageYML){
            StorageYML st = (StorageYML) getStorage();
            st.read();

            st.writeSection("skill-inventory");

            st.save();
        }
    }

    public void readSkillInventory(){

    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public StorageAbstract getStorage() {
        return storage;
    }

    public File getYmlStorageFile() {
        return ymlStorageFile;
    }

    public PlayerSkillExecutionData getExecutionData() {
        return executionData;
    }
}
