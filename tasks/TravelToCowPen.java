package scripts.SPXCowKiller.tasks;

import org.tribot.api.General;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSNPC;
import scripts.SPXCowKiller.data.Vars;
import scripts.TaskFramework.framework.Task;
import scripts.TribotAPI.game.npcs.NPCs07;

/**
 * Created by Sphiinx on 7/11/2016.
 */
public class TravelToCowPen implements Task {

    @Override
    public boolean validate() {
        final RSNPC cow = NPCs07.getNPC("Cow", "Cow calf");
        return cow == null || Player.getPosition().distanceTo(Vars.get().location.getTile()) > 30;
    }

    @Override
    public void execute() {
        WebWalking.walkTo(Vars.get().location.getTile(), new Condition() {
            @Override
            public boolean active() {
                General.sleep(100);
                final RSNPC cow = NPCs07.getNPC("Cow", "Cow calf");
                return cow != null && cow.isOnScreen();
            }
        }, General.random(250, 500));
    }

    @Override
    public String toString() {
        return "Traveling to cow pen";
    }
}

