package game;

import edu.monash.fit2099.engine.Item;

/**
 * Class representing a StunPowder that can stun the Actor.
 */
public class StunPowder extends Item {

    public static final char STUN_POWDER_CHAR = ':';

    /**
     * Constructor to create stun powder with a name.
     * This StunPowder is a static item which does not move after it is placed on a Location.
     * Makes the game player recognise the stun powder that is created.
     *
     * @param name name of the stun powder bomb
     */
    public StunPowder(String name) {
        super(name, STUN_POWDER_CHAR);
        allowableActions.clear();
        GamePlayer.addStunPowder(this);
    }

}
