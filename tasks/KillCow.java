package scripts.spxcowkiller.tasks;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSNPC;
import scripts.spxcowkiller.data.Vars;
import scripts.task_framework.framework.Task;
import scripts.tribotapi.game.combat.Combat07;
import scripts.tribotapi.game.npcs.NPCs07;
import scripts.tribotapi.game.timing.Timing07;
import scripts.tribotapi.game.walking.Walking07;

/**
 * Created by Sphiinx on 7/11/2016.
 */
public class KillCow implements Task {

    @Override
    public boolean validate() {
        RSNPC cow = NPCs07.getNPC("Cow", "Cow calf");
        return cow != null && !Combat07.isInCombat() && !Vars.get().should_pickup_item;
    }

    @Override
    public void execute() {
        if (Player.getAnimation() != -1)
            return;

        RSNPC cow = NPCs07.getNPC("Cow", "Cow calf");
        if (cow == null)
            return;

        if (!cow.isOnScreen())
            Walking07.screenWalkToRSNPC(cow);

        if (cow.isInCombat())
            return;

        if (Clicking.click("Attack", cow)) {
            if (Timing07.waitCondition(Combat07::isInCombat, General.random(2000, 2500))) {
                Vars.get().cows_killed++;
                if (Vars.get().bank_hides || Vars.get().bury_bones)
                    Vars.get().should_pickup_item = true;
            }
        }
    }

    @Override
    public String toString() {
        return "Going to kill cow";
    }

}

