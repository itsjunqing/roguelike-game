package game;

import edu.monash.fit2099.engine.Item;

public class Spacesuit extends Item {

    public static final char SPACESUIT_CHAR = 'P';

    public Spacesuit(String name) {
        super(name, SPACESUIT_CHAR);
    }
}
