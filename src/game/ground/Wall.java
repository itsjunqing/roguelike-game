package game.ground;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

/**
 * Class representing a Wall.
 */
public class Wall extends Ground {

    /**
     * Constructor to create a Wall.
     */
    public Wall() {
        super('#');
    }

    /**
     * Stops the actor from passing through the Wall.
     *
     * @param actor actor approaching the Wall
     * @return false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * Blocks any object thrown over the Wall.
     *
     * @return true
     */
    @Override
    public boolean blocksThrownObjects() {
        return true;
    }
}
