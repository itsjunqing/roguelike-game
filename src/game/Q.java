package game;

import edu.monash.fit2099.engine.*;

public class Q extends Actor {
    private boolean pass = false;

    public Q(String name) {
        super(name, 'Q', 8, 1000);
        // this is duplicated code? added rocket body into inventory but not removed when giving to player
        addItemToInventory(new RocketBody("Rocket body"));
    }

    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        if (!pass) {
            Location qLocation = map.locationOf(this);
            actions.clear();

            for (Exit exit : qLocation.getExits()) {
                Location destination = exit.getDestination();
                if (map.isAnActorAt(destination)) {
                    Actor actor = map.actorAt(destination);
                    for (Item item : this.getInventory()) {
                        if (item instanceof RocketPlans) {
                            pass = true;
                            // not getting rocket body from inventory?
                            return new GiveItemAction(actor, new RocketBody("Rocket body"));
                        }
                    }
                } else {
                    Ground adjacentGround = map.groundAt(destination);
                    actions.add(adjacentGround.getMoveAction(this, destination, exit.getName(), exit.getHotKey()));
                }
            }
            // what does this do? i've forgotten. option for the system to choose whether to move the Q or not to move the Q?
            actions.add(new SkipTurnAction());
            return super.playTurn(actions, map, display);
        }
        else{
            display.println(disappear(map));
            return new SkipTurnAction();
        }
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
        return this + " disappears into the air with a cheery wave ~~~~";
    }

}
