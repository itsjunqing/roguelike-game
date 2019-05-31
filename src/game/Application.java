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
                new RocketPad(), new Crater(), new Water(), new OxygenDispenser());

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


        // Add to Earth
        earth.addItem(new RocketPlans("Rocket plans"), 1, 3);

        earth.addItem(new Spacesuit("NASA SpaceSuit"), 1, 4);

        earth.addItem(new WaterPistol("Air Gun"), 0, 4);

        Location rocketPadLocation = earth.at(6, 2);

        earth.add(new RocketPad(), rocketPadLocation);
        GamePlayer player = new GamePlayer("Player", 1, 100, rocketPadLocation);

        world.addPlayer(player, earth, 2, 2);

//        Item bow = new WeaponItem("Abyss Bow", 'B', 10, "bows");
//        earth.addItem(bow, 1, 2);

//        Item sword = new WeaponItem("Infinity Blade", '/', 20, "slices");
//        earth.addItem(sword, 1, 1);

        Item gun = new WeaponItem("Revolver", '!', 50, "shoots");
        earth.addItem(gun, 2, 1);

//        Item katana = new WeaponItem("Katana Blade", 'W', 50, "double-edged attack");
//        earth.addItem(katana, 1, 3);


        Grunt grunt = new Grunt("Mongo");
        earth.addActor(grunt, 1, 5);

//        Grunt grunt2 = new Grunt("Norbert");
//        moon.addActor(grunt2, 3, 6);

        Ninja ninja = new Ninja("Ninja");
        earth.addActor(ninja, 10, 5);

//        DoctorMaybe drMaybe = new DoctorMaybe("Maybe");
//        earth.addActor(drMaybe, 0, 0);

//        Enemy necromancer = new Necromancer("Necromancer");
//        earth.addActor(necromancer, 1, 3);
//        Goon goon = new Goon("Goonie");
//        earth.addActor(goon, 17, 9);

        Q q = new Q("Q");
        earth.addActor(q, 0, 2);

        // Add to Moon
        YugoMaxx Yugo = new YugoMaxx("Yugo Maxx");
        moon.addActor(Yugo, 0, 5);

        world.run();
    }

    public static GameMap getEarthMap() {
        return earthMap;
    }

    public static GameMap getMoonMap() {
        return moonMap;
    }
}
