package scripts.SPXCowKiller.nodes;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.*;
import scripts.SPXCowKiller.API.Framework.Node;
import scripts.SPXCowKiller.API.Game.Combat.Combat07;
import scripts.SPXCowKiller.API.Game.Game.Game07;
import scripts.SPXCowKiller.data.Constants;
import scripts.SPXCowKiller.data.Variables;

/**
 * Created by Sphiinx on 1/20/2016.
 */
public class EmptyQuiver extends Node{

    public EmptyQuiver(Variables v) {
        super(v);
    }

    @Override
    public void execute() {
        if (Combat07.isInCombat()) {
            WebWalking.walkTo(Constants.SAFE_ZONE, new Condition() {
                @Override
                public boolean active() {
                    General.sleep(100);
                    return !Combat07.isInCombat();
                }
            }, General.random(100, 150));
        } else {
            if (Login.logout()) {
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        General.sleep(100);
                        return Game07.isAtLoginScreen();
                    }
                }, General.random(750, 1000));
                General.println("We're out of arrows...");
                General.println("Stopping script...");
                vars.stopScript = true;
            }
        }
    }

    @Override
    public String toString(){
        return "Logging out...";
    }

    public boolean validate() {
        return vars.outOfArrows;
    }
}

