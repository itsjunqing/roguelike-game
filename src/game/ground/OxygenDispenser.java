package game.ground;

import edu.monash.fit2099.engine.*;
import game.action.GenerateOxygenTankAction;

public class OxygenDispenser extends Ground {

    public OxygenDispenser() {
        super('$');
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = super.allowableActions(actor, location, direction);
        if (actor instanceof Player) {
            actions.add(new GenerateOxygenTankAction(location));
        }
        return actions;
    }
}
