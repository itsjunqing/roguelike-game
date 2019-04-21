package game;

import edu.monash.fit2099.engine.*;

import java.util.Arrays;
import java.util.List;

public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Floor(), new Wall(), new LockedDoor(), new RocketPad());
		GameMap gameMap;

		List<String> map = Arrays.asList(
				".......................",
				"....#####....######....",
				"....#...#....#....#....",
				"....#...+....#....#....",
				"...=#####....##.###....",
				".......................",
				".......................",
				".......................",
				".......................",
				".......................",
				".......................");
		gameMap = new GameMap(groundFactory, map);
		gameMap.addItem(new RocketPlans("Rocket plans"), 1, 2);
//		gameMap.addItem(new RocketEngine("Rocket engine"), 1, 3);
//		Location padLocation = new Location(gameMap, 5, 5);
//		gameMap.add(new RocketPad(), padLocation);
		world.addMap(gameMap);
		
		Actor player = new GamePlayer("Player", '@', 1, 100);

		world.addPlayer(player, gameMap, 2, 2);

		
		Grunt grunt = new Grunt("Mongo", player);
		gameMap.addActor(grunt, 0, 0);

//        Ninja ninja = new Ninja("Ninja", player);
//        gameMap.addActor(ninja, 2, 0);

//		Grunt grunt2 = new Grunt("Norbert", player);
//		gameMap.addActor(grunt2,  3, 3);

		DoctorMaybe drMaybe = new DoctorMaybe("Maybe");
		gameMap.addActor(drMaybe, 6, 2);

		Goon goon = new Goon("Goonie", player);
		gameMap.addActor(goon, 3, 3);

		Q q = new Q("Q");
		gameMap.addActor(q, 2, 6);

		world.run();
	}
}
