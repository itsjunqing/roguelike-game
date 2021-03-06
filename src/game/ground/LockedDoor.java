package game.ground;

import edu.monash.fit2099.engine.*;
import game.GameWorld;
import game.action.UnlockDoorAction;

import java.util.ArrayList;

/**
 * Class representing a LockedDoor that allow an Actor access and can only be unlocked with a Key.
 */
public class LockedDoor extends Ground {

    private static ArrayList<Item> keys = new ArrayList<>();

    /**
     * Constructor to create a LockedDoor.
     */
    public LockedDoor() {
        super('+');
    }

    /**
     * Prevents actors from entering the LockedDoor.
     *
     * @param actor actor approaching the door
     * @return false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * Allows actor to unlock the door if it detects the actor has a key in its inventory
     * and the actor is a player.
     *
     * @param actor     the Actor beside the LockedDoor
     * @param location  the Location beside the LockedDoor
     * @param direction the direction the Actor is beside the LockedDoor
     * @return a collection of Action which the actor adjacent to it can perform
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = super.allowableActions(actor, location, direction);
        if (actor.equals(GameWorld.getGamePlayer())) {
            for (Item item : actor.getInventory()) {
                if (keys.contains(item)) {
                    actions.add(new UnlockDoorAction(direction, location, item));
                }
            }
        }
        return actions;
    }

    /**
     * Prevents objects to be thrown over the door.
     *
     * @return true
     */
    @Override
    public boolean blocksThrownObjects() {
        return true;
    }

    /**
     * Adds a key to a list of keys to ensure that the Door recognises the Key
     *
     * @param key an Item representing a key that unlocks a Door
     */
    public static void addKey(Item key) {
        keys.add(key);
    }

    /**
     * Removes a key from a list of keysto signify that the key has been used
     *
     * @param key an Item representing a key that unlocks a Door
     */
    public static void removeKey(Item key) {
        keys.remove(key);
    }
}