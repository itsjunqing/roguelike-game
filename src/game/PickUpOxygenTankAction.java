package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class PickUpOxygenTankAction extends Action {

    private OxygenTank oxygenTank;

    public PickUpOxygenTankAction(OxygenTank oxygenTank) {
        this.oxygenTank = oxygenTank;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(oxygenTank);
        GamePlayer.addTank(oxygenTank);
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
