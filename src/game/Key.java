package game;

import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;

public class Key extends Item {

    public Key(String name) {
        super(name, 'K');
        allowableActions.clear();
        allowableActions.add(new DropItemAction(this));
    }
}
