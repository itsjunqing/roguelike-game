package game;

import edu.monash.fit2099.engine.Actor;

import java.util.ArrayList;
import java.util.List;

public class Goon extends Actor {

    private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

    public Goon(String name, Actor player) {
        super(name, 'o', 10, 100);
        addBehaviours(new FollowBehaviour(player));
    }

    public void addBehaviours(ActionFactory behaviour) {
        actionFactories.add(behaviour);
    }


}
