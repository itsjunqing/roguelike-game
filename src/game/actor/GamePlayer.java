package game.actor;

import edu.monash.fit2099.engine.*;
import game.Application;
import game.action.EndGameAction;
import game.ground.LockedDoor;
import game.item.OxygenTank;

import java.util.ArrayList;

/**
 * Class representing the GamePlayer.
 */
public class GamePlayer extends Player {

    private static ArrayList<Item> stunPowders = new ArrayList<>();
    public static final char GAME_PLAYER_CHAR = '@';
    private int stunCount = 0;
    private ArrayList<OxygenTank> oxygenTanks = new ArrayList<>();
//    private int totalOxygenCount = 0;
//    private static ArrayList<Integer> ocount = new ArrayList<>();
    private static Location rocketLocation;

    /**
     * Constructor to create a GamePlayer.
     *
     * @param name      name of the player in the UI
     * @param priority  how early in the turn the player can act
     * @param hitPoints player's starting number of hitpoints
     */
    public GamePlayer(String name, int priority, int hitPoints) {
        super(name, GAME_PLAYER_CHAR, priority, 10);
        Enemy.addPlayer(this);
        LockedDoor.addPlayer(this);
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
        Location playerLocation = map.locationOf(this);

        for (Item item : playerLocation.getItems()) {
            if (stunPowders.contains(item)) {
                if (stunCount != 2) {
                    actions.clear();
                    actions.add(new SkipTurnAction());
                    stunCount++;
                    break;
                } else {
                    stunCount = 0;
                    playerLocation.removeItem(item);
                    break;
                }
            }
        }


//        if (map.equals(Application.getMoonMap())) {
//            if (oxygenTanks.size() > 0) {
//                for (Item item : this.getInventory()) {
//                    if (item.equals(oxygenTanks.get(0))) {
//                        if (ocount.size() > 0) {
//                            ocount.set(0, ocount.get(0) - 1);
//                            if (ocount.get(0) <= 0) {
//                                ocount.remove(0);
//                                this.removeItemFromInventory(item);
//                                oxygenTanks.remove(0);
//                            }
//                        }
//                    }
//                }
//            }
//        }

        if (map.equals(Application.getMoonMap())) {
            if (!oxygenTanks.isEmpty()) {
                OxygenTank tank = oxygenTanks.get(0);
                tank.decreaseCount();
                if (!tank.hasOxygen()) {
                    oxygenTanks.remove(tank);
                }
            } else {
                return new MoveActorAction(rocketLocation, "back to Earth via a safety system!");
            }
        }




        return super.playTurn(actions, map, display);
    }

    /**
     * Adds a StunPowder into the list of recognizable stunPowders as references.
     *
     * @param stunPowder an Item signifying the StunPowder
     */
    public static void addStunPowder(Item stunPowder) {
        stunPowders.add(stunPowder);
    }

    public void addTank(OxygenTank tank){
        oxygenTanks.add(tank);
    }


    public static void setRocketLocation(Location rocketLocation) {
        GamePlayer.rocketLocation = rocketLocation;
    }
}
