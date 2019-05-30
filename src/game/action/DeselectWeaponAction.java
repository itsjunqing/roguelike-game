package game.action;


import edu.monash.fit2099.engine.*;
import game.GameSkills;

/**
 * An Action that removes the Weapon from being equipped by the Player
 */
public class DeselectWeaponAction extends Action {

    private Item weaponItem;

    /**
     * Constructor to create an Action that unequips a Weapon from the Player
     *
     * @param weaponItem a weaponItem to unequip from the Player
     */
    public DeselectWeaponAction(Item weaponItem) {
        this.weaponItem = weaponItem;
    }

    /**
     * Removes a skill from the weaponItem to signify that the Player is currently not using the weaponItem.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a suitable description to display in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        weaponItem.removeSkill(GameSkills.WEAPONSKILL);
        weaponItem.getAllowableActions().add(new DropItemAction(weaponItem));
        return actor + " unequipped the " + weaponItem;
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     *
     * @param actor The actor performing the action.
     * @return a string, e.g. "Player unequip the Bow"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " unequip the " + weaponItem;
    }

    /**
     * Returns an empty string, as deselecting a weaponItem does not have a dedicated hotkey.
     *
     * @return an empty string
     */
    @Override
    public String hotKey() {
        return "";
    }
}
