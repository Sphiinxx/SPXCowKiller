package scripts.SPXCowKiller.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.*;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSGroundItem;
import scripts.SPXCowKiller.data.Vars;
import scripts.SPXCowKiller.framework.Task;
import scripts.TribotAPI.game.grounditems.GroundItems07;

import java.util.ArrayList;

/**
 * Created by Sphiinx on 12/21/2015.
 */
public class PickupItems implements Task {

    private String[] pickupItems;
    private RSGroundItem[] groundItems;

    public PickupItems() {
        ArrayList<String> itemList = new ArrayList<>();
        if (Vars.get().buryBones) {
            itemList.add("Bones");
        }
        if (Vars.get().bankHides) {
            itemList.add("Cowhide");
        }
        pickupItems = new String[itemList.size()];
        pickupItems = itemList.toArray(pickupItems);
    }

    @Override
    public void execute() {
        for (RSGroundItem item : groundItems) {
            if (GroundItems07.pickUpGroundItem(item)) {
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        return Player.isMoving();
                    }
                }, General.random(1000, 1200));
            }
        }
    }

    @Override
    public String toString() {
        return "Picking up items...";
    }

    @Override
    public boolean validate() {
        groundItems = GroundItems.findNearest(pickupItems);
        return !Player.getRSPlayer().isInCombat() && groundItems.length > 0 && groundItems[0].getPosition().distanceTo(Player.getPosition()) <= 4 && !Inventory.isFull();
    }
}


