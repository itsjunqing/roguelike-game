package game;

import edu.monash.fit2099.engine.*;

public class GenerateOxygenTankAction extends Action {

    private OxygenDispenser dispenser;
    private Location dispenserLocation;

    public GenerateOxygenTankAction(OxygenDispenser dispenser, Location dispenserLocation) {
        this.dispenser = dispenser;
        this.dispenserLocation = dispenserLocation;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (dispenser.getCount() != 0) {
            return "Oxygen tank is still in production";
        }
        for (Item item : dispenserLocation.getItems()) {
            if (item instanceof OxygenTank) {
                return "Oxygen tank is already produced";
            }
        }
        dispenser.setCount(1);
        return "Producing oxygen tank";
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
