package io.github.tivecs.orionskill.targeter;

import javax.annotation.Nonnull;
import java.util.HashMap;

public abstract class DynamicTargetObject {

    private String id; // SkillSlot as Id for target object
    private int skillSlot;
    private String targetType;

    private HashMap<String, Object> attributes;
    private Object target;

    public DynamicTargetObject(@Nonnull DynamicTargetData targetData, int skillSlot, @Nonnull String targetType){
        this.skillSlot = skillSlot;
        this.attributes = new HashMap<>();
        this.id = skillSlot + "." + targetType;
    }

    public abstract void searchTarget(Object[] args);

    public Object getTarget() {
        return target;
    }

    public String getTargetType() {
        return targetType;
    }

    public String getId() {
        return id;
    }

    public int getSkillSlot() {
        return skillSlot;
    }

    public HashMap<String, Object> getAttributes() {
        return attributes;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public void setAttributes(HashMap<String, Object> attributes) {
        this.attributes = attributes;
    }
}
