package scripts.SPXCowKiller.nodes;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.*;
import org.tribot.api2007.Combat;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSTile;
import scripts.SPXCowKiller.API.Game.Combat.Combat07;
import scripts.SPXCowKiller.Variables;
import scripts.SPXCowKiller.API.Framework.Node;


/**
 * Created by Sphiinx on 12/21/2015.
 */
public class KillCow extends Node {

    public KillCow(Variables v) {
        super(v);
    }

    @Override
    public void execute() {
        if (Player.getRSPlayer().isInCombat()) {
            Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    General.sleep(100);
                    return !Player.getRSPlayer().isInCombat();
                }
            }, General.random(1000, 1200));
        } else {
            attackCow();
        }
    }

    public void attackCow() {
        RSNPC cow = findCow();
        if (cow != null) {
            if (vars.currentLocation.contains(cow.getPosition())) {
                if (cow.isOnScreen()) {
                    if (Clicking.click("Attack", cow)) {
                        Timing.waitCondition(new Condition() {
                            @Override
                            public boolean active() {
                                return Combat.getAttackingEntities().length > 0;
                            }
                        }, General.random(1000, 1200));
                    }
                } else {
                    RSTile[] path = Walking.generateStraightScreenPath(cow.getPosition());
                    if (Walking.walkScreenPath(path)) {
                        Timing.waitCondition(new Condition() {
                            @Override
                            public boolean active() {
                                return cow.isOnScreen();
                            }
                        }, General.random(1000, 1200));
                    }
                }
            }
        }
    }

    public RSNPC findCow() {
        RSNPC[] cows = NPCs.findNearest(new Filter<RSNPC>() {
            @Override
            public boolean accept(RSNPC rsnpc) {
                String name = rsnpc.getName();
                if (name != null) {
                    return name.equals("Cow") && !rsnpc.isInCombat() && rsnpc.getInteractingCharacter() != Player.getRSPlayer();
                }
                return false;
            }
        });
        return cows.length > 0 ? cows[0] : null;
    }

    @Override
    public String toString() {
        return "Killing cow...";
    }

    public boolean validate() {
        if (vars.foodName.length() > 0) {
            return Inventory.getCount(vars.foodName) > 0 && !Combat07.isInCombat() && !Player.isMoving();
        }
        return !Combat07.isInCombat() && !Player.isMoving();
    }

}

