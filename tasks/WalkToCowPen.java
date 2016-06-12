package scripts.SPXCowKiller.tasks;

import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;
import scripts.SPXCowKiller.data.Vars;
import scripts.SPXCowKiller.framework.Task;

/**
 * Created by Sphiinx on 12/21/2015.
 */
public class WalkToCowPen implements Task {

    @Override
    public void execute() {
        WebWalking.walkTo(Vars.get().area.getRandomTile());
    }

    @Override
    public String toString() {
        return "Walking to cows...";
    }

    @Override
    public boolean validate() {
        if (Vars.get().foodName.length() > 0) {
            return Inventory.getCount(Vars.get().foodName) > 0 && !Vars.get().area.contains(Player.getPosition());
        }
       return !Vars.get().area.contains(Player.getPosition());
    }

}

