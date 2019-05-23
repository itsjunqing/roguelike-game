package game.item;

import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;
import game.Q;
import game.ground.RocketPad;

/**
 * Class representing a RocketBody required to build a Rocket.
 */
public class RocketBody extends Item {

    public static final char ROCKET_BODY_CHAR = '[';

    /**
     * Constructor to create a rocket body with a name.
     * This RocketBody is by default in the actor's inventory (e.g. Q), so it has the ability of dropping.
     *
     * @param name name of the rocket body
     */
    public RocketBody(String name) {
        super(name, ROCKET_BODY_CHAR);
        allowableActions.clear();
        allowableActions.add(new DropItemAction(this));
        RocketPad.addRocketBody(this);
        Q.addRocketBody(this);
    }

}
