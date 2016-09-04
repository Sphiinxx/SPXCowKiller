package scripts.spxcowkiller.tasks;

import org.tribot.api.General;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSTile;
import scripts.spxcowkiller.data.Vars;
import scripts.task_framework.framework.Task;
import scripts.task_framework.framework.TaskManager;
import scripts.tribotapi.game.combat.Combat07;
import scripts.tribotapi.util.Logging;

/**
 * Created by Sphiinx on 7/11/2016.
 */
public class OutOfAmmo implements Task {

    private final RSTile SAFE_ZONE = new RSTile(3224, 3219, 0);

    @Override
    public boolean validate() {
        return Vars.get().out_of_ammo;
    }

    @Override
    public void execute() {
        if (Combat07.isInCombat())
            WebWalking.walkTo(SAFE_ZONE, new Condition() {
                @Override
                public boolean active() {
                    General.sleep(100);
                    return !Combat07.isInCombat();
                }
            }, General.random(250, 500));

        Logging.warning("We are out of arrows/bolts or runes.");
        TaskManager.stopProgram(true);
    }

    @Override
    public String toString() {
        return "Traveling to safe area";
    }
}

