package game;

import edu.monash.fit2099.engine.Item;

public class Rocket extends Item {

    public Rocket(String name) {
        super(name, '^');
        allowableActions.clear();
    }
}
