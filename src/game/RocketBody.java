package game;

import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;

public class RocketBody extends Item {

    public RocketBody(String name) {
        super(name, '[');
        allowableActions.clear();
        allowableActions.add(new DropItemAction(this));
    }

}
