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
				"....#.=.#....#....#....",
				"#####...+....#....#....",
				"....#####....##+###....",
				".......................",
				".......................",
				"#####+#################",
				".......................",
				".......................",
				".......................");
		gameMap = new GameMap(groundFactory, map);
		gameMap.addItem(new RocketPlans("Rocket plans"), 1, 4);
		Location padLocation = new Location(gameMap, 6, 2);
		gameMap.add(new RocketPad(), padLocation);
		world.addMap(gameMap);
		
		Actor player = new GamePlayer("Player", '@', 1, 100);

		world.addPlayer(player, gameMap, 2, 2);

		
		Grunt grunt = new Grunt("Mongo", player);
		gameMap.addActor(grunt, 1, 5);

        Ninja ninja = new Ninja("Ninja", player);
        gameMap.addActor(ninja, 10, 6);

		Grunt grunt2 = new Grunt("Norbert", player);
		gameMap.addActor(grunt2,  2, 9);

		DoctorMaybe drMaybe = new DoctorMaybe("Maybe");
		gameMap.addActor(drMaybe, 16, 2);

		Goon goon = new Goon("Goonie", player);
		gameMap.addActor(goon, 20, 9);

		Q q = new Q("Q");
		gameMap.addActor(q, 14, 9);

		Wizard wizard = new Wizard("Gandalf");
		gameMap.addActor(wizard, 4, 6);

		world.run();
	}
}
