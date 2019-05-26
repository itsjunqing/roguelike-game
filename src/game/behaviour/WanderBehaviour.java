package game.behaviour;

import edu.monash.fit2099.engine.*;

import java.util.Random;

public class WanderBehaviour implements ActionFactory{

    private Random random = new Random();

    public WanderBehaviour() {
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location actorLocation = map.locationOf(actor);
        Actions actions = new Actions();
        for (Exit exit : actorLocation.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                Ground adjacentGround = map.groundAt(destination);
                actions.add(adjacentGround.getMoveAction(actor, destination, exit.getName(), exit.getHotKey()));
            }
        }
        return actions.get(random.nextInt(actions.size()));
    }
}
