package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.Random;

public class Goon extends Enemy {
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        actions.clear();

        for (ActionFactory factory : getActionFactories()) {
            Action action = factory.getAction(this, map);
            if (action != null)
                return action;
        }

        super.addActions(actions, this, map);

        for (Action action : actions) {
            if (action instanceof AttackAction) {
                if (random.nextDouble() <= 0.1) {
                    return new TalkAction(insults.get(random.nextInt(insults.size())), this);
                }
            }
        }
        return super.playTurn(actions, map, display);
    }

//        Location qLocation = map.locationOf(this);
//        Actions routesList = new Actions();
//
//
//        for (ActionFactory factory : getActionFactories()) {
//            Action action = factory.getAction(this, map);
//            if(action != null)
//                return action;
//        }
//
//        for (Exit exit : qLocation.getExits()) {
//            Location destination = exit.getDestination();
//            if (map.isAnActorAt(destination)) {
//                Actor actor = map.actorAt(destination);
//                if (actor instanceof Player){
//                    routesList.add(new AttackAction(this, actor));
//                    if (random.nextDouble() <= 0.1) {
//                        return new TalkAction(insults.get(random.nextInt(insults.size())), this);
//                    }
//                }
//            } else {
//                Ground adjacentGround = map.groundAt(destination);
//                routesList.add(adjacentGround.getMoveAction(this, destination, exit.getName(), exit.getHotKey()));
//            }
//        }
//

    private ArrayList<String> insults = new ArrayList<>();

    private Random random = new Random();

    public Goon(String name, Actor player) {
        super(name, 'o', 10, 50);
        insults.add("Weak!");
        insults.add("Slow!");
        insults.add("You're not going to win this!");
        insults.add("I'm stronger than you!");
        insults.add("You'll never get me!");
        addBehaviour(new FollowBehaviour(player));
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(super.getDamage() * 2, "punches");
    }

}
