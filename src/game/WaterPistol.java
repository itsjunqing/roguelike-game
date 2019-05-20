package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Skills;

public class WaterPistol extends Item {
    private static boolean filled = false;

    public WaterPistol(){
        super("Water Pistol", 'P');
        addSkill(MoonSkills.WATERSKILL);
    };

    public static void usePistol(boolean filler){
        filled = filler;
    }

}
