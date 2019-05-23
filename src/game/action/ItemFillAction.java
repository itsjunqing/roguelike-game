package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.MoonSkills;

public class ItemFillAction extends Action {

    private Item item;
    private MoonSkills skill;

    public ItemFillAction(Item item, MoonSkills skill) {
        this.item = item;
        this.skill = skill;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        item.addSkill(skill);
        return actor + " has refilled the " + item;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " refills the " + item;
    }

    @Override
    public String hotKey() {
        return "";
    }
}
