package game;

import edu.monash.fit2099.engine.Item;

/**
 * Class representing a RocketPlan that can be exchanged for a RocketBody.
 */
public class RocketPlans extends Item {

    public static final char ROCKET_PLANS_CHAR = '~';

    /**
     * Constructor to create rocket plans with a name.
     *
     * @param name name of the rocket plans
     */
    public RocketPlans(String name) {
        super(name, ROCKET_PLANS_CHAR);
    }
}
