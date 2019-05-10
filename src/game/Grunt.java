package game;

import edu.monash.fit2099.engine.*;

/**
 * Class representing a Grunt.
 */
public class Grunt extends Enemy {

    public static final char GRUNT_CHAR = 'g';
    /**
     * Constructor to create an Enemy of type Grunt with a name.
     * By default, it has an ability to follow a player.
     *
     * @param name   the name of the Grunt
     */
    public Grunt(String name) {
        super(name, GRUNT_CHAR, 5, 5);
        for (Actor player : players) {
            addBehaviour(new FollowBehaviour(player));
        }
    }

    /**
     * Allows the Grunt to move towards the player if FollowBehaviour is active.
     * Returns a list of actions if FollowBehaviour is inactive.
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
