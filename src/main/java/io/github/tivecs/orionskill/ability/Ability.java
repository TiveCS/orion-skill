package io.github.tivecs.orionskill.ability;

import java.util.HashMap;

public abstract class Ability {

    private String id;
    private HashMap<String, Double> defaultAttribute;

    public Ability(String id){
        this.id = id;
        this.defaultAttribute = new HashMap<>();
    }

    public abstract void execute(HashMap<String, Double> attributes);

    public String getId() {
        return id;
    }

    public HashMap<String, Double> getDefaultAttribute() {
        return defaultAttribute;
    }

}
