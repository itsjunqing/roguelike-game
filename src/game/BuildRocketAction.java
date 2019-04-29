package game;

import edu.monash.fit2099.engine.*;

/**
 * Special action used by the player to build thee Rocket.
 */
public class BuildRocketAction extends Action {

    private RocketBody rocketBody;
    private RocketEngine rocketEngine;
    private Location rocketLocation;

    /**
     * Constructor to create an Action that would build a Rcoket on the Location.
     * @param rocketBody    the rocket body that is required to build the rocket
     * @param rocketEngine  the rocket engine that is required to build the rocket
     * @param rocketLocation    the Location that the Rocket would be built
     */
    public BuildRocketAction(RocketBody rocketBody, RocketEngine rocketEngine, Location rocketLocation) {
        this.rocketBody = rocketBody;
        this.rocketEngine = rocketEngine;
        this.rocketLocation = rocketLocation;
    }

    /**
     * Allows the player to build the rocket
     *
     * Overrider Action.execute()
     *
     * @see Action#execute(Actor, GameMap)
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return  a description of the Action suitable for the menu
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        rocketLocation.removeItem(rocketBody);
        rocketLocation.removeItem(rocketEngine);
        rocketLocation.addItem(new Rocket("Falcon Wings"));
        return menuDescription(actor);
    }

    /**
     * Returns a description of building the rocket action that is suitable to display
     * in the menu
     *
     * @see Action#menuDescription(Actor)
     * @param actor The actor performing the action.
     * @return  a String describing the Action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " builds a rocket";
    }

    /**
     * Returns the empty String as no hotkey is permanently assigned to building the rocket
     * @return  an empty String
     */
    @Override
    public String hotKey() {
        return "";
    }
}
