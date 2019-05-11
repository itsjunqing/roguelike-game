package game;

import edu.monash.fit2099.engine.Item;

/**
 * Class representing a Rocket.
 */
public class Rocket extends Item {

    public static final char ROCKET_CHAR = '^';

    /**
     * Constructor that creates a static rocket with a name.
     *
     * @param name the name of the rocket
     */
    public Rocket(String name) {
        super(name, ROCKET_CHAR);
        allowableActions.clear();
    }
}
