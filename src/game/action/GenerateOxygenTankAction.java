package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.actor.GamePlayer;
import game.item.OxygenTank;

/**
 * An Action that creates a new OxygenTank on the location.
 */
public class GenerateOxygenTankAction extends Action {

    private Location tankLocation;
    private GamePlayer player;

    /**
     * Constructor to create a new OxygenTank on the location provided.
     *
     * @param tankLocation the Location to create a new OxygenTank
     * @param player the Actor that the OxygenTank recognises.
     */
    public GenerateOxygenTankAction(Location tankLocation, GamePlayer player) {
        this.tankLocation = tankLocation;
        this.player = player;
    }

    /**
     * Creates a new OxygenTank in the location provided that recognises the Player.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a suitable String to describe the activity in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        tankLocation.addItem(new OxygenTank("Oxygen tank", player));
        return menuDescription(actor);
    }

    /**
     * A string describing the action suitable for displaying in the UI
     *
     * @param actor The actor performing the action.
     * @return a string
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " generates an oxygen tank";
    }

    /**
     * Returns an empty string, as generating an OxygenTank does not have a dedicated hotkey.
     *
     * @return the empty string
     */
    @Override
    public String hotKey() {
        return "";
    }
}
