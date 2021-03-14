package io.github.tivecs.orionskill.targeter;

import io.github.tivecs.orionskill.player.PlayerTargetData;

import java.util.HashMap;

public class DynamicTargetData {

    private PlayerTargetData playerTargetData;

    // !!!!! CHOOSE BETWEEN THESE TWO TYPE !!!!!
    // ABOVE SELECTION, THERE ARE YOUR THINKING ABOUT BOTH SELECTION
    /*
    * - Simple and Easy to call
    * - The type is too ambiguous
    * - For complex targeting, cannot use attribute or customization
    *
    * */
    //private HashMap<String, Object> targets; // SkillSlot.Target, Value

    /*
    * - Complex and Hard to call
    * - The type can more variety
    * - Can use attribute or customization, e.g NearbyEntities(type=LivingEntity, x=3, y=1, z=3)
    *
    * */
    private HashMap<DynamicTargetObject, Object> targets; // (SkillSlot, getTargetMethod, Attribute, etc), Value
    private HashMap<String, DynamicTargetObject> targetObjects; // get target object by id

    public DynamicTargetData(PlayerTargetData playerTargetData){
        this.playerTargetData = playerTargetData;
        this.targets = new HashMap<>();
        this.targetObjects = new HashMap<>();
    }

    public HashMap<DynamicTargetObject, Object> getTargets() {
        return targets;
    }

    public HashMap<String, DynamicTargetObject> getTargetObjects() {
        return targetObjects;
    }

    public PlayerTargetData getPlayerTargetData() {
        return playerTargetData;
    }
}
