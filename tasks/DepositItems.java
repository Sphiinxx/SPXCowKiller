package scripts.SPXCowKiller.tasks;

import org.tribot.api.General;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSItem;
import scripts.SPXCowKiller.data.Vars;
import scripts.TaskFramework.framework.Task;
import scripts.TribotAPI.game.banking.Banking07;
import scripts.TribotAPI.game.timing.Timing07;

/**
 * Created by Sphiinx on 7/11/2016.
 */
public class DepositItems implements Task {


    @Override
    public boolean validate() {
        return Inventory.isFull() && Inventory.getCount(Vars.get().food_name) <= 0;
    }

    @Override
    public void execute() {
        if (Banking07.isBankItemsLoaded()) {
            RSItem[] inventory_cache = Inventory.getAll();
            if (Banking.deposit(0, "Cowhide"))
                Timing07.waitCondition(() -> inventory_cache.length != Inventory.getAll().length, General.random(1500, 2000));
        } else {
            if (!Banking07.isInBank())
                WebWalking.walkToBank();

            if (Banking.openBank())
                Timing07.waitCondition(Banking07::isBankItemsLoaded, General.random(1500, 2000));
        }
    }

    @Override
    public String toString() {
        return "Going to deposit items";
    }

}

