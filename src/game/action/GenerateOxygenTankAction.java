package game.action;

import edu.monash.fit2099.engine.*;
import game.item.OxygenTank;

public class GenerateOxygenTankAction extends Action {

    private Location dispenserLocation;

    public GenerateOxygenTankAction(Location dispenserLocation) {
        this.dispenserLocation = dispenserLocation;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        for (Item item : dispenserLocation.getItems()) {
            if (item instanceof OxygenTank) {
                return "Oxygen tank is already produced";
            }
        }
        dispenserLocation.addItem(new OxygenTank("Oxygen Tank"));
        return actor + " generates an oxygen tank";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " pushes the dispenser button";
    }

    @Override
    public String hotKey() {
        return "";
    }
}
