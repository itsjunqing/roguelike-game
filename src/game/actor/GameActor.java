package game.actor;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.action.GameAttackAction;
import game.behaviour.ActionFactory;
import game.behaviour.ActorBehaviours;

import java.util.ArrayList;
import java.util.List;

public abstract class GameActor extends Actor implements ActorBehaviours {

    private List<ActionFactory> actionFactories = new ArrayList<>();

    public GameActor(String name, char displayChar, int priority, int hitPoints) {
        super(name, displayChar, priority, hitPoints);
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        return new Actions(new GameAttackAction(otherActor, this));
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
