package game.item;

import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;
import game.ground.LockedDoor;

/**
 * Class representing a Key.
 */
public class Key extends Item {

    public static final char KEY_CHAR = 'K';

    /**
     * Constructor to create a Key with a name.
     * This Key can only be dropped by an enemy.
     *
     * @param name the name of the Key
     */
    public Key(String name) {
        super(name, KEY_CHAR);
        allowableActions.clear();
        allowableActions.add(new DropItemAction(this));
        LockedDoor.addKey(this);
    }
}
