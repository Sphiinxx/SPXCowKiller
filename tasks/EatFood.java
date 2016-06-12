package scripts.SPXCowKiller.tasks;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Combat;
import org.tribot.api2007.Inventory;

import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSItem;
import scripts.SPXCowKiller.data.Vars;
import scripts.SPXCowKiller.framework.Task;

/**
 * Created by Sphiinx on 1/10/2016.
 */
public class EatFood implements Task {

    public void execute() {
        RSItem[] food = Inventory.find(Vars.get().foodName);
        if (Clicking.click("Eat", food[0])) {
            Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    General.sleep(100);
                    return Player.getAnimation() == -1;
                }
            }, General.random(750, 1000));
        }
    }

    public String toString() {
        return "Eating food...";
    }

    public boolean validate() {
        return Vars.get().foodName.length() > 0 && Inventory.getCount(Vars.get().foodName) > 0 && Combat.getHPRatio() < Vars.get().eatPercentage;
    }

}

