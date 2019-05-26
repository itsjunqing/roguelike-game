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
    public BreakArmorAction(Actor target, Item item, GameSkills itemSkill, GameSkills armorSkill) {
        this.target = target;
        this.item = item;
        this.itemSkill = itemSkill;
        this.armorSkill = armorSkill;
    }

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

    @Override
    public String menuDescription(Actor actor) {
        return actor + " squirts " + target + " to remove armor";
    }

    @Override
    public String hotKey() {
        return "";
    }
}
