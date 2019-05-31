package game.item;

import edu.monash.fit2099.engine.*;
import game.action.HealAction;

/**
 * Class representing a Potion that heals an Actor.
 */
public class Potion extends Item {

    /**
     * Constructor to create a Potion.
     *
     * @param name      name of the Potion
     * @param healCount the heal amount of the Potion
     */
    public Potion(String name, int healCount) {
        super(name, 'a');
        allowableActions.clear();
        allowableActions.add(new DropItemAction(this));
        allowableActions.add(new HealAction(this, healCount));
    }

    /**
     * Stops Actor from being able to pick up the item but only able to heal itself when it is on the Potion.
     *
     * @return a collection of Action which the actor can perform
     */
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
