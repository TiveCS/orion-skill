package io.github.tivecs.orionskill.event;

import io.github.tivecs.orionskill.execution.ExecutionPattern;
import io.github.tivecs.orionskill.player.PlayerData;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class PlayerSkillExecutionEvent extends Event implements Cancellable {

    public final static HandlerList HANDLER_LIST = new HandlerList();

    private PlayerData playerData;
    private UUID uniqueId;

    private boolean isCancelled = false;

    public PlayerSkillExecutionEvent(PlayerData playerData){
        this.playerData = playerData;
        this.uniqueId = playerData.getUniqueId();
    }

    public PlayerData getPlayerData() {
        return playerData;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }


    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        isCancelled = cancel;
    }
}
