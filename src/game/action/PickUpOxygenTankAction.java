package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actor.GamePlayer;
import game.item.OxygenTank;

public class PickUpOxygenTankAction extends Action {

    private OxygenTank oxygenTank;
    private GamePlayer player;

    public PickUpOxygenTankAction(OxygenTank oxygenTank, GamePlayer player) {
        this.oxygenTank = oxygenTank;
        this.player = player;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(oxygenTank);
        player.addTank(oxygenTank);
        oxygenTank.getAllowableActions().remove(this);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up the " + oxygenTank;
    }

    @Override
    public String hotKey() {
        return "";
    }
}
