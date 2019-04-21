package game;

import edu.monash.fit2099.engine.Item;

public class StunPowderBomb extends Item {

    public StunPowderBomb(String name) {
        super(name, ':');
        allowableActions.clear();
    }

}
