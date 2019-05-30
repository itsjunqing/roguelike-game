package game.action;

import edu.monash.fit2099.engine.*;

public class DropWeaponAction extends Action {

    private WeaponItem weaponItem;

    public DropWeaponAction(WeaponItem weaponItem) {
        this.weaponItem = weaponItem;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }

    @Override
    public String hotKey() {
        return null;
    }
}
