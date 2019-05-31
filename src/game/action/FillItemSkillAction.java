package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.GameSkills;

/**
 * An Action that fills up an Item with a Skill.
 */
public class FillItemSkillAction extends Action {

    private Item item;
    private GameSkills skill;

    /**
     * Constructor for an Action that fills up the item.
     *
     * @param item  the item to be filled up
     * @param skill the skill to add to the item that signifies "filling it up"
     */
    public FillItemSkillAction(Item item, GameSkills skill) {
        this.item = item;
        this.skill = skill;
    }

    /**
     * Adds a skill to the item to signify that the item is "filled up"
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a string stating the item is refilled
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        item.addSkill(skill);
        return actor + " has refilled the " + item;
    }

    /**
     * A string suitable to display filling up an item in the UI.
     *
     * @param actor The actor performing the action.
     * @return a String
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " refills the " + item;
    }

    /**
     * Returns an empty string, as filling an item does not have a dedicated hotkey.
     *
     * @return an empty string
     */
    @Override
    public String hotKey() {
        return "";
    }
}
