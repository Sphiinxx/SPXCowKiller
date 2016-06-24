package scripts.SPXCowKiller.tasks;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.*;
import org.tribot.api2007.Combat;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSTile;
import scripts.SPXCowKiller.data.Vars;
import scripts.SPXCowKiller.framework.Task;
import scripts.TribotAPI.game.combat.Combat07;


/**
 * Created by Sphiinx on 12/21/2015.
 */
public class KillCow implements Task {

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
            if (Vars.get().area.contains(cow.getPosition())) {
                if (cow.isOnScreen()) {
                    if (Clicking.click("Attack", cow)) {
                        Timing.waitCondition(new Condition() {
                            @Override
                            public boolean active() {
                                return Combat.getAttackingEntities().length > 0;
                            }
                        }, General.random(1000, 1200));
                        Vars.get().cowsKilled++;
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
                    return (name.equals("Cow") || name.equals("Cow calf")) && !rsnpc.isInCombat() && rsnpc.getInteractingCharacter() != Player.getRSPlayer();
                }
                return false;
            }
        });
        return cows.length > 0 ? cows[0] : null;
    }

    public String toString() {
        return "Killing cow...";
    }

    public boolean validate() {
        if (Vars.get().foodName.length() > 0) {
            return Inventory.getCount(Vars.get().foodName) > 0 && !Combat07.isInCombat() && !Player.isMoving();
        }
        return !Combat07.isInCombat() && !Player.isMoving();
    }

}

