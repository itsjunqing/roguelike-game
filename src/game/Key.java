package game;

import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;

/**
 * Class representing a Key.
 */
public class Key extends Item {

    public static final char KEY_CHAR = 'K';

    /**
     * Constructor to create a key with a name.
     * This Key is by default in the enemy's inventory hence it has DropItemAction only.
     *
     * @param name the name of the key
     */
    public Key(String name) {
        super(name, KEY_CHAR);
        allowableActions.clear();
        allowableActions.add(new DropItemAction(this));
        LockedDoor.addKey(this);
    }
}
