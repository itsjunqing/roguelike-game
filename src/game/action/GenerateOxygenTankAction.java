package game.action;

import edu.monash.fit2099.engine.*;
import game.item.OxygenTank;

/**
 * An Action that creates an OxygenTank.
 */
public class GenerateOxygenTankAction extends Action {

    private Location dispenserLocation;

    /**
     * Constructor to create an Action that signifies creating an OxygenTank.
     *
     * @param dispenserLocation the Location to create an OxygenTank
     */
    public GenerateOxygenTankAction(Location dispenserLocation) {
        this.dispenserLocation = dispenserLocation;
    }

    /**
     * Generates an OxygenTank on the dispenserLocation if it does not consists of any OxygenTank.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a suitable description to display in the UI
     */
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

    /**
     * A string describing the action suitable for displaying in the UI menu.
     *
     * @param actor The actor performing the action.
     * @return a string, e.g. "Player pushes the dispenser button"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " pushes the dispenser button";
    }

    /**
     * Returns an empty string, as generating an OxygenTank does not have a dedicated hotkey.
     *
     * @return an empty string
     */
    @Override
    public String hotKey() {
        return "";
    }
}
