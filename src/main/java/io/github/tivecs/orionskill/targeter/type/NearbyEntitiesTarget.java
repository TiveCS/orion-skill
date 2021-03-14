package io.github.tivecs.orionskill.targeter.type;

import io.github.tivecs.orionskill.targeter.DynamicTargetData;
import io.github.tivecs.orionskill.targeter.DynamicTargetObject;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.Collection;

public class NearbyEntitiesTarget extends DynamicTargetObject {

    public NearbyEntitiesTarget(DynamicTargetData targetData, int skillSlot) {
        super(targetData, skillSlot, "NearbyEntities");
    }

    @Override
    public void searchTarget(Object[] args) {
        double x,y,z;
        x = (double) getAttributes().get("x");
        y = (double) getAttributes().get("y");
        z = (double) getAttributes().get("z");

        if (args != null){
            if (args[0] instanceof Location){
                Location loc = (Location) args[0];
                Collection<Entity> entities = loc.getWorld().getNearbyEntities(loc, x, y, z);

                setTarget(entities);
            }
        }
    }
}
