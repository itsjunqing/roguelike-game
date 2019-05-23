package game.action;

import edu.monash.fit2099.engine.*;

/**
 * An Action that gives an Actor an Item while removing the Item from the other Actor's inventory.
 */
public class GiveItemAction extends Action {

    private Actor target;
    private Item item;

    /**
     * Constructor to create an Action that gives an actor an item.
     *
     * @param target an actor to give the item
     * @param item   item to give
     */
    public GiveItemAction(Actor target, Item item) {
        this.target = target;
        this.item = item;
    }

    /**
     * Gives an item to the target by putting that item into the target's inventory
     * and removes the item from the giver's inventory.
     *
     * @param actor the actor performing the action.
     * @param map   the map the actor is on.
     * @return a suitable description to display in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        target.addItemToInventory(item);
        actor.removeItemFromInventory(item);
        return menuDescription(actor);
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     *
     * @param actor the actor performing the action.
     * @return a string, e.g. "Q gives Rocket Body to Player"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " gives " + item + " to " + target;
    }

    /**
     * Returns an empty string, as item giving does not have a dedicated hotkey.
     *
     * @return an empty string
     */
    @Override
    public String hotKey() {
        return "";
    }

}
