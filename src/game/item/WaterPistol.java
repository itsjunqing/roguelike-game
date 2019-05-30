package game.item;

import edu.monash.fit2099.engine.Item;
import game.ground.Water;

public class WaterPistol extends Item {

    public WaterPistol(String name) {
        super(name, 'G');
        Water.addWaterPistol(this);
    }
}
