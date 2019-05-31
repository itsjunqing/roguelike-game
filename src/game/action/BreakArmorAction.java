package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.GameSkills;

import java.util.Random;

/**
 * An Action that removes the Invulnerable skill from an Actor.
 */
public class BreakArmorAction extends Action {

    private Actor target;
    private Item item;
    private GameSkills itemSkill;
    private GameSkills armorSkill;
    private Random random = new Random();

    // breaks the armor with an item's skill

    /**
     * Constructor to create an Action that removes an item with a Skill using an Item with a given skill
     *
     * @param target the Actor holding the Item with the given skill
     * @param item the Item containing the given skill
     * @param itemSkill the Skill required to remove the Item
     * @param armorSkill the Skill possessed by an Item to be removed
     */
    public BreakArmorAction(Actor target, Item item, GameSkills itemSkill, GameSkills armorSkill) {
        this.target = target;
        this.item = item;
        this.itemSkill = itemSkill;
        this.armorSkill = armorSkill;
    }

    /**
     * Removes the Item in the target's inventory if the Item has a given Skill at a 70% success rate.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a suitable description to display in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        item.removeSkill(itemSkill);

        if (random.nextDouble() <= 0.7) {
            for (Item item : target.getInventory()) {
                if (item.hasSkill(armorSkill)) {
                    target.removeItemFromInventory(item);
                    return target + "'s armor is destroyed";
                }
            }
        }
        return "Failed to destroy " + target + "'s armor";
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     *
     * @param actor The actor performing the action.
     * @return a string, e.g. "Player squirts Yugo Maxx to remove armor"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " squirts " + target + " to remove armor";
    }

    /**
     * Returns an empty string, as breaking armor does not have a dedicated hotkey.
     *
     * @return an empty string
     */
    @Override
    public String hotKey() {
        return "";
    }
}
