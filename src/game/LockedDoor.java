package game;

import edu.monash.fit2099.engine.*;

public class LockedDoor extends Ground {

    public LockedDoor() {
        super('+');
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = super.allowableActions(actor, location, direction);

        for (Item item : actor.getInventory()){
            if (item instanceof Key){
                actions.add(new UnlockDoorAction(direction, location, (Key) item));
            }
        }
        return actions;
    }

    @Override
    public boolean blocksThrownObjects() {
        return true;
    }
}