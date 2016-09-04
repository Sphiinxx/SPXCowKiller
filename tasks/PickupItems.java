package scripts.spxcowkiller.tasks;

import org.tribot.api.General;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSGroundItem;
import org.tribot.api2007.types.RSItem;
import scripts.spxcowkiller.data.Vars;
import scripts.task_framework.framework.Task;
import scripts.tribotapi.game.combat.Combat07;
import scripts.tribotapi.game.grounditems.GroundItems07;
import scripts.tribotapi.game.timing.Timing07;

/**
 * Created by Sphiinx on 7/11/2016.
 */
public class PickupItems implements Task {


    @Override
    public boolean validate() {
        return (Vars.get().bury_bones || Vars.get().bank_hides) && Vars.get().should_pickup_item && !Combat07.isInCombat() && !Inventory.isFull();
    }

    @Override
    public void execute() {
        if (Vars.get().bury_bones) {
            RSGroundItem item_to_pickup = GroundItems07.getGroundItem("Bones");
            if (pickupItem(item_to_pickup)) {
                Vars.get().bones_looted++;
            }
        } else {
            RSGroundItem item_to_pickup = GroundItems07.getGroundItem("Cowhide");
            if (pickupItem(item_to_pickup)) {
                Vars.get().hides_looted++;
            }
        }
    }

    private boolean pickupItem(RSGroundItem item_to_pickup) {
        if (item_to_pickup == null)
            return false;

        RSItem[] item_cache = Inventory.getAll();
        if (GroundItems07.pickUpGroundItem(item_to_pickup))
            if (Timing07.waitCondition(() -> item_cache.length != Inventory.getAll().length, General.random(1500, 2000))) {
                Vars.get().should_pickup_item = false;
                return true;
            }

        return false;
    }

    @Override
    public String toString() {
        return "Picking up items";
    }

}

