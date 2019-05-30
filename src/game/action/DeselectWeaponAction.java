package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponItem;
import game.GameWorld;
import game.actor.GamePlayer;

public class DeselectWeaponAction extends Action {

    private WeaponItem weaponItem;

    public DeselectWeaponAction(WeaponItem weaponItem) {
        this.weaponItem = weaponItem;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        GamePlayer gamePlayer = GameWorld.getGamePlayer();
        weaponItem.getAllowableActions().remove(this);
        weaponItem.getAllowableActions().add(new SelectWeaponAction(weaponItem));
        gamePlayer.removeItemFromInventory(weaponItem);
        gamePlayer.addWeapon(weaponItem);

        return actor + " unequipped the " + weaponItem;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " deselects the " + weaponItem;
    }

    @Override
    public String hotKey() {
        return "";
    }
}
