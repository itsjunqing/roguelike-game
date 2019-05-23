package game.item;

import edu.monash.fit2099.engine.Item;
import game.GamePlayer;

/**
 * Class representing a StunPowder that can stun the Actor.
 */
public class StunPowder extends Item {

    public static final char STUN_POWDER_CHAR = ':';

    /**
     * Constructor to create static stun powder with a name.
     *
     * @param name name of the stun powder bomb
     */
    public StunPowder(String name) {
        super(name, STUN_POWDER_CHAR);
        allowableActions.clear();
        GamePlayer.addStunPowder(this);
    }

}
