package game;

import edu.monash.fit2099.engine.Item;

public class StunPowderBomb extends Item {

    /**
     * Constructor to create stun powder bomb with a name.
     * This StunPowderBomb is a static item which does not move after it is placed on a Location.
     *
     * @param name name of the stun powder bomb
     */
    public StunPowderBomb(String name) {
        super(name, ':');
        allowableActions.clear();
    }

}
