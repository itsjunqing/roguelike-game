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
                "ooooooooo#ooooo",
                "ooooooooo#ooooo",
                "ooooooooo###+##",
                "ooooooooooooooo",
                "ooooooooooooooo",
                "ooooooooooooooo",
                "#+#############",
                "ooooooooooooooo",
                "oo=oooooooooooo",
                "ooooooooooooooo");
        GameMap moon = new GameMap(groundFactory, moonList);
        moonMap = moon;
        // adding moon map to the world
        world.addMap(moon);


        List<String> earthList = Arrays.asList(
                ".......................",
                "....#####....######....",
                "....#.=.#....#....#....",
                "#####...+....#....#....",
                "....#####....##+###....",
                "$......................",
                ".......................",
                "#####.#################",
                "***................#...",
                "***................+...",
                "***................#...");
        GameMap earth = new GameMap(groundFactory, earthList);
        earthMap = earth;
        world.addMap(earth);


        // Add to Earth
        earth.addItem(new RocketPlans("Rocket plans"), 21, 9);

        earth.addItem(new Spacesuit("NASA SpaceSuit"), 17, 2);

        Location rocketPadLocation = earth.at(6, 2);

        earth.add(new RocketPad(), rocketPadLocation);
        GamePlayer player = new GamePlayer("Player", 1, 200, rocketPadLocation);

        world.addPlayer(player, earth, 2, 2);

        Item bow = new WeaponItem("Abyss Bow", 'B', 20, "bows");
        earth.addItem(bow, 5, 2);

        Item katana = new WeaponItem("Wrench", 'W', 10, "whacks");
        earth.addItem(katana, 22, 9);


        Grunt grunt = new Grunt("Mongo");
        earth.addActor(grunt, 1, 4);

        Ninja ninja = new Ninja("Ninja");
        earth.addActor(ninja, 10, 5);

        DoctorMaybe drMaybe = new DoctorMaybe("Maybe");
        earth.addActor(drMaybe, 16, 2);

        Enemy necromancer = new Necromancer("Necromancer");
        earth.addActor(necromancer, 10, 9);

        Goon goon = new Goon("Goonie");
        earth.addActor(goon, 15, 9);

        Q q = new Q("Q");
        earth.addActor(q, 7, 9);

        // Add to Moon
        YugoMaxx Yugo = new YugoMaxx("Yugo Maxx");
        moon.addActor(Yugo, 6, 3);

        Goon goon2 = new Goon("Norbert");
        moon.addActor(goon2, 0, 0);

        Grunt grunt2 = new Grunt("Jonesy");
        moon.addActor(grunt2, 11, 4);

        Goon goon3 = new Goon("Tfue");
        moon.addActor(goon3, 14, 1);

        Item sword = new WeaponItem("Lightsaber", '/', 50, "slices");
        moon.addItem(sword, 10, 8);

        Item gauntlet = new WeaponItem("Infinity Gauntlet", '!', 100, "punches");
        moon.addItem(gauntlet, 14, 0);

        moon.addItem(new WaterPistol("Air Gun"), 10, 8);

        world.run();
    }

    public static GameMap getEarthMap() {
        return earthMap;
    }

    public static GameMap getMoonMap() {
        return moonMap;
    }
}
