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

/**
 * A GameActor base class that generalizes the properties and methods which an actor is capable of doing.
 */
public class GameActor extends Actor implements ActorBehaviours {

    private List<ActionFactory> actionFactories = new ArrayList<>();

    /**
     * Constructor to create a GameActor.
     *
     * @param name        name of the GameActor
     * @param displayChar a Character to identify the GameActor on the GameMap
     * @param priority    an integer that determines when the GameActor is able to take its turn in a round
     * @param hitPoints   an integer that determines the amount of damage it can take before being unconscious
     */
    public GameActor(String name, char displayChar, int priority, int hitPoints) {
        super(name, displayChar, priority, hitPoints);
    }

    /**
     * Returns a collection of the Actions containing an GameAttackAction that the
     * otherActor can do to the current GameActor.
     *
     * @param otherActor the GameActor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        return new Actions(new GameAttackAction(otherActor, this));
    }

    /**
     * Adds a behaviour into the list of behaviours.
     *
     * @param behaviour the behaviour which the Actor has
     */
    @Override
    public void addBehaviour(ActionFactory behaviour) {
        actionFactories.add(behaviour);
    }

    /**
     * Execute the GameActor's behaviour.
     *
     * @param map map which the Actor is on
     * @return the Action to be executed for the behaviour
     */
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
