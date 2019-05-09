package game;

import edu.monash.fit2099.engine.Item;

/**
 * Class representing a StunPowderBomb that can stun the Actor.
 */
public class StunPowderBomb extends Item {

    public static final char STUN_POWDER_CHAR = ':';

    /**
     * Constructor to create stun powder bomb with a name.
     * This StunPowderBomb is a static item which does not move after it is placed on a Location.
     *
     * @param name name of the stun powder bomb
     */
    public StunPowderBomb(String name) {
        super(name, STUN_POWDER_CHAR);
        allowableActions.clear();
    }

}
