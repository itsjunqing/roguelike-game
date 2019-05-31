package game.item;

import edu.monash.fit2099.engine.Item;
import game.actor.Q;

/**
 * Class representing a RocketPlans that can be exchanged for a RocketBody.
 */
public class RocketPlans extends Item {

    public static final char ROCKET_PLANS_CHAR = '~';

    /**
     * Constructor to create RocketPlans.
     *
     * @param name name of the RocketPlans
     */
    public RocketPlans(String name) {
        super(name, ROCKET_PLANS_CHAR);
        Q.addRocketPlans(this);
    }
}
