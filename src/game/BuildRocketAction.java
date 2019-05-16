package game;

import edu.monash.fit2099.engine.*;

/**
 * An Action that builds a Rocket.
 */
public class BuildRocketAction extends Action {

    private Item rocketBody;
    private Item rocketEngine;
    private Location rocketEarthLocation;
    private Location rocketMoonLocation;

    /**
     * Constructor to create an Action that will build a rocket on a Location.
     *
     * @param rocketBody     a rocket body
     * @param rocketEngine   a rocket engine
     * @param rocketEarthLocation location to build the rocket
     */
    public BuildRocketAction(Item rocketBody, Item rocketEngine, Location rocketEarthLocation, Location rocketMoonLocation) {
        this.rocketBody = rocketBody;
        this.rocketEngine = rocketEngine;
        this.rocketEarthLocation = rocketEarthLocation;
        this.rocketMoonLocation = rocketMoonLocation;
    }

    /**
     * Builds a rocket on the rocket location and removes the rocket body and rocket engine from it.
     *
     * @param actor the actor performing the action.
     * @param map   the map the actor is on.
     * @return a suitable description to display in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        rocketEarthLocation.removeItem(rocketBody);
        rocketEarthLocation.removeItem(rocketEngine);
        RocketPad.removeRocketBody(rocketBody);
        RocketPad.removeRocketEngine(rocketEngine);
        Item rocket = new Rocket("Falcon Wings", actor, rocketEarthLocation, rocketMoonLocation);
        rocketEarthLocation.addItem(rocket);
        rocketMoonLocation.addItem(rocket);
        return menuDescription(actor);
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     *
     * @param actor the actor performing the action.
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
