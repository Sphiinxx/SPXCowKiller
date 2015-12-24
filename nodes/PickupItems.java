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
import scripts.SPXCowKiller.Main;
import scripts.SPXCowKiller.api.Node;

import java.util.ArrayList;

/**
 * Created by Sphiinx on 12/21/2015.
 */
public class PickupItems extends Node {

    private String[] pickupItems;
    private RSGroundItem[] groundItems;

    public PickupItems() {
        ArrayList<String> itemList = new ArrayList<String>();
        if (Main.buryBones) {
            System.out.println("Added bones");
            itemList.add("Bones");
        }
        if (Main.bankHides) {
            System.out.println("Added Cowhide");
            itemList.add("Cowhide");
        }
        pickupItems = new String[itemList.size()];
        pickupItems = itemList.toArray(pickupItems);
    }

    @Override
    public void execute() {
        Main.status = "Picking up...";
        groundItems = GroundItems.findNearest(pickupItems);
        if (groundItems.length > 0) {
            walkToItem();
        } else {
            pickupItems();
        }
    }

    private void walkToItem() {
        if (!groundItems[0].isOnScreen()) {
            if (Walking.walkTo(groundItems[0])) {
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        return groundItems[0].isOnScreen();
                    }
                }, General.random(750, 1000));
            }
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
                                return Player.isMoving();
                            }
                        }, General.random(750, 1000));
                    }
                }
            }
        }
    }

    @Override
    public boolean validate() {
        RSGroundItem[] groundItems = GroundItems.findNearest(pickupItems);

        return groundItems.length > 0 &&
                groundItems[0].getPosition().distanceTo(Player.getPosition()) <= 4 &&
                !Combat.isUnderAttack() &&
                !Inventory.isFull();
    }
}


