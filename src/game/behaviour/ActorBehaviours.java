package game.behaviour;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.GameMap;

import java.util.ArrayList;
import java.util.List;

public interface ActorBehaviours {

    List<ActionFactory> actionFactories = new ArrayList<>();

    void addBehaviour(ActionFactory behaviour);

    Action executeBehaviours(GameMap map);
}
