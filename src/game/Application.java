package game;

import edu.monash.fit2099.engine.*;

import java.util.Arrays;
import java.util.List;

/**
 * An Application to run the game.
 */
public class Application {

    private static GameMap earthMap;
    private static GameMap MoonMap;

    public static void main(String[] args) {
        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Floor(), new Wall(), new LockedDoor(),
                new RocketPad(), new Crater(), new OxygenDispenser(), new Water());
        GameMap gameMap;

//        List<String> earth = Arrays.asList(
//                ".......................",
//                "....#####....######....",
//                "....#.=.#....#....#....",
//                "#####...+....#....#....",
//                "....#####....##+###....",
//                ".......................",
//                ".......................",
//                "#####+#################",
//                "...................#...",
//                "...................+...",
//                "...................#...");

        List<String> earth = Arrays.asList(
                ".!.....................",
                "....#####....######....",
                "......=.#....#....#....",
                "........+....#....#....",
                "....#####.......###....",
                ".......................",
                ".......................",
                "#####.#################",
                "***................#...",
                "***................+...",
                "***................#...");
        gameMap = new GameMap(groundFactory, earth);
        earthMap = gameMap;
        gameMap.addItem(new RocketPlans("Rocket plans"), 1, 3);
        gameMap.addItem(new Spacesuit("Space suit"), 1, 4);
        Location padLocation = new Location(gameMap, 1, 2);
        Location dispenseLocation = new Location(gameMap, 1, 0);
        gameMap.add(new OxygenDispenser(), dispenseLocation);
        gameMap.add(new RocketPad(), padLocation);
        world.addMap(gameMap);

        gameMap.addItem(new WaterPistol(), 0, 4);
        YugoMaxx Yugo = new YugoMaxx("Yugo Maxx");
        world.addPlayer(Yugo, gameMap, 0, 5);

        List<String> moonMap = Arrays.asList(
                "ooooooooooooooo",
                "ooooooooooooooo",
                "ooooooooooooooo",
                "ooooooooooooooo",
                "ooooooo=ooooooo",
                "ooooooooooooooo",
                "ooooooooooooooo",
                "ooooooooooooooo",
                "ooooooooooooooo",
                "ooooooooooooooo");
        GameMap moon = new GameMap(groundFactory, moonMap);
        MoonMap = moon;
        world.addMap(moon);

        Actor player = new GamePlayer("Player", 1, 100);

        world.addPlayer(player, gameMap, 2, 2);

//        Grunt grunt = new Grunt("Mongo", player);
//        gameMap.addActor(grunt, 1, 5);

//        Grunt grunt2 = new Grunt("Norbert", player);
//        gameMap.addActor(grunt2, 20, 6);

//        Ninja ninja = new Ninja("Ninja", player);
//        gameMap.addActor(ninja, 10, 5);

        DoctorMaybe drMaybe = new DoctorMaybe("Maybe");
        gameMap.addActor(drMaybe, 0, 0);

//        Goon goon = new Goon("Goonie", player);
//        gameMap.addActor(goon, 17, 9);

        Q q = new Q("Q", player);
        gameMap.addActor(q, 0, 2);

        world.run();
    }

    public static GameMap getEarthMap(){
        return earthMap;
    }

    public static GameMap getMoonMap() {
        return MoonMap;
    }
}
