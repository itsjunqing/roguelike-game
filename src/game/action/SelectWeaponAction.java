package game.action;

import edu.monash.fit2099.engine.*;
import game.GameSkills;

/**
 * An Action that equips a Weapon to the Player.
 */
public class SelectWeaponAction extends Action {

    private Item weaponItem;

    /**
     * Constructor to create an Action that equips a Weapon to the Player.
     *
     * @param weaponItem a weaponItem to equip to the Player
     */
    public SelectWeaponAction(Item weaponItem) {
        this.weaponItem = weaponItem;
    }

    /**
     * Adds a skill to the weaponItem to signify that the Player is currently using the weaponItem.
     * After equipped, player would not be able to drop the equipped WeaponItem unless unequipped.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a suitable description to display in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        weaponItem.addSkill(GameSkills.WEAPONSKILL);
        for (Action action : weaponItem.getAllowableActions()) {
            if (action instanceof DropItemAction) {
                weaponItem.getAllowableActions().remove(action);
            }
        }
        return actor + " equipped the " + weaponItem;
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     *
     * @param actor The actor performing the action.
     * @return a string, e.g. "Player equips the Bow"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " equips the " + weaponItem;
    }

    /**
     * Returns an empty string, as selecting a weaponItem does not have a dedicated hotkey.
     *
     * @return an empty string
     */
    @Override
    public String hotKey() {
        return "";
    }
}
