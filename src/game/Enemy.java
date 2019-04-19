package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.IntrinsicWeapon;

import java.util.ArrayList;
import java.util.List;

public abstract class Enemy extends Actor {

    public Enemy(String name, char displayChar, int priority, int hitPoints) {
        super(name, displayChar, priority, hitPoints);
    }

    private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

    protected void addBehaviour(ActionFactory behaviour) {
        actionFactories.add(behaviour);
    }

    protected List<ActionFactory> getActionFactories() {
        return actionFactories;
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(5, "scratches");
    }

    public int getDamage() {
        return getIntrinsicWeapon().damage();
    }
}
