package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.Application;
import game.actor.GamePlayer;

public class OxygenSafetyBehaviour implements ActionFactory {

    private Location safeLocation;
    private GamePlayer player;

    public OxygenSafetyBehaviour(Location safeLocation, GamePlayer player) {
        this.safeLocation = safeLocation;
        this.player = player;
    }

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
