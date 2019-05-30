//package game.item;
//
//import edu.monash.fit2099.engine.WeaponItem;
//import game.action.PickUpWeaponAction;
//
//public class Bow extends WeaponItem {
//
//    public static final char BOW_CHAR = 'B';
//
//    public Bow(String name, int damage) {
//        super(name, BOW_CHAR, damage, "shoots arrow");
//        allowableActions.clear();
//        allowableActions.add(new PickUpWeaponAction(this));
//    }
//
//    @Override
//    public Actions getAllowableActions() {
//        allowableActions = super.getAllowableActions();
//        GamePlayer gamePlayer = GameWorld.getGamePlayer();
//
//        if (gamePlayer.getInventory().contains(this)) {
//            allowableActions.add(new DeselectWeaponAction(this));
//        }
//        return allowableActions;
//    }
//}
