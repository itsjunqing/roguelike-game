package game;

import edu.monash.fit2099.engine.*;

public class RocketPad extends Ground {

    public RocketPad() {
        super('=');
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = super.allowableActions(actor, location, direction);

        RocketBody rocketBody = null;
        RocketEngine rocketEngine = null;

        for (Item item : location.getItems()) {
            if (item instanceof RocketBody) {
                rocketBody = (RocketBody) item;
            } else if (item instanceof RocketEngine) {
                rocketEngine = (RocketEngine) item;
            }
        }

        if ((rocketEngine != null) && (rocketBody != null)) {
            actions.add(new BuildRocketAction(rocketBody, rocketEngine, location));
            return actions;
        }

        return actions;
    }
}
