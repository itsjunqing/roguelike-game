package game;

import edu.monash.fit2099.demo.WindowSmashAction;
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
        if (actor.getInventory().isEmpty()){
            return null;
        }
        for (Item item : actor.getInventory()){
            if (item instanceof Key){
                return new Actions(new UnlockDoorAction(direction, location, (Key) item));
            }
        }
        return null;
    }

    @Override
    public boolean blocksThrownObjects() {
        return true;
    }
}