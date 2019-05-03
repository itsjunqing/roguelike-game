package game;

import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;

/**
 * Class representing a RocketEngine that is required to build a Rocket.
 */
public class RocketEngine extends Item {

    /**
     * Constructor to create a rocket engine with a name.
     * This RocketBody is by default in the actor's inventory (e.g. DoctorMaybe), so the item can only have DropItemAction.
     *
     * @param name name of the rocket engine
     */
    public RocketEngine(String name) {
        super(name, ']');
        allowableActions.clear();
        allowableActions.add(new DropItemAction(this));
    }
}
