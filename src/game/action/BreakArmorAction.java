package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.GameSkills;

import java.util.Random;

/**
 * An Action that breaks an Actor's armor.
 */
public class BreakArmorAction extends Action {

    private Actor target;
    private Item item;
    private GameSkills itemSkill;
    private GameSkills armorSkill;
    private Random random = new Random();

    /**
     * Constructor to create an Action that breaks an armor by using an item's skill.
     *
     * @param target     the Actor holding the Item with the given skill
     * @param item       the Item containing the skill to remove the Armor
     * @param itemSkill  the skill required to remove the Armor
     * @param armorSkill the skill possessed by the Armor
     */
    public BreakArmorAction(Actor target, Item item, GameSkills itemSkill, GameSkills armorSkill) {
        this.target = target;
        this.item = item;
        this.itemSkill = itemSkill;
        this.armorSkill = armorSkill;
    }

    /**
     * Removes the target's Armor in its inventory by utilizing the skill required to remove the Armor
     * with a 70% chance of success.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
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
     * @return a string, e.g. "Player squirts YugoMaxx to remove armor"
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
