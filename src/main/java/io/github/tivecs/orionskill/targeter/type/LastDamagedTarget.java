package io.github.tivecs.orionskill.targeter.type;

import io.github.tivecs.orionskill.targeter.DynamicTargetData;
import io.github.tivecs.orionskill.targeter.DynamicTargetObject;
import org.bukkit.entity.LivingEntity;

import javax.annotation.Nonnull;

public class LastDamagedTarget extends DynamicTargetObject {

    public LastDamagedTarget(@Nonnull DynamicTargetData targetData, int skillSlot) {
        super(targetData, skillSlot, "LastDamaged");
    }

    @Override
    public void searchTarget(Object[] args) {
        if (args[0] instanceof LivingEntity){
            setTarget(args[0]);
        }
    }
}
