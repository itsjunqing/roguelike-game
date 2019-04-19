package game;

import edu.monash.fit2099.engine.*;

public class Q extends Actor {

    private Item rocketBody = new RocketBody("Rocket body");

    public Q(String name) {
        super(name, 'Q', 8, 1000);
    }

    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {


        return super.playTurn(actions, map, display);
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        if (otherActor instanceof Player) {
            for (Item item : otherActor.getInventory()) {
                if (item instanceof RocketPlans) {
                    actions.add(new TalkAction("Hand the rocket plans over, I don’t have all day!"));
                    actions.add(new GiveItemAction(otherActor, rocketBody));
                    return actions;
                }
            }
        }

        actions.add(new TalkAction("I can give you something that will help, but I’m going to need the plans."));
        return actions;
    }

    public String disappear(GameMap map) {
        map.removeActor(this);
        return this + " disappears into the air";
    }


}
