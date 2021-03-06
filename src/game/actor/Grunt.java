package game.actor;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import game.GameWorld;
import game.behaviour.FollowBehaviour;

/**
 * Class representing a Grunt as a type of an Enemy.
 */
public class Grunt extends Enemy {

    public static final char GRUNT_CHAR = 'g';

    /**
     * Constructor to create an Enemy of type Grunt with a name.
     * It has a behaviour of following the player.
     *
     * @param name the name of the Grunt
     */
    public Grunt(String name) {
        super(name, GRUNT_CHAR, 5, 30);
        addBehaviour(new FollowBehaviour(GameWorld.getGamePlayer()));
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
        Action action = executeBehaviours(map);
        if (action != null) {
            return action;
        }
        super.addActions(actions, this, map);
        return super.playTurn(actions, map, display);
    }
}
