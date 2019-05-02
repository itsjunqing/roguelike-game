package game;

import edu.monash.fit2099.engine.*;

/**
 * An Action that builds a Rocket.
 */
public class BuildRocketAction extends Action {

    private RocketBody rocketBody;
    private RocketEngine rocketEngine;
    private Location rocketLocation;

    /**
     * Constructor to create an Action that will build a rocket on a Location
     * @param rocketBody a rocket body
     * @param rocketEngine a rocket engine
     * @param rocketLocation location to build the rocket
     */
    public BuildRocketAction(RocketBody rocketBody, RocketEngine rocketEngine, Location rocketLocation) {
        this.rocketBody = rocketBody;
        this.rocketEngine = rocketEngine;
        this.rocketLocation = rocketLocation;
    }

    /**
     * Builds a rocket on the rocket location.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a suitable description to display in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        rocketLocation.removeItem(rocketBody);
        rocketLocation.removeItem(rocketEngine);
        rocketLocation.addItem(new Rocket("Falcon Wings"));
        return menuDescription(actor);
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     *
     * @param actor The actor performing the action.
     * @return a string, e.g. "Player builds a rocket"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " builds a rocket";
    }

    /**
     * Returns an empty string, as rocket building does not have a dedicated hotkey.
     *
     * @return the empty string
     */
    @Override
    public String hotKey() {
        return "";
    }
}
