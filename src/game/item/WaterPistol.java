package game.item;

import edu.monash.fit2099.engine.Item;
import game.ground.Water;

/**
 * Class representing a WaterPistol that is able to be refilled with Water.
 */
public class WaterPistol extends Item {
    public static final char PISTOL_CHAR = 'P';

    /**
     * Constructor to create a WaterPistol.
     *
     * @param name name of the WaterPistol
     */
    public WaterPistol(String name) {
        super(name, PISTOL_CHAR);
        Water.addWaterPistol(this);
    }
}
