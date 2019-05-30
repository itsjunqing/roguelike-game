package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponItem;
import game.GameSkills;
import game.GameWorld;
import game.actor.GamePlayer;

public class SelectWeaponAction extends Action {

    private WeaponItem weaponItem;

    public SelectWeaponAction(WeaponItem weaponItem) {
        this.weaponItem = weaponItem;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        GamePlayer gamePlayer = GameWorld.getGamePlayer();
//        weaponItem.getAllowableActions().remove(this);
//        weaponItem.getAllowableActions().add(new DeselectWeaponAction(weaponItem));
//        gamePlayer.removeWeapon(weaponItem);
//        gamePlayer.addItemToInventory(weaponItem);
        weaponItem.addSkill(GameSkills.WEAPONSKILL);
        return actor + " equipped the " + weaponItem;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " selects the " + weaponItem;
    }

    @Override
    public String hotKey() {
        return "";
    }
}
