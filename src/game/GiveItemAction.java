package game;

import edu.monash.fit2099.engine.*;

public class GiveItemAction extends Action {

    private Actor target;
    private Item item;

    public GiveItemAction(Actor target, Item item) {
        this.target = target;
        this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        target.addItemToInventory(item);
        actor.removeItemFromInventory(item);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " gives " + item + " to " + target;
    }

    @Override
    public String hotKey() {
        return "";
    }

}
