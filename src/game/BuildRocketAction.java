package game;

import edu.monash.fit2099.engine.*;

public class BuildRocketAction extends Action {

    private RocketBody rocketBody;
    private RocketEngine rocketEngine;

    public BuildRocketAction() {
    }

    public BuildRocketAction(RocketBody rocketBody) {
        this.rocketBody = rocketBody;
    }

    public BuildRocketAction(RocketEngine rocketEngine) {
        this.rocketEngine = rocketEngine;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.getInventory().contains(rocketBody) && actor.getInventory().contains(rocketEngine)) {
            actor.getInventory().remove(rocketBody);
            actor.getInventory().remove(rocketEngine);
            map.locationOf(actor).addItem(new Rocket("Rocket"));
        }
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " has built a rocket";
    }

    @Override
    public String hotKey() {
        return "";
    }
}
