package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Enemy extends Actor {

    private final int BASE_DAMAGE = 5;

    public Enemy(String name, char displayChar, int priority, int hitPoints) {
        super(name, displayChar, priority, hitPoints);
        addItemToInventory(new Key("Key"));
    }

    private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

    public void addBehaviour(ActionFactory behaviour) {
        actionFactories.add(behaviour);
    }

    protected List<ActionFactory> getActionFactories() {
        return actionFactories;
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(BASE_DAMAGE, "scratches");
    }

    public int getDamage() {
        return BASE_DAMAGE;
    }

    protected Actions addActions(Actions actions, Actor enemy, GameMap map){

        Location qLocation = map.locationOf(this);

        for (Exit exit : qLocation.getExits()) {
            Location destination = exit.getDestination();
            if (map.isAnActorAt(destination)) {
                Actor actor = map.actorAt(destination);
                if (actor instanceof Player) {
                    actions.add(new AttackAction(enemy, actor));
                }
            }else {
                Ground adjacentGround = map.groundAt(destination);
                actions.add(adjacentGround.getMoveAction(enemy, destination, exit.getName(), exit.getHotKey()));
            }
        }
        actions.add(new SkipTurnAction());
        return actions;
    }
}
