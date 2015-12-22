package scripts.SPXCowKiller.nodes;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.*;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSGroundItem;
import scripts.SPXCowKiller.api.Node;

/**
 * Created by Sphiinx on 12/21/2015.
 */
public class PickupItems extends Node {

    private final String[] ITEM_IDS = new String[]{
            "Bones"
    };

    @Override
    public void execute() {
        RSGroundItem[] groundItems = GroundItems.findNearest(ITEM_IDS);
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
                    if (Clicking.click("Take", groundItems[0])) {
                        Timing.waitCondition(new Condition() {
                            @Override
                            public boolean active() {
                                return !Player.isMoving();
                            }
                        }, General.random(750, 1000));
                    }
                }
            }
        }
    }

    @Override
    public boolean validate() {
        RSGroundItem[] groundItems = GroundItems.findNearest(ITEM_IDS);

        return groundItems.length > 0 &&
                groundItems[0].getPosition().distanceTo(Player.getPosition()) <= 5 &&
                !Player.getRSPlayer().isInCombat();
    }
}


