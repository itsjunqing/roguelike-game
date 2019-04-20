package game;

import edu.monash.fit2099.engine.Item;

public class Rocket extends Item {

    public Rocket(String name) {
        super(name, '^');
        super.allowableActions.clear();
    }

//    @Override
//    public Actions getAllowableActions() {
//        Actions actions = super.allowableActions;
//        actions.clear();
//        return actions;
//    }
}
