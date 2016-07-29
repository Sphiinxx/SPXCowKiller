package scripts.SPXCowKiller.tasks;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSItem;
import scripts.SPXCowKiller.data.Vars;
import scripts.TaskFramework.framework.Task;
import scripts.TribotAPI.game.combat.Combat07;
import scripts.TribotAPI.game.inventory.Inventory07;
import scripts.TribotAPI.game.timing.Timing07;

/**
 * Created by Sphiinx on 7/11/2016.
 */
public class BuryBones implements Task {


    @Override
    public boolean validate() {
        return Vars.get().bury_bones && !Combat07.isInCombat() && Inventory.getCount("Bones") > General.random(0, 15);
    }

    @Override
    public void execute() {
        RSItem item_to_bury = Inventory07.getItem("Bones");
        if (item_to_bury == null)
            return;

        if (Clicking.click("Bury", item_to_bury))
            if (Timing07.waitCondition(() -> Player.getAnimation() != -1, General.random(1500, 2000)))
                Vars.get().bones_buried++;
    }

    @Override
    public String toString() {
        return "Burying bones";
    }

}

