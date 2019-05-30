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

    @Override
    public void addBehaviour(ActionFactory behaviour) {
        actionFactories.add(behaviour);
    }

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
     * Play a turn by displaying a menu to the user and getting their selected Action returned.
     * If it is stunned, can only skip its current action until the stun effect is gone (after 2 stuns).
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
            updateStunnedActions(actions, map);
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

    private boolean isStunned(GameMap map) {
        for (Item item : map.locationOf(this).getItems()) {
            if (stunPowders.contains(item)) {
                stunPowderOnMap = item;
                return true;
            }
        }
        return false;
    }

    private void updateStunnedActions(Actions actions, GameMap map) {
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

    public static void addStunPowder(Item stunPowder) {
        stunPowders.add(stunPowder);
    }

    public void addTank(OxygenTank tank) {
        oxygenTanks.add(tank);
    }

    private void updateOxygenStatus() {
        OxygenTank tank = oxygenTanks.get(0);
        tank.decreaseCount();
        if (!tank.hasOxygen()) {
            oxygenTanks.remove(tank);
        }
    }

    public ArrayList<OxygenTank> getOxygenTanks() {
        return oxygenTanks;
    }
}
