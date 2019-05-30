package game.action;


import edu.monash.fit2099.engine.*;
import game.GameSkills;

public class DeselectWeaponAction extends Action {

    private Item weaponItem;

    public DeselectWeaponAction(Item weaponItem) {
        this.weaponItem = weaponItem;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        weaponItem.removeSkill(GameSkills.WEAPONSKILL);
        weaponItem.getAllowableActions().add(new DropItemAction(weaponItem));
        return actor + " unequipped the " + weaponItem;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " unequip the " + weaponItem;
    }

    @Override
    public String hotKey() {
        return "";
    }
}
