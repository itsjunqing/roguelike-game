package game.item;

import edu.monash.fit2099.engine.Item;
import game.actor.GamePlayer;
import game.MoonSkills;
import game.action.PickUpOxygenTankAction;

public class OxygenTank extends Item {

    public static final char OXYGENTANK_CHAR = 'T';
    private int oxygenCount = 10;
    private static GamePlayer player;

    public OxygenTank(String name) {
        super(name, OXYGENTANK_CHAR);
        addSkill(MoonSkills.OXYGENSUPPLY);
        allowableActions.clear();
        allowableActions.add(new PickUpOxygenTankAction(this, player));
    }

    public boolean hasOxygen() {
        return oxygenCount != 0;
    }

    public void decreaseCount() {
        oxygenCount--;
    }

    public static void setPlayer(GamePlayer player) {
        OxygenTank.player = player;
    }
}
