package scripts.SPXCowKiller.nodes;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSNPC;
import scripts.SPXCowKiller.api.Node;


/**
 * Created by Sphiinx on 12/21/2015.
 */
public class KillCow extends Node {


    @Override
    public void execute() {
        RSNPC[] cow = NPCs.findNearest("Cow");
        if (!cow[0].isOnScreen()) {
            if (Walking.walkTo(cow[0])) {
                Camera.turnToTile(cow[0]);
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        return cow[0].isOnScreen();
                    }
                }, General.random(750, 1000));
            }
        } else {
            if (cow.length > 0  && !cow[0].isInCombat()) {
                System.out.println("True");
                if (Clicking.click("Attack", cow[0])) {
                    Timing.waitCondition(new Condition() {
                        @Override
                        public boolean active() {
                            return Player.getRSPlayer().isInCombat();
                        }
                    }, General.random(750, 1000));
                }
            }
        }
    }

    @Override
    public boolean validate() {
        return !Player.getRSPlayer().isInCombat();
    }

}

