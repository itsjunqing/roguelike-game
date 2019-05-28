package game.item;

import edu.monash.fit2099.engine.Item;
import game.GameSkills;
import game.GameWorld;
import game.action.PickUpOxygenTankAction;

public class OxygenTank extends Item {

    public static final char OXYGENTANK_CHAR = 'T';
    private int oxygenCount = 10;

    public OxygenTank(String name) {
        super(name, OXYGENTANK_CHAR);
        addSkill(GameSkills.OXYGENSUPPLY);
        allowableActions.clear();
        allowableActions.add(new PickUpOxygenTankAction(this, GameWorld.getGamePlayer()));
    }

    public boolean hasOxygen() {
        return oxygenCount != 0;
    }

    public void decreaseCount() {
        oxygenCount--;
    }
}
