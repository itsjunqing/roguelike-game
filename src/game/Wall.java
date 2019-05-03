package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

/**
 * Class representing a wall that blocks player from going after it.
 */
public class Wall extends Ground {

    /**
     * Constructor to create a wall.
     */
    public Wall() {
        super('#');
    }

    /**
     * Stops the actor from passing through the wall.
     *
     * @param actor actor approaching the door
     * @return false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * Blocks any object thrown towards the wall.
     *
     * @return true
     */
    @Override
    public boolean blocksThrownObjects() {
        return true;
    }
}
