package game;

import edu.monash.fit2099.engine.*;

public class LockedDoor extends Ground {

    /**
     * Constructor to create a LockedDoor.
     */
    public LockedDoor() {
        super('+');
    }

    /**
     * Stops the actor from entering the LockedDoor.
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
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
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
     * Blocks any object thrown towards the door.
     *
     * @return true
     */
    @Override
    public boolean blocksThrownObjects() {
        return true;
    }
}