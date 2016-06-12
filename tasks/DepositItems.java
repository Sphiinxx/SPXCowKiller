package scripts.SPXCowKiller.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.WebWalking;
import scripts.SPXCowKiller.data.Vars;
import scripts.SPXCowKiller.framework.Task;

/**
 * Created by Sphiinx on 12/22/2015.
 */
public class DepositItems implements Task {

    public void execute() {
        if (Banking.isInBank()) {
            openBank();
        } else {
            walkToBank();
        }
    }

    private void openBank() {
            if (Banking.isBankScreenOpen()) {
                if (Banking.depositAll() > 0) {
                    Timing.waitCondition(new Condition() {
                        @Override
                        public boolean active() {
                            General.sleep(100);
                            return !Inventory.isFull();
                        }
                    }, General.random(750, 1000));
                }
            } else {
                if (Banking.openBank()) {
                    Timing.waitCondition(new Condition() {
                        @Override
                        public boolean active() {
                            General.sleep(100);
                            return Banking.isBankScreenOpen();
                        }
                    }, General.random(750, 1000));
                }
            }
    }

    private void walkToBank() {
        if (WebWalking.walkToBank()) {
            Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    General.sleep(100);
                    return Banking.isInBank();
                }
            }, General.random(750, 1000));
        }
    }

    public String toString(){
        return "Depositing items...";
    }

    public boolean validate() {
        return Vars.get().bankHides && Inventory.getCount("Cowhide") == 28;
    }

}

