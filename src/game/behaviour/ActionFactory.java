package game.behaviour;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * An interface to create an Action.
 */
public interface ActionFactory {

    /**
     * Creates a condition for the Action to be executed.
     *
     * @param actor the actor that performs the action
     * @param map   the map which the actor is in it
     * @return the action to perform after passing the conditions
     */
    Action getAction(Actor actor, GameMap map);
}
