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

    public PickupItems() {
        ArrayList<String> itemList = new ArrayList<String>();
        if (Variables.buryBones) {
            System.out.println("Added bones");
            itemList.add("Bones");
        }
        if (Variables.bankHides) {
            System.out.println("Added Cowhide");
            itemList.add("Cowhide");
        }

        Variables.pickupItems = new String[itemList.size()];
        Variables.pickupItems = itemList.toArray(Variables.pickupItems);
    }

    @Override
    public void execute() {
        Variables.STATUS = "Picking up...";
        RSGroundItem[] groundItems = GroundItems.findNearest(Variables.pickupItems);
        if (groundItems.length > 0) {
            if (!groundItems[0].isOnScreen()) {
                if (Walking.walkTo(groundItems[0])) {
                    Timing.waitCondition(new Condition() {
                        @Override
                        public boolean active() {
                            return groundItems[0].isOnScreen();
                        }
                    }, General.random(750, 1000));
                }
            } else {
                Camera.turnToTile(groundItems[0]);
                if (!Player.isMoving()) {
                    RSItemDefinition definition = groundItems[0].getDefinition();
                    if(definition != null){
                        String name = definition.getName();
                        if(name != null){
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
        }
    }

    @Override
    public boolean validate() {
        RSGroundItem[] groundItems = GroundItems.findNearest(Variables.pickupItems);

        return groundItems.length > 0 &&
                groundItems[0].getPosition().distanceTo(Player.getPosition()) <= 4 &&
                !Combat.isUnderAttack() &&
                !Inventory.isFull();
    }
}


