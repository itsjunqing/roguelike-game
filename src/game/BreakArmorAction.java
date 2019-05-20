package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class BreakArmorAction extends Action {
    YugoMaxx maxx;

    public BreakArmorAction(YugoMaxx boss){
        maxx = boss;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        WaterPistol.usePistol(false);
        for (Item item : maxx.getInventory()){
            if (item.hasSkill(MoonSkills.INVULNERABLE)){
                maxx.removeItemFromInventory(item);
            }
        }
        return actor + " shoots " + maxx + " with the Water Pistol";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Shoot " + maxx + " with the Water Pistol";
    }

    @Override
    public String hotKey() {
        return "";
    }
}
