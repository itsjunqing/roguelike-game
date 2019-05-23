package game.item;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.MoonSkills;

public class ReturnScroll extends Item {

    private Location returnLocation;

    // special item to keep track of teleporting player to the location
    public ReturnScroll(String name, Location returnLocation) {
        super(name, 'R');
        this.returnLocation = returnLocation;
        addSkill(MoonSkills.TELEPORTSKILL);


    }

    public Location getReturnLocation() {
        return returnLocation;
    }
}
