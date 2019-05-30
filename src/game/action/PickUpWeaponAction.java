package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponItem;
import game.GameWorld;
import game.actor.GamePlayer;

public class PickUpWeaponAction extends Action {

    private WeaponItem weaponItem;

    public PickUpWeaponAction(WeaponItem weaponItem) {
        this.weaponItem = weaponItem;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(weaponItem);

        GamePlayer gamePlayer = GameWorld.getGamePlayer();
        weaponItem.getAllowableActions().remove(this);
        weaponItem.getAllowableActions().add(new SelectWeaponAction(weaponItem));
        gamePlayer.addWeapon(weaponItem);

        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up " + weaponItem;
    }

    @Override
    public String hotKey() {
        return "";
    }
}
