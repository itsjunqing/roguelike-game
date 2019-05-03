package game;

import edu.monash.fit2099.engine.Item;

/**
 * Class representing a Rocket.
 */
public class Rocket extends Item {

    /**
     * Constructor that creates a rocket with a name.
     * This Rocket is a static item so it does not have any allowableActions.
     *
     * @param name the name of the rocket
     */
    public Rocket(String name) {
        super(name, '^');
        allowableActions.clear();
    }
}
