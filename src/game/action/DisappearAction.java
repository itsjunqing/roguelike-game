package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * An Action that makes an actor disappear from the map.
 */
public class DisappearAction extends Action {

    /**
     * Constructor to create an Action that will make an actor disappear.
     */
    public DisappearAction() {
    }

    /**
     * Makes an Actor disappear by removing the Actor from the map
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a suitable description to display in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return menuDescription(actor);
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     *
     * @param actor The actor performing the action.
     * @return a string
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " disappears into the air";
    }

    /**
     * Returns an empty string, as disappearing an Actor does not have a designated hotkey.
     *
     * @return an empty string
     */
    @Override
    public String hotKey() {
        return "";
    }
}
