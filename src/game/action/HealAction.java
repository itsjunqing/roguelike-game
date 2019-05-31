package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * An Action that heals an Actor by a heal amount.
 */
public class HealAction extends Action {

    private Item potion;
    private int healCount;

    /**
     * Constructor to create an Action that heals an Actor.
     *
     * @param potion    an Item that heals an Actor
     * @param healCount the amount of hitpoints to heal an Actor
     */
    public HealAction(Item potion, int healCount) {
        this.potion = potion;
        this.healCount = healCount;
    }

    /**
     * Heals the Actor by removing the potion from the map and increases the Actor's hitpoints by the healCount.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a suitable description to display in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(potion);
        actor.heal(healCount);
        return actor + " is healed with " + healCount + "hp";
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     *
     * @param actor The actor performing the action.
     * @return a string, e.g. "Player heals with the potion"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " heals with the " + potion;
    }

    /**
     * Returns an empty string, as healing an Actor does not have a dedicated hotkey.
     *
     * @return an empty string
     */
    @Override
    public String hotKey() {
        return "";
    }
}
