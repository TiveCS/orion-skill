package io.github.tivecs.orionskill.listener;

import io.github.tivecs.orionskill.OrionSkill;
import io.github.tivecs.orionskill.config.SkillExecutionConfig;
import io.github.tivecs.orionskill.event.PlayerCastingSkillExecutionEvent;
import io.github.tivecs.orionskill.event.PlayerSkillExecutionEvent;
import io.github.tivecs.orionskill.execution.ExecutionPattern;
import io.github.tivecs.orionskill.player.PlayerData;
import io.github.tivecs.orionskill.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashSet;

public class SkillExecutionEventListener implements Listener {

    private final static OrionSkill plugin = OrionSkill.getPlugin(OrionSkill.class);

    private SkillExecutionConfig config;
    private PlayerManager playerManager;

    private HashSet<ExecutionPattern> availablePatterns;

    public SkillExecutionEventListener(){
        initField();
    }

    public void initField(){
        this.config = plugin.getSkillExecutionConfig();
        this.availablePatterns = config.getPatternTypes();
        this.playerManager = plugin.getPlayerManager();
    }

    @EventHandler
    public void onPlayerClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Material mainHandItem = player.getInventory().getItemInMainHand().getType();
        Action action = event.getAction();
        HashSet<Material> mats = config.getDefaultExecutionGroup().getExecutionItemMaterial();

        ExecutionPattern pattern = ExecutionPattern.fromAction(action);
        if (pattern != null && mats.contains(mainHandItem)){
            PlayerData pd = playerManager.getPlayerData(player.getUniqueId());
            PlayerCastingSkillExecutionEvent castingEvent = new PlayerCastingSkillExecutionEvent(pd, pattern);

            Bukkit.getPluginManager().callEvent(castingEvent);
            if (!castingEvent.isCancelled()) {
                pd.getExecutionData().addPattern(pattern);
            }
        }
    }

    @EventHandler
    public void onSkillExecution(PlayerSkillExecutionEvent event){



    }

}
