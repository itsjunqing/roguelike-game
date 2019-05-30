package game.item;

import edu.monash.fit2099.engine.*;
import game.action.HealAction;

public class Potion extends Item {

    public Potion(String name, int healCount) {
        super(name, 'a');
        allowableActions.clear();
        allowableActions.add(new DropItemAction(this));
        allowableActions.add(new HealAction(this, healCount));
    }

    @Override
    public Actions getAllowableActions() {
        for (Action action : allowableActions) {
            if (action instanceof PickUpItemAction) {
                allowableActions.remove(action);
            }
        }
        return allowableActions;
    }

}
