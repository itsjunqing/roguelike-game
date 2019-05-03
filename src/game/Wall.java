package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

/**
 * Class representing a Wall that does not allow an Actor access.
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
     * Blocks any object thrown over the wall.
     *
     * @return true
     */
    @Override
    public boolean blocksThrownObjects() {
        return true;
    }
}
