package io.github.tivecs.orionskill.event;

import io.github.tivecs.orionskill.execution.ExecutionPattern;
import io.github.tivecs.orionskill.player.PlayerData;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class PlayerCastingSkillExecutionEvent extends Event implements Cancellable {

    private final static HandlerList HANDLER_LIST = new HandlerList();

    private PlayerData playerData;
    private ExecutionPattern lastUsedPattern;
    private UUID uniqueId;

    private boolean isCancelled = false;

    public PlayerCastingSkillExecutionEvent(PlayerData playerData, ExecutionPattern lastUsedPattern){
        this.playerData = playerData;
        this.uniqueId = playerData.getUniqueId();
        this.lastUsedPattern = lastUsedPattern;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public PlayerData getPlayerData() {
        return playerData;
    }

    public ExecutionPattern getLastUsedPattern() {
        return lastUsedPattern;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        isCancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList(){
        return HANDLER_LIST;
    }
}
