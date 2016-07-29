package scripts.SPXCowKiller.tasks;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSCharacter;
import org.tribot.api2007.types.RSNPC;
import scripts.SPXCowKiller.data.Vars;
import scripts.TaskFramework.framework.Task;
import scripts.TribotAPI.game.combat.Combat07;
import scripts.TribotAPI.game.npcs.NPCs07;
import scripts.TribotAPI.game.timing.Timing07;
import scripts.TribotAPI.game.walking.Walking07;

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

