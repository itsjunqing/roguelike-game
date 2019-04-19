package game;

import edu.monash.fit2099.engine.Item;

public class RocketBody extends Item {

    public RocketBody(String name) {
        super(name, '[');
        allowableActions.add(new BuildRocketAction(this));
    }
}
