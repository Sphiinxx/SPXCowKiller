package scripts.SPXCowKiller.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.*;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSGroundItem;
import scripts.SPXCowKiller.API.Game.GroundItems.GroundItems07;
import scripts.SPXCowKiller.data.Variables;
import scripts.SPXCowKiller.API.Framework.Task;

import java.util.ArrayList;

/**
 * Created by Sphiinx on 12/21/2015.
 */
public class PickupItems extends Task {

    private String[] pickupItems;
    private RSGroundItem[] groundItems;

    public PickupItems(Variables v) {
        super(v);
        ArrayList<String> itemList = new ArrayList<>();
        if (vars.buryBones) {
            System.out.println("Added bones");
            itemList.add("Bones");
        }
        if (vars.bankHides) {
            System.out.println("Added Cowhide");
            itemList.add("Cowhide");
        }
        pickupItems = new String[itemList.size()];
        pickupItems = itemList.toArray(pickupItems);
    }

    @Override
    public void execute() {
        if (GroundItems07.pickUpGroundItem(groundItems)) {
            Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    return Player.isMoving();
                }
            }, General.random(1000, 1200));
        }
    }

    @Override
    public String toString(){
        return "Picking up items...";
    }

    @Override
    public boolean validate() {
        groundItems = GroundItems.findNearest(pickupItems);
        return !Player.getRSPlayer().isInCombat() && groundItems.length > 0 && groundItems[0].getPosition().distanceTo(Player.getPosition()) <= 4 && !Inventory.isFull();
    }
}


