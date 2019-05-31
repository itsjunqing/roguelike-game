package game.item;

import edu.monash.fit2099.engine.Item;
import game.GameSkills;
import game.action.PickUpOxygenTankAction;

/**
 * Class representing an OxygenTank with the capability of supplying oxygen to the actor.
 */
public class OxygenTank extends Item {

    public static final char OXYGENTANK_CHAR = 'T';
    private int oxygenCount = 10;

    /**
     * Constructor to create an OxygenTank.
     *
     * @param name name of the OxygenTank
     */
    public OxygenTank(String name) {
        super(name, OXYGENTANK_CHAR);
        addSkill(GameSkills.OXYGENSUPPLY);
        allowableActions.clear();
        allowableActions.add(new PickUpOxygenTankAction(this));
    }

    /**
     * Returns true if the current OxygenTank has oxygen left.
     *
     * @return true or false
     */
    public boolean hasOxygen() {
        return oxygenCount != 0;
    }

    /**
     * Decreases the oxygenCount by 1.
     */
    public void decreaseCount() {
        oxygenCount--;
    }
}
