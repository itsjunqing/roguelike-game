package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.actor.OxygenDispenser;
import game.item.OxygenTank;

/**
 * An Action to simulate pressing a button on the OxygenDispenser
 */
public class PushDispenserButtonAction extends Action {

    private OxygenDispenser oxygenDispenser;

    /**
     * Constructor to interact with the OxygenDispenser.
     *
     * @param oxygenDispenser an OxygenDispenser for the player to interact with.
     */
    public PushDispenserButtonAction(OxygenDispenser oxygenDispenser) {
        this.oxygenDispenser = oxygenDispenser;
    }

    /**
     * Sets a countdown for the OxygenDispenser to create an OxygenTank at its location if there isn't a OxygenTank
     * at the OxygenDispenser's location. If the OxygenDisoenser has already been interacted, returns a message stating
     * that the OxygenTank is in process. If the OxygenTank already exists, returns a message stating that there is
     * already an OxygenTank.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String stating the OxygenDispenser's status
     */
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

    /**
     * A String suitable to display about interacting with the OxygenDispenser in the UI.
     *
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " pushes the Oxygen Dispenser button";
    }

    /**
     * Returns an empty string, as interacting with the OxygenDispenser does not have a dedicated hotkey.
     *
     * @return an empty string
     */
    @Override
    public String hotKey() {
        return "";
    }
}
