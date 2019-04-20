package game;

import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;

public class RocketEngine extends Item {

    public RocketEngine(String name) {
        super(name, ']');
        allowableActions.clear();
        allowableActions.add(new DropItemAction(this));
    }
}
