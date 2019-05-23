package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.actor.GamePlayer;
import game.item.OxygenTank;

public class GenerateOxygenTankAction extends Action {

    private Location tankLocation;
    private GamePlayer player;

    public GenerateOxygenTankAction(Location tankLocation, GamePlayer player) {
        this.tankLocation = tankLocation;
        this.player = player;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        tankLocation.addItem(new OxygenTank("Oxygen tank", player));
        return menuDescription(actor);
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
