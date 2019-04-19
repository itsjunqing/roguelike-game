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
        for (Item itemInInventory : target.getInventory()) {
            if (itemInInventory instanceof RocketPlans) {
                target.removeItemFromInventory(itemInInventory);
                target.addItemToInventory(item);
                break;
            }
        }
        return actor + " has given " + item + " to " + target;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "";
    }

    @Override
    public String hotKey() {
        return "";
    }

}
