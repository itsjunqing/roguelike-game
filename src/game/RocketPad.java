package game;

import edu.monash.fit2099.engine.*;

import static game.RocketBody.ROCKET_BODY_CHAR;
import static game.RocketEngine.ROCKET_ENGINE_CHAR;

/**
 * Class representing a RocketPad that the player is able to build a Rocket on.
 */
public class RocketPad extends Ground {

    /**
     * Constructor to create a rocket pad.
     */
    public RocketPad() {
        super('=');
    }

    /**
     * Allows the actor to build a rocket when the RocketPad detects that it has both RocketBody and RocketEngine on it.
     *
     * @param actor     the Actor beside the RocketPad
     * @param location  the Location of the RocketPad
     * @param direction the direction the Actor is beside the RocketPad
     * @return a collection of Action which the actor adjacent to it can perform
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = super.allowableActions(actor, location, direction);

        Item rocketBody = null;
        Item rocketEngine = null;

        for (Item item : location.getItems()) {
            if (item.getDisplayChar() == ROCKET_BODY_CHAR){
                rocketBody = item;
            }
            if (item.getDisplayChar() == ROCKET_ENGINE_CHAR) {
                rocketEngine = item;
            }
        }

        if ((rocketEngine != null) && (rocketBody != null)) {
            actions.add(new BuildRocketAction(rocketBody, rocketEngine, location));
            return actions;
        }
        return actions;
    }
}
