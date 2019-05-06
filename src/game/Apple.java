package game;

import edu.monash.fit2099.engine.*;

public class Apple extends Item {
    public Apple(String name){
        super(name, 'a');
        allowableActions.clear();
        allowableActions.add(new DropItemAction(this));
        allowableActions.add(new HealAction(25, this));
    }

    @Override
    public Actions getAllowableActions() {
        for (Action action : super.getAllowableActions()){
            if (action instanceof PickUpItemAction){
                super.getAllowableActions().remove(action);
            }
        }
        return super.getAllowableActions();
    }
}
