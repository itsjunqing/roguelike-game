package edu.monash.fit2099.demo;

import edu.monash.fit2099.engine.*;
import game.FollowBehaviour;

import java.util.Arrays;
import java.util.List;


public class Application {

    public static void main(String[] args) {
        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Floor(), new Wall(), new LockedDoor(),
                new Crater());
        GameMap gameMap;

        List<String> map = Arrays.asList(
                ".............",
                "......######.",
                "......+....+.",
                "......######.",
                ".............");
        gameMap = new GameMap(groundFactory, map);
        world.addMap(gameMap);

        List<String> marsMap = Arrays.asList(
                "ooooooooooooo",
                "oooooooo...oo",
                "oooooo....ooo",
                "oooooooo..ooo",
                "ooo..oooooooo",
                "ooooooooooooo");
        GameMap mars = new GameMap(groundFactory, marsMap);
        world.addMap(mars);

        Item rocket = Item.newFurniture("Rocket", '^');
        rocket.getAllowableActions().add(new MoveActorAction(mars.at(7, 2), "to Mars!"));
        gameMap.addItem(rocket, 1, 1);

        Item spaceSuit = new Item("space suit", '[');
        spaceSuit.addSkill(DemoSkills.SPACETRAVELLER);
        gameMap.addItem(spaceSuit, 0, 1);

        Item stick = new WeaponItem("stick", '/', 10, "pokes");
        gameMap.addItem(stick, 8, 2);

        Actor player = new Player("The Player", '@', 1, 100);
        world.addPlayer(player, gameMap, 3, 2);

        Bug bug = new Bug();
        bug.addItemToInventory(Item.newInventoryItem("rock", '*'));
        bug.actionFactories.add(new SpitBehaviour(player));
        bug.actionFactories.add(new FollowBehaviour(player));
        gameMap.addActor(bug, 0, 2);

        world.run();
    }

}
