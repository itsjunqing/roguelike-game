package game;

import edu.monash.fit2099.engine.*;

public class OxygenDispenser extends Ground {

    private boolean generated = false;
    public OxygenDispenser() {
        super('D');
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = super.allowableActions(actor, location, direction);
        if (actor instanceof Player) {
            actions.add(new GenerateOxygenTankAction(location));
        }
        return actions;
    }

    public void setGenerated(boolean generated) {
        this.generated = generated;
    }
}
