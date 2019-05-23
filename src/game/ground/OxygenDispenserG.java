package game.ground;

import edu.monash.fit2099.engine.*;

public class OxygenDispenserG extends Ground {

    public OxygenDispenserG() {
        super('$');
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = super.allowableActions(actor, location, direction);

//        if (!hasOxygenTank(location)) {
//            if (actor instanceof Player) {
//                actions.add(new PushDispenserGAction(location));
//            }
//        }
        if (actor instanceof Player) {
            actions.add(new PushDispenserGAction(location));
        }



        return actions;
    }

//    public boolean hasOxygenTank(Location location) {
//
//        for (Item item : location.getItems()) {
//            if (item instanceof OxygenTank) {
//                return true;
//            }
//        }
//        return false;
//    }
}
