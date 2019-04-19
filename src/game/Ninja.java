package game;

import edu.monash.fit2099.engine.Actor;

import java.util.ArrayList;
import java.util.List;

public class Ninja extends Actor {

    public Ninja(String name, int priority, int hitPoints, Actor player) {
        super(name, 'N', 15, hitPoints);
        addBehaviour(new ThrowStunBehaviour(player));
    }

    private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

    private void addBehaviour(ActionFactory behaviour) {
        actionFactories.add(behaviour);
    }


}
