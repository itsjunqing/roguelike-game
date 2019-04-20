package game;

import edu.monash.fit2099.engine.*;

public class BuildRocketAction extends Action {

    private RocketBody rocketBody;
    private RocketEngine rocketEngine;
    private Location rocketLocation;

    public BuildRocketAction(RocketBody rocketBody, RocketEngine rocketEngine, Location rocketLocation) {
        this.rocketBody = rocketBody;
        this.rocketEngine = rocketEngine;
        this.rocketLocation = rocketLocation;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        rocketLocation.removeItem(rocketBody);
        rocketLocation.removeItem(rocketEngine);
        rocketLocation.addItem(new Rocket("Falcon Wings"));
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " builds a rocket";
    }

    @Override
    public String hotKey() {
        return "";
    }
}
