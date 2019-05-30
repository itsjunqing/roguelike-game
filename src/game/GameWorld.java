package game;

import edu.monash.fit2099.engine.*;
import game.actor.GamePlayer;

public class GameWorld extends World {

    private static GamePlayer gamePlayer;

    // use this to access the map instead of accessing via the Application
    public GameWorld(Display display) {
        super(display);
    }

    @Override
    protected void processActorTurn(Actor actor) {
        Location here = actorLocations.locationOf(actor);
        GameMap map = here.map();

        Actions actions = new Actions();
        for (Item item : actor.getInventory()) {
            actions.add(item.getAllowableActions());
        }

        for (WeaponItem weaponItem : gamePlayer.getWeaponItems()) {
            actions.add(weaponItem.getAllowableActions());
        }

        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (actorLocations.isAnActorAt(destination)) {
                Actor adjacentActor = actorLocations.actorAt(destination);
                actions.add(adjacentActor.getAllowableActions(actor, exit.getName(), map));
            } else {
                Ground adjacentGround = map.groundAt(destination);
                actions.add(adjacentGround.allowableActions(actor, destination, exit.getName()));
                actions.add(adjacentGround.getMoveAction(actor, destination, exit.getName(), exit.getHotKey()));
            }
        }

        for (Item item : here.getItems()) {
            actions.add(item.getAllowableActions());
        }
        actions.add(new SkipTurnAction());

        Action action = actor.playTurn(actions, map, display);
        String result = action.execute(actor, map);
        display.println(result);
    }

    @Override
    public void run() {
        if (player == null)
            throw new IllegalStateException();
        boolean resume;
        boolean win = false;
        while (stillRunning()) {
            GameMap playersMap = actorLocations.locationOf(player).map();
            playersMap.draw(display);
            for (Actor actor : actorLocations) {
                resume = false;
                if (actorLocations.contains(player)) {
//                    for (Item item : actorLocations.locationOf(player).getItems()){
//                    Checks if Player has dropped the YugoMaxx 's dead body
//                        if (item.hasSkill(GameSkills.SPACEBOSSPOWER) && playersMap.equals(Application.getEarthMap())){
//                            win = true;
//                            break;
//                        }
                    if (player.hasSkill(GameSkills.SPACEBOSSPOWER) && playersMap == Application.getEarthMap()) {
                        win = true;
                    }

                    if (!win) {
                        resume = true;
                    }
                }

                if (resume) {
                    processActorTurn(actor);
                } else if (win) {
                    display.println(playerWin());
                    actorLocations.remove(player);
                    break;
                } else {
                    display.println(playerLose());
                    break;
                }
            }
        }
        display.println(endGameMessage());
    }

    public String playerLose() {
        return "You lose.";
    }

    public void addPlayer(GamePlayer gamePlayer, GameMap map, int y, int x) {
        super.addPlayer(gamePlayer, map, y, x);
        GameWorld.gamePlayer = gamePlayer;
    }

    public static GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public String playerWin() {
        return "You win.";
    }
}
