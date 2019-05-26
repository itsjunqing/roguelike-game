package game.actor;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.behaviour.ActionFactory;
import game.behaviour.ActorBehaviours;

public class GameActor extends Actor implements ActorBehaviours {

    public GameActor(String name, char displayChar, int priority, int hitPoints) {
        super(name, displayChar, priority, hitPoints);
    }

    @Override
    public void addBehaviour(ActionFactory behaviour) {
        actionFactories.add(behaviour);
    }

    @Override
    public Action executeBehaviours(GameMap map) {
        for (ActionFactory factory : actionFactories) {
            Action action = factory.getAction(this, map);
            if (action != null)
                return action;
        }
        return null;
    }
}