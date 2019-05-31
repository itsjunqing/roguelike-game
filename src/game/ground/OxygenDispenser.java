package game.ground;

import edu.monash.fit2099.engine.*;
import game.GameWorld;
import game.action.GenerateOxygenTankAction;

/**
 * Class representing an OxygenDispenser that dispenses an OxygenTank when requested by an Actor.
 */
public class OxygenDispenser extends Ground {

    /**
     * Constructor to create an OxygenDispenser.
     */
    public OxygenDispenser() {
        super('$');
    }

    /**
     * Allows the actor to generate an OxygenTank if the actor is a player.
     *
     * @param actor the Actor beside the OxygenDispenser
     * @param location the Location beside the OxygenDispenser
     * @param direction the direction of the Ground from the Actor
     * @return a collection of Action which the actor adjacent to it can perform
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = super.allowableActions(actor, location, direction);
        if (actor.equals(GameWorld.getGamePlayer())) {
            actions.add(new GenerateOxygenTankAction(location));
        }
        return actions;
    }
}
