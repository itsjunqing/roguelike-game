package game;

import edu.monash.fit2099.engine.*;
import game.actor.*;
import game.ground.*;
import game.item.RocketPlans;
import game.item.Spacesuit;
import game.item.WaterPistol;

import java.util.Arrays;
import java.util.List;

/**
 * An Application to run the game.
 */
public class Application {

    private static GameMap earthMap;
    private static GameMap moonMap;

    public static void main(String[] args) {
        GameWorld world = new GameWorld(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Floor(), new Wall(), new LockedDoor(),
                new RocketPad(), new Crater(), new Water(), new OxygenDispenserG());

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

        List<String> moonList = Arrays.asList(
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
        GameMap moon = new GameMap(groundFactory, moonList);
        moonMap = moon;
        // adding moon map to the world
        world.addMap(moon);


        List<String> earthList = Arrays.asList(
                "...$...................",
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
        GameMap earth = new GameMap(groundFactory, earthList);
        earthMap = earth;
        world.addMap(earth);


        // adding items and grounds onto earth map
        earth.addItem(new RocketPlans("Rocket plans"), 1, 3);
        earth.addItem(new Spacesuit(),1, 4);
//        earth.add(new RocketPad(moon.at(7,4)), new Location(earth, 1, 2));
        Location rocketPadLocation = new Location(earth, 6, 2);
        earth.add(new RocketPad(), earth.at(6,2));
        earth.add(new OxygenDispenserG(), new Location(earth, 3, 0));

        earth.addItem(new WaterPistol("Air Gun"), 0, 4);

        // earth.at(6,2) cannot be replaced with rocketPadLocation, the list of exits will be empty, because exits are created via the map
        GamePlayer player = new GamePlayer("Player", 1, 100, earth.at(6, 2));
        world.addPlayer(player, earth, 2, 2);

//        OxygenDispenser dispenser = new OxygenDispenser("SpaceX O2 Dispenser", player);
//        earth.addActor(dispenser, 1, 0);

//        Grunt grunt = new Grunt("Mongo", player);
//        earth.addActor(grunt, 1, 5);

//        Grunt grunt2 = new Grunt("Norbert", player);
//        gameMap.addActor(grunt2, 20, 6);

//        Ninja ninja = new Ninja("Ninja", player);
//        gameMap.addActor(ninja, 10, 5);

        DoctorMaybe drMaybe = new DoctorMaybe("Maybe");
        earth.addActor(drMaybe, 0, 0);

//        Goon goon = new Goon("Goonie", player);
//        gameMap.addActor(goon, 17, 9);


//        YugoMaxx Yugo = new YugoMaxx("Yugo Maxx", player);
//        earth.addActor(Yugo, 0, 5);

        Q q = new Q("Q", player);
        earth.addActor(q, 0, 2);

        world.run();
    }

    public static GameMap getEarthMap(){
        return earthMap;
    }

    public static GameMap getMoonMap() {
        return moonMap;
    }
}
