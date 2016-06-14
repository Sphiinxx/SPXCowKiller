package scripts.SPXCowKiller.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.*;
import scripts.SPXCowKiller.data.Constants;
import scripts.SPXCowKiller.data.Vars;
import scripts.SPXCowKiller.framework.Task;
import TribotAPI.game.combat.Combat07;
import TribotAPI.game.game.Game07;

/**
 * Created by Sphiinx on 1/20/2016.
 */
public class EmptyQuiver implements Task {

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
                Vars.get().stopScript = true;
            }
        }
    }

    public String toString(){
        return "Logging out...";
    }

    public boolean validate() {
        return Vars.get().outOfArrows;
    }
}

