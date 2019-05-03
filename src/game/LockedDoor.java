package game;

import edu.monash.fit2099.engine.*;

/**
 * Class representing a LockedDoor that allow an Actor access and can only be unlocked with a Key.
 */
public class LockedDoor extends Ground {

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

        for (Item item : actor.getInventory()) {
            if (item instanceof Key) {
                actions.add(new UnlockDoorAction(direction, location, (Key) item));
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
}