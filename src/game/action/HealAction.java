package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class HealAction extends Action {

    private Item potion;
    private int healCount;

    public HealAction(Item potion, int healCount) {
        this.potion = potion;
        this.healCount = healCount;
    }

    @Override
    public String execute(Actor actor, GameMap map){
        map.locationOf(actor).removeItem(potion);
        actor.heal(healCount);
        return actor + " is healed with " + healCount + "hp";
    }

    @Override
    public String menuDescription(Actor actor){
        return actor + " heals with the " + potion;
    }

    @Override
    public String hotKey() {
        return "";
    }
}
