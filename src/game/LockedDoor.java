package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

/**
 * Class representing a LockedDoor that allow an Actor access and can only be unlocked with a Key.
 */
public class LockedDoor extends Ground {

    private static ArrayList<Item> keys = new ArrayList<>();
    private static Actor player;

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
     * Allows actor to unlock the door if it detects the actor has a key in its inventory.
     *
     * @param actor     the Actor beside the LockedDoor
     * @param location  the Location of the LockedDoor
     * @param direction the direction the Actor is beside the LockedDoor
     * @return a collection of Action which the actor adjacent to it can perform
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = super.allowableActions(actor, location, direction);
        // .equals() or ==?
        if (actor == player) {
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

    public static void addKey(Item key) {
        keys.add(key);
    }

    // remove key exists because so that when an instance of the key is used to unlock a door, the key will be
    // removed from the player's inventory, hence this key would no longer exists in the game so for memory efficiency,
    // we remove the key from the class variable of arraylist to tell the lockeddoor that this key is no longer in the game
    public static void removeKey(Item key) {
        keys.remove(key);
    }

    // no need to remove player because multiple locked doors should detect all instance of players in the game
    public static void setPlayer(Actor player) {
        LockedDoor.player = player;
    }
}