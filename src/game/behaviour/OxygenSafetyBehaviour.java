package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.Application;
import game.actor.GamePlayer;

/**
 * Class representing the behaviour of having a oxygen safety mechanism.
 */
public class OxygenSafetyBehaviour implements ActionFactory {

    private Location safeLocation;
    private GamePlayer player;

    /**
     * Constructor to create a behaviour that allows the player to return to a safe location.
     *
     * @param safeLocation a Location for player to traverse to
     * @param player       the player with this behaviour
     */
    public OxygenSafetyBehaviour(Location safeLocation, GamePlayer player) {
        this.safeLocation = safeLocation;
        this.player = player;
    }

    /**
     * Moves the Actor to a safe location if there is no sufficient oxygen supplied to the Actor.
     *
     * @param actor the actor that performs the action
     * @param map   the map which the actor is in it
     * @return
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (map.equals(Application.getMoonMap())) {
            if (player.getOxygenTanks().isEmpty()) {
                return new MoveActorAction(safeLocation, "back to Earth via a safety system!");
            }
        }
        return null;
    }
}
