package game.behaviour;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.GameMap;

/**
 * An interface to have behaviours.
 */
public interface ActorBehaviours {

    /**
     * Adds a behaviour into the list of ActionFactory behaviours.
     *
     * @param behaviour the behaviour which the Actor has
     */
    void addBehaviour(ActionFactory behaviour);

    /**
     * Executes the list of behaviours which the Actor has.
     *
     * @param map map which the Actor is on
     * @return the Action to be executed
     */
    Action executeBehaviours(GameMap map);
}
