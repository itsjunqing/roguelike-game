package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.GameWorld;
import game.item.OxygenTank;

/**
 * An Action that allows an Actor to pick up an OxygenTank.
 */
public class PickUpOxygenTankAction extends Action {

    private OxygenTank oxygenTank;

    /**
     * Constructor to create an Action that allows Actor to pick up an OxygenTank.
     *
     * @param oxygenTank an OxygenTank to be picked up
     */
    public PickUpOxygenTankAction(OxygenTank oxygenTank) {
        this.oxygenTank = oxygenTank;
    }

    /**
     * Removes the OxygenTank from the map and adds the OxygenTank to the GamePlayer's list of oxygenTanks.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a string stating the OxygenTank is picked up by the GamePlayer
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(oxygenTank);
        oxygenTank.getAllowableActions().remove(this);
        GameWorld.getGamePlayer().addTank(oxygenTank);
        return menuDescription(actor);
    }

    /**
     * A string suitable to display picking up an OxygenTank in the UI.
     *
     * @param actor The actor performing the action.
     * @return a string, e.g. "Player picks up the oxygen tank"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up the " + oxygenTank;
    }

    /**
     * Returns an empty string, as picking up an OxygenTank does not have a dedicated hotkey.
     *
     * @return an empty string
     */
    @Override
    public String hotKey() {
        return "";
    }
}
