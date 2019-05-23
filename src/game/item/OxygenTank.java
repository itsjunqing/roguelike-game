package game.item;

import edu.monash.fit2099.engine.Item;
import game.actor.GamePlayer;
import game.MoonSkills;
import game.action.PickUpOxygenTankAction;

public class OxygenTank extends Item {

    public static final char OXYGENTANK_CHAR = 'T';
//    public static final int oxygenCount = 10;
    private int oxygenCount = 10;
    private GamePlayer player;

    public OxygenTank(String name, GamePlayer player) {
        super(name, OXYGENTANK_CHAR);
        addSkill(MoonSkills.OXYGENSUPPLY);
        allowableActions.clear();
        allowableActions.add(new PickUpOxygenTankAction(this, player));


//        GamePlayer.addTank(this);
//        GamePlayer.addOCount(10);
    }


    public boolean hasOxygen() {
        return oxygenCount != 0;
    }

    public void decreaseCount() {
        oxygenCount--;
    }
}
