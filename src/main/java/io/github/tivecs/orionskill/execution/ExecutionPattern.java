package io.github.tivecs.orionskill.execution;

import org.bukkit.event.block.Action;

public enum ExecutionPattern {
    LEFT_CLICK, RIGHT_CLICK, MIDDLE_CLICK;

    public static ExecutionPattern fromAction(Action action){
        if (action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)){
            return LEFT_CLICK;
        }

        if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)){
            return RIGHT_CLICK;
        }
        return null;
    }

    public static boolean isMatch(Action action){
        return fromAction(action) != null;
    }
}
