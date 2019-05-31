package game.actor;

import edu.monash.fit2099.engine.*;
import game.Application;
import game.GameSkills;
import game.action.DeselectWeaponAction;
import game.action.EndGameAction;
import game.action.SelectWeaponAction;
import game.behaviour.ActionFactory;
import game.behaviour.ActorBehaviours;
import game.behaviour.OxygenSafetyBehaviour;
import game.item.OxygenTank;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the GamePlayer.
 */
public class GamePlayer extends Player implements ActorBehaviours {

    private static ArrayList<Item> stunPowders = new ArrayList<>();
    public static final char GAME_PLAYER_CHAR = '@';
    private int stunCount = 0;
    private ArrayList<OxygenTank> oxygenTanks = new ArrayList<>();
    private Item stunPowderOnMap = null;
    private List<ActionFactory> actionFactories = new ArrayList<>();

    /**
     * Constructor to create a GamePlayer.
     *
     * @param name      name of the player in the UI
     * @param priority  how early in the turn the player can act
     * @param hitPoints player's starting number of hitpoints
     */
    public GamePlayer(String name, int priority, int hitPoints, Location safeLocation) {
        super(name, GAME_PLAYER_CHAR, priority, hitPoints);
        addBehaviour(new OxygenSafetyBehaviour(safeLocation, this));
    }

    /**
     * Play a turn by displaying a menu to the user and getting their selected Action returned.
     * If it is stunned, can only skip its current action until the stun effect is gone (after 2 stuns).
     * <p>
     * // add the description for selecting and deselecting weapon here
     * Otherwise, it runs the default implementation of a Player.
     *
     * @param actions the actions to display
     * @param map     the map to display
     * @param display the object that performs the console I/O
     * @return a list of actions that the player is able to perform
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        actions.add(new EndGameAction());

        Action action = executeBehaviours(map);
        if (action != null) {
            return action;
        }

        if (map.equals(Application.getMoonMap())) {
            updateOxygenStatus();
        }

        if (isStunned(map)) {
            updateStunnedStatus(actions, map);
        }

        if (this.hasSkill(GameSkills.WEAPONSKILL)) {
            for (Item item : inventory) {
                if (item.hasSkill(GameSkills.WEAPONSKILL)) {
                    actions.add(new DeselectWeaponAction(item));
                }
            }
        } else {
            for (Item item : inventory) {
                if (item.asWeapon() != null) {
                    actions.add(new SelectWeaponAction(item));
                }
            }
        }

        return super.playTurn(actions, map, display);
    }

    /**
     * Adds a behaviour into the list of behaviours.
     *
     * @param behaviour the behaviour which the Actor has
     */
    @Override
    public void addBehaviour(ActionFactory behaviour) {
        actionFactories.add(behaviour);
    }

    /**
     * Execute the GamePlayer's behaviour.
     *
     * @param map map which the GamePlayer is on
     * @return the Action to be executed for the behaviour
     */
    @Override
    public Action executeBehaviours(GameMap map) {
        for (ActionFactory factory : actionFactories) {
            Action action = factory.getAction(this, map);
            if (action != null)
                return action;
        }
        return null;
    }


    /**
     * Returns the item in the inventory as a Weapon if the item is a WeaponItem and has a WEAPONSKILL.
     * Otherwise, it returns the default IntrinsicWeapon.
     *
     * @return a Weapon of IntrinsicWeapon or a WeaponItem to be used as the GamePlayer's weapon for attacking
     */
    @Override
    public Weapon getWeapon() {
        for (Item item : inventory) {
            if (item.asWeapon() != null) {
                if (item.hasSkill(GameSkills.WEAPONSKILL)) {
                    return item.asWeapon();
                }
            }
        }
        return getIntrinsicWeapon();
    }

    /**
     * Returns true if the GamePlayer is stunned by checking if the GamePlayer's location has a StunPowder.
     *
     * @param map map which the GamePlayer is on
     * @return true or false
     */
    private boolean isStunned(GameMap map) {
        for (Item item : map.locationOf(this).getItems()) {
            if (stunPowders.contains(item)) {
                stunPowderOnMap = item;
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the stunned status by decreasing the stunCount by 1 if it is stunned and forces the GamePlayer to
     * skip its turn. Otherwise, it removes the StunPowder from the GamePlayer's location.
     *
     * @param actions a list of actions to be returned
     * @param map     the map which the GamePlayer is on
     */
    private void updateStunnedStatus(Actions actions, GameMap map) {
        if (stunCount != 2) {
            actions.clear();
            actions.add(new SkipTurnAction());
            stunCount++;
        } else {
            stunCount = 0;
            map.locationOf(this).removeItem(stunPowderOnMap);
            stunPowderOnMap = null;
        }
    }

    /**
     * Adds a StunPowder into the list of recognizable stunPowders as reference.
     *
     * @param stunPowder a StunPowder
     */
    public static void addStunPowder(Item stunPowder) {
        stunPowders.add(stunPowder);
    }

    /**
     * Adds an OxygenTank into the list of oxygenTanks.
     *
     * @param tank an OxygenTank
     */
    public void addTank(OxygenTank tank) {
        oxygenTanks.add(tank);
    }

    /**
     * Updates the oxygen status by decreasing the oxygen count for the first OxygenTank
     * in the list of oxygenTanks and remove it from the list if there isn't any oxygen left.
     */
    private void updateOxygenStatus() {
        OxygenTank tank = oxygenTanks.get(0);
        tank.decreaseCount();
        if (!tank.hasOxygen()) {
            oxygenTanks.remove(tank);
        }
    }

    /**
     * Returns the list of oxygenTanks.
     *
     * @return list of oxygenTanks
     */
    public ArrayList<OxygenTank> getOxygenTanks() {
        return oxygenTanks;
    }
}
