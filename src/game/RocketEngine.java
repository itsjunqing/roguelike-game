package game;

import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;

/**
 * Class representing a RocketEngine that is required to build a Rocket.
 */
public class RocketEngine extends Item {

    public static final char ROCKET_ENGINE_CHAR = ']';

    /**
     * Constructor to create a rocket engine with a name.
     * This RocketBody is by default in the actor's inventory (e.g. DoctorMaybe), so it has the ability of dropping.
     *
     * @param name name of the rocket engine
     */
    public RocketEngine(String name) {
        super(name, ROCKET_ENGINE_CHAR);
        allowableActions.clear();
        allowableActions.add(new DropItemAction(this));
        RocketPad.addRocketEngine(this);
    }
}
