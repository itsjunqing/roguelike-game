package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * An Action that unlocks locked doors and enable Actors to go through them.
 */
public class UnlockDoorAction extends Action {

    private String direction;
    private Location doorLocation;
    private Key key;

    /**
     * Constructor to create an Action that will unlock a LockedDoor on a Location in a given Direction.
     *
     * @param direction    direction to unlock the door
     * @param doorLocation the location of the door
     * @param key          key to unlock the door
     */
    public UnlockDoorAction(String direction, Location doorLocation, Key key) {
        this.direction = direction;
        this.doorLocation = doorLocation;
        this.key = key;
    }

    /**
     * Unlocks the door by removing the door from the location and replace it with a Floor.
     * The key is also removed from the actor's inventory.
     *
     * @param actor the actor performing the action.
     * @param map   the map the actor is on.
     * @return a string statement that tells the door is unlocked
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.add(new Floor(), doorLocation);
        actor.removeItemFromInventory(key);
        return "The door is unlocked";
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     *
     * @param actor The actor performing the action.
     * @return a string, e.g. "Player unlocks the door to the west"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " unlocks the door to the " + direction;
    }

    /**
     * Returns an empty string, as item giving does not have a dedicated hotkey.
     *
     * @return an empty string
     */
    @Override
    public String hotKey() {
        return "";
    }
}
