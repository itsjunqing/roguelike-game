package game;

import edu.monash.fit2099.engine.*;


public class LockedRoom extends Ground {

    public LockedRoom() {
        super('|');
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        return super.allowableActions(actor, location, direction);
    }
}