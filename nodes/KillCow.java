package scripts.SPXCowKiller.nodes;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.*;
import org.tribot.api2007.Combat;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSNPC;
import scripts.SPXCowKiller.Variables;
import scripts.SPXCowKiller.api.Node;


/**
 * Created by Sphiinx on 12/21/2015.
 */
public class KillCow extends Node {

    public KillCow(Variables v) {
        super(v);
    }

    @Override
    public void execute() {
        RSNPC[] cows = NPCs.findNearest("Cow");
        for (final RSNPC cow : cows) {
            if (!cow.isInCombat()) {
                if (!cow.isOnScreen()) {
                    if (Walking.walkTo(cow)) {
                        Timing.waitCondition(new Condition() {
                            @Override
                            public boolean active() {
                                General.sleep(100);
                                return cow.isOnScreen();
                            }
                        }, General.random(1200, 1500));
                    }
                } else {
                    if (Clicking.click("Attack", cow)) {
                        Timing.waitCondition(new Condition() {
                            @Override
                            public boolean active() {
                                General.sleep(100);
                                return Combat.getAttackingEntities().length > 0;
                            }
                        }, General.random(1000, 1200));
                    }
                    break;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Killing cow...";
    }

    @Override
    public boolean validate() {
        RSNPC[] cows = NPCs.findNearest("Cow");
        for(final RSNPC cow : cows)
        {
            if(cow.isInteractingWithMe() && (cow.getHealth() == 0 || !cow.isValid()))
                return true;
        }
        return !Player.getRSPlayer().isInCombat() && !Player.isMoving() && Combat.getAttackingEntities().length == 0;
    }

}

