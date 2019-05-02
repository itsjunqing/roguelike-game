package game;

import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;

public class Key extends Item {

    /**
     * Constructor to create a key with a name.
     * This Key is by default in the enemy's inventory hence it only has DropItemAction.
     *
     * @param name the name of the key
     */
    public Key(String name) {
        super(name, 'K');
        allowableActions.clear();
        allowableActions.add(new DropItemAction(this));
    }
}
