package game;

import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;

/**
 * Class representing a RocketBody required to build a Rocket.
 */
public class RocketBody extends Item {

    /**
     * Constructor to create a rocket body with a name.
     * This RocketBody is by default in the actor's inventory (e.g. Q), so the item can only have DropItemAction.
     *
     * @param name name of the rocket body
     */
    public RocketBody(String name) {
        super(name, '[');
        allowableActions.clear();
        allowableActions.add(new DropItemAction(this));
    }

}
