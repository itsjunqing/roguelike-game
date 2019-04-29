package game;

import edu.monash.fit2099.engine.*;

public class Grunt extends Enemy {

    // Grunts have 50 hitpoints and are always represented with a g
    public Grunt(String name, Actor player) {
        super(name, 'g', 5, 5);
        addBehaviour(new FollowBehaviour(player));
    }

    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        actions.clear();
        for (ActionFactory factory : getActionFactories()) {
            Action action = factory.getAction(this, map);
            if (action != null)
                return action;
        }
        super.addActions(actions, this, map);

//        Location qLocation = map.locationOf(this);
//        actions.clear();
//
//		for (ActionFactory factory : getActionFactories()) {
//			Action action = factory.getAction(this, map);
//			if(action != null)
//				return action;
//		}
//
//        for (Exit exit : qLocation.getExits()) {
//            Location destination = exit.getDestination();
//            if (map.isAnActorAt(destination)) {
//                Actor actor = map.actorAt(destination);
//                if (actor instanceof  Player) {
//                    actions.add(new AttackAction(this, actor));
//                }
//            } else {
//                Ground adjacentGround = map.groundAt(destination);
//                actions.add(adjacentGround.getMoveAction(this, destination, exit.getName(), exit.getHotKey()));
//            }
//        }
        return super.playTurn(actions, map, display);
    }

}
