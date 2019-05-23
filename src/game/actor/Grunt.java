package game.actor;

import edu.monash.fit2099.engine.*;
import game.behaviour.ActionFactory;
import game.behaviour.FollowBehaviour;

/**
 * Class representing a Grunt.
 */
public class Grunt extends Enemy {

    public static final char GRUNT_CHAR = 'g';

    /**
     * Constructor to create an Enemy of type Grunt with a name.
     * It takes in a player of Actor type as target to follow.
     *
     * @param name the name of the Grunt
     */
    public Grunt(String name, Actor player) {
        super(name, GRUNT_CHAR, 5, 5);
        addBehaviour(new FollowBehaviour(player));
    }

    /**
     * Returns an Action to be performed during its turn.
     * It moves itself towards the player if FollowBehaviour is active.
     * Otherwise, it moves randomly in the map (including attack if player is next to it).
     *
     * @param actions collection of possible actions by Grunt in that turn
     * @param map     the map containing the Actor
     * @param display the object that contains the console I/O
     * @return the Action to be performed, e.g. attacking the player when it is next to it
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        actions.clear();
        for (ActionFactory factory : getActionFactories()) {
            Action action = factory.getAction(this, map);
            if (action != null)
                return action;
        }
        super.addActions(actions, this, map);
        return super.playTurn(actions, map, display);
    }
}
