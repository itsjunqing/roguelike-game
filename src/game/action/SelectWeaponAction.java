package game.action;

import edu.monash.fit2099.engine.*;
import game.GameSkills;
import game.GameWorld;
import game.actor.GamePlayer;

public class SelectWeaponAction extends Action {

    private Item weaponItem;

    public SelectWeaponAction(Item weaponItem) {
        this.weaponItem = weaponItem;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
//        GamePlayer gamePlayer = GameWorld.getGamePlayer();
//        weaponItem.getAllowableActions().remove(this);
//        weaponItem.getAllowableActions().add(new DeselectWeaponAction(weaponItem));
//        gamePlayer.removeWeapon(weaponItem);
//        gamePlayer.addItemToInventory(weaponItem);
        for (Item item : actor.getInventory()){
            if (item.equals(weaponItem)){
                item.addSkill(GameSkills.WEAPONSKILL);
                for (Action action : item.getAllowableActions()){
                    if (action instanceof DropItemAction){
                        item.getAllowableActions().remove(action);
                    }
                }
            }
        }

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
