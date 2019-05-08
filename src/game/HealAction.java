package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class HealAction extends Action {
    private Integer hitpoints;
    private Item healingitem;

    public HealAction(Integer hitpoints, Item item){
        this.hitpoints = hitpoints;
        this.healingitem = item;
    }

    @Override
    public String execute(Actor actor, GameMap map){
        map.locationOf(actor).removeItem(healingitem);
        actor.heal(hitpoints);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor){
        return actor + " heals with the " + healingitem;
    }

    @Override
    public String hotKey() {
        return "";
    }
}
