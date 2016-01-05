package scripts.SPXCowKiller.nodes;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.*;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Combat;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSGroundItem;
import org.tribot.api2007.types.RSItemDefinition;
import scripts.SPXCowKiller.Variables;
import scripts.SPXCowKiller.api.Node;

import java.util.ArrayList;

/**
 * Created by Sphiinx on 12/21/2015.
 */
public class PickupItems extends Node {

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
        if (!groundItems[0].isOnScreen()) {
            walkToItem();
        } else {
            pickupItems();
        }
    }

    private void walkToItem() {
        if (Walking.walkTo(groundItems[0])) {
            Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    General.sleep(100);
                    return groundItems[0].isOnScreen();
                }
            }, General.random(750, 1000));

        }
    }

    private void pickupItems() {
        Camera.turnToTile(groundItems[0]);
        if (!Player.isMoving()) {
            RSItemDefinition definition = groundItems[0].getDefinition();
            if (definition != null) {
                String name = definition.getName();
                if (name != null) {
                    if (groundItems[0].click("Take " + name)) {
                        Timing.waitCondition(new Condition() {
                            @Override
                            public boolean active() {
                                General.sleep(100);
                                return Player.isMoving();
                            }
                        }, General.random(750, 1000));
                    }
                }
            }
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


