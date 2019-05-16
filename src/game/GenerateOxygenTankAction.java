package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class GenerateOxygenTankAction extends Action {

    private Location tankLocation;

    public GenerateOxygenTankAction(Location tankLocation) {
        this.tankLocation = tankLocation;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " generates an oxygen tank";
    }

    @Override
    public String hotKey() {
        return "";
    }
}
