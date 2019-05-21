package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

/**
 * Class representing the GamePlayer.
 */
public class GamePlayer extends Player {

    private static ArrayList<Item> stunPowders = new ArrayList<>();
    public static final char GAME_PLAYER_CHAR = '@';
    private int stunCount = 0;
    private int totalOxygenCount = 0;
    private static ArrayList<OxygenTank> otank = new ArrayList<>();
    private static ArrayList<Integer> ocount = new ArrayList<>();

    /**
     * Constructor to create a GamePlayer.
     *
     * @param name      name of the player in the UI
     * @param priority  how early in the turn the player can act
     * @param hitPoints player's starting number of hitpoints
     */
    public GamePlayer(String name, int priority, int hitPoints) {
        super(name, GAME_PLAYER_CHAR, priority, hitPoints);
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

//        if (totalOxygenCount == 0) {
//             fly back
//        } else {
//            totalOxygenCount--;
//        }
        if (map.equals(Application.getMoonMap())){
            for (Action action : actions){
                if (action instanceof MoveActorAction){
                    actions.remove(action);
                }
            }
        }

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
        if (map.equals(Application.getMoonMap())) {
            if (otank.size() > 0) {
                for (Item item : this.getInventory()) {
                    if (item.equals(otank.get(0))) {
                        if (ocount.size() > 0) {
                            ocount.set(0, ocount.get(0) - 1);
                            if (ocount.get(0) <= 0) {
                                ocount.remove(0);
                                this.removeItemFromInventory(item);
                                otank.remove(0);
                            }
                        }
                    }
                }
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

    private void calculateOxygenCount() {
        for (Item item : this.getInventory()) {
            if (item.hasSkill(MoonSkills.OXYGENSUPPLY)) {
                totalOxygenCount += OxygenTank.oxygenCount;
            }
        }
    }

    public static void addTank(OxygenTank tank){
        otank.add(tank);
    }

    public static void addOCount(Integer oxygen){
        ocount.add(oxygen);
    }


}
