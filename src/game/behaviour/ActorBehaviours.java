package game.behaviour;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.GameMap;

public interface ActorBehaviours {

    void addBehaviour(ActionFactory behaviour);

    Action executeBehaviours(GameMap map);
}
