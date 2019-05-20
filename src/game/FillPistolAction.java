package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class FillPistolAction extends Action {
    private Item pistol;

    public FillPistolAction(Item item){
        pistol = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        WaterPistol.usePistol(true);
        return actor + " filled the Water Pistol." ;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " fills the Water Pistol";
    }

    @Override
    public String hotKey() {
        return "";
    }
}
