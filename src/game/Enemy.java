package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for all Enemies. These represents the functions that the Enemies are capable of doing.
 */
public abstract class Enemy extends Actor {

    static final int BASE_DAMAGE = 5;
    private List<ActionFactory> actionFactories = new ArrayList<>();

    /**
     * Constructor that creates a new enemy and adds a Key item in its inventory.
     *
     * @param name a String the resembles the enemies name
     * @param displayChar a Character to identify the enemy on the GameMap
     * @param priority an integer that determines when the enemy is able to take its turn in a round
     * @param hitPoints an integer that determines the amount of damage it can take before being unconscious
     */
    protected Enemy(String name, char displayChar, int priority, int hitPoints) {
        super(name, displayChar, priority, hitPoints);
        addItemToInventory(new Key("Key"));
    }

    /**
     * Allows enemies to add special behaviours that the enemy may possess
     *
     * @param behaviour an ActionFactory that contains a set of rules that may determine what the enemy does
     */
    protected void addBehaviour(ActionFactory behaviour) {
        actionFactories.add(behaviour);
    }

    /**
     * Obtains a list of ActionFactory that contains the special behaviours that the enemy possesses
     *
     * @return a list of ActionFactory of the enemy
     */
    protected List<ActionFactory> getActionFactories() {
        return actionFactories;
    }

    /**
     * Sets the base damage for enemies and sets the description as "scratches" after it attacks
     * @return
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(BASE_DAMAGE, "scratches");
    }

    protected void addActions(Actions actions, Actor enemy, GameMap map) {
        Location location = map.locationOf(this);

        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();
            if (map.isAnActorAt(destination)) {
                Actor actor = map.actorAt(destination);
                if (actor instanceof Player) {
                    actions.add(new AttackAction(enemy, actor));
                }
            } else {
                Ground adjacentGround = map.groundAt(destination);
                actions.add(adjacentGround.getMoveAction(enemy, destination, exit.getName(), exit.getHotKey()));
            }
        }
        actions.add(new SkipTurnAction());
//        return actions;
    }
}
