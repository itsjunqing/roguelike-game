package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class representing a Goon.
 */
public class Goon extends Enemy {

    public static final char GOON_CHAR = 'o';
    private Random random = new Random();
    private static ArrayList<String> insults = new ArrayList<>();

    /**
     * Constructor to create an Enemy of type Goon with a name.
     * Adds an ability for the Goon to follow the player around.
     *
     * @param name   the name of the Goon
     */
    public Goon(String name, Actor player) {
        super(name, GOON_CHAR, 10, 5);
        insults.add("Weak!");
        insults.add("Slow!");
        insults.add("You're not going to win this!");
        insults.add("I'm stronger than you!");
        insults.add("You'll never get me!");
        addBehaviour(new FollowBehaviour(player));
    }

    /**
     * Allows the Goon to move towards the player if FollowBehaviour is active. Gives Goon the ability
     * to shout an insult to the player with a 10% chance of success. Will return a list of possible actions
     * if FollowBehaviour is inactive and shouting an insult is unsuccessful.
     *
     * @param actions collection of possible actions for Goon in the turn
     * @param map     the map containing the Actor
     * @param display the object that performs the console I/O
     * @return the Action to be performed, e.g. attacking the player when it is next to it
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        if (random.nextDouble() <= 0.1) {
            return new TalkAction(insults.get(random.nextInt(insults.size())), this);
        }
        actions.clear();
        for (ActionFactory factory : getActionFactories()) {
            Action action = factory.getAction(this, map);
            if (action != null)
                return action;
        }
        super.addActions(actions, this, map);
        return super.playTurn(actions, map, display);
    }

    /**
     * Creates a new IntrinsicWeapon with twice the damage of a base enemy damage and a new description when it attacks
     *
     * @return an IntrinsicWeapon for Goon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(BASE_DAMAGE * 2, "punches");
    }
}
