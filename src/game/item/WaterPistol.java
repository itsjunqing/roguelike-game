package game.item;

import edu.monash.fit2099.engine.Item;
import game.ground.Water;

public class WaterPistol extends Item {

//    private boolean filled = false;

    public WaterPistol(String name) {
        super(name, 'P');
        Water.addWaterPistol(this);
//        addSkill(MoonSkills.WATERSKILL);
    }
}
