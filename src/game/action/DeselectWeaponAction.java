package game.action;

import edu.monash.fit2099.engine.*;
import game.GameSkills;
import game.GameWorld;
import game.actor.GamePlayer;

public class DeselectWeaponAction extends Action {

    private Item weaponItem;

    public DeselectWeaponAction(Item weaponItem) {
        this.weaponItem = weaponItem;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
//        GamePlayer gamePlayer = GameWorld.getGamePlayer();
//        weaponItem.getAllowableActions().remove(this);
//        weaponItem.getAllowableActions().add(new SelectWeaponAction(weaponItem));
//        gamePlayer.removeItemFromInventory(weaponItem);
//        gamePlayer.addWeapon(weaponItem);
        for (Item item : actor.getInventory()){
            if (item.equals(weaponItem)){
                item.removeSkill(GameSkills.WEAPONSKILL);
                item.getAllowableActions().add(new DropItemAction(item));
            }
        }
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
