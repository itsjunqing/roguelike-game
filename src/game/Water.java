package game;

import edu.monash.fit2099.engine.*;

public class Water extends Ground {
    public Water(){
        super('*');
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        if (actor.hasSkill(MoonSkills.WATERSKILL)){
            for (Item item : actor.getInventory()){
                if (item.hasSkill(MoonSkills.WATERSKILL)){
                    actions.add(new FillPistolAction(item));
                }
            }
        }
        return actions;
    }
}
