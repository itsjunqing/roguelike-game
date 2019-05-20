package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class PushDispenserButtonAction extends Action {

    private OxygenDispenser oxygenDispenser;

    public PushDispenserButtonAction(OxygenDispenser oxygenDispenser) {
        this.oxygenDispenser = oxygenDispenser;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (oxygenDispenser.getDispenserCount() != 0) {
            return "Oxygen tank is still in production";
        }

        if (oxygenDispenser.getTankLocation() != null) {
            for (Item item : oxygenDispenser.getTankLocation().getItems()) {
                if (item instanceof OxygenTank) {
                    return "Oxygen tank is already produced";
                }
            }
        }
        oxygenDispenser.setDispenserCount(2);
        oxygenDispenser.setTankLocation(map.locationOf(actor));
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " pushes the Oxygen Dispenser button";
    }

    @Override
    public String hotKey() {
        return "";
    }
}
