package scripts.SPXCowKiller.nodes;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.WebWalking;
import scripts.SPXCowKiller.Main;
import scripts.SPXCowKiller.api.Node;

/**
 * Created by Sphiinx on 12/22/2015.
 */
public class BankHides extends Node {

    @Override
    public void execute() {
        Main.status = "Banking...";
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
                            return Inventory.getCount("Cowhide") < 1;
                        }
                    }, General.random(750, 1000));
                }
            } else {
                if (Banking.openBank()) {
                    Timing.waitCondition(new Condition() {
                        @Override
                        public boolean active() {
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
                    return Banking.isInBank();
                }
            }, General.random(750, 1000));
        }
    }

    @Override
    public boolean validate() {
        return Main.bankHides &&
                Inventory.isFull();
    }

}

