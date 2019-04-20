package game;

import edu.monash.fit2099.engine.*;

import java.util.Random;

public class Q extends Actor {

    Random random = new Random();

    public Q(String name) {
        super(name, 'Q', 8, 1000);
    }


    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        Location qLocation = map.locationOf(this);
        Actions routesList = new Actions();

        for (Exit exit : qLocation.getExits()) {
            Location destination = exit.getDestination();
            if (map.isAnActorAt(destination)) {
                Actor actor = map.actorAt(destination);
                for (Item item : actor.getInventory()) {
                    if (item instanceof RocketPlans) {
                        return new TalkAction("Hand the rocket plans over, I don’t have all day!");
                    }
                }

                for (Item item : this.getInventory()) {
                    if (item instanceof RocketPlans) {
                        display.println(disappear(map));
//                        RocketBody rocketBody = (RocketBody) Item.newInventoryItem("Rocket body", '[');
                        return new GiveItemAction(actor, new RocketBody("Rocket body"));
                    }
                }
                return new TalkAction("I can give you something that will help, come back to me when you've the plans.");
            } else {
                Ground adjacentGround = map.groundAt(destination);
                routesList.add(adjacentGround.getMoveAction(this, destination, exit.getName(), exit.getHotKey()));
            }
        }
//        return routesList.get(random.nextInt(routesList.size()));
        return super.playTurn(actions, map, display);
    }


    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        for (Item itemInInventory : otherActor.getInventory()) {
            if (itemInInventory instanceof RocketPlans) {
                actions.add(new GiveItemAction(this, itemInInventory));
                break;
            }
        }
        return actions;
    }


    private String disappear(GameMap map) {
        map.removeActor(this);
        return this + " disappears into the air ~~~~";
    }


}
