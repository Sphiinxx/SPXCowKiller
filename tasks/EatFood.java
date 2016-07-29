package scripts.SPXCowKiller.tasks;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api2007.Combat;
import org.tribot.api2007.types.RSItem;
import scripts.SPXCowKiller.data.Vars;
import scripts.TaskFramework.framework.Task;
import scripts.TribotAPI.game.inventory.Inventory07;
import scripts.TribotAPI.game.timing.Timing07;

/**
 * Created by Sphiinx on 7/11/2016.
 */
public class EatFood implements Task {


    @Override
    public boolean validate() {
        RSItem item_to_eat = Inventory07.getItem(Vars.get().food_name);
        return item_to_eat != null && Combat.getHPRatio() <= General.random(45, 55);
    }

    @Override
    public void execute() {
        RSItem item_to_eat = Inventory07.getItem(Vars.get().food_name);
        if (item_to_eat == null)
            return;

        final int hp_ratio_cache = Combat.getHPRatio();
        if (Clicking.click("Eat", item_to_eat))
            Timing07.waitCondition(() -> hp_ratio_cache != Combat.getHPRatio(), General.random(1500, 2000));
    }

    @Override
    public String toString() {
        return "Eating food";
    }
}

