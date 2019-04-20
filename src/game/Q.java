package game;

import edu.monash.fit2099.engine.*;

public class Q extends Actor {


    public Q(String name) {
        super(name, 'Q', 8, 1000);
        addItemToInventory(new RocketBody("Rocket body"));
    }

    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        boolean dissapear = false;
        Location qLocation = map.locationOf(this);
        actions.clear();

        for (Exit exit : qLocation.getExits()) {
            Location destination = exit.getDestination();
            if (map.isAnActorAt(destination)) {
                Actor actor = map.actorAt(destination);
                for (Item item : this.getInventory()) {
                    if (item instanceof RocketPlans) {
                        display.println(disappear(map));
                        return new GiveItemAction(actor, new RocketBody("Rocket body"));
                    }
                }
            } else {
                Ground adjacentGround = map.groundAt(destination);
                actions.add(adjacentGround.getMoveAction(this, destination, exit.getName(), exit.getHotKey()));
            }
        }
        actions.add(new SkipTurnAction());
        return super.playTurn(actions, map, display);
    }


    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        boolean hasrocketplans = false;
        Actions actions = new Actions();
        for (Item itemInInventory : otherActor.getInventory()) {
            if (itemInInventory instanceof RocketPlans) {
                actions.add(new TalkAction("Hand the rocket plans over, I don't have all day", this));
                actions.add(new GiveItemAction(this, itemInInventory));
                hasrocketplans = true;
                break;
            }
        }
        if (!hasrocketplans){
            actions.add(new TalkAction("I can give you something that will help, come back to me when you've the plans.", this));
        }
        return actions;
    }


    private String disappear(GameMap map) {
        map.removeActor(this);
        return this + " disappears into the air ~~~~";
    }


}
