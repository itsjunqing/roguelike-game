package game.action;

import edu.monash.fit2099.engine.*;
import game.GameSkills;

public class SelectWeaponAction extends Action {

    private Item weaponItem;

    public SelectWeaponAction(Item weaponItem) {
        this.weaponItem = weaponItem;
    }

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

    @Override
    public String menuDescription(Actor actor) {
        return actor + " equips the " + weaponItem;
    }

    @Override
    public String hotKey() {
        return "";
    }
}
