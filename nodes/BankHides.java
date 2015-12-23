package scripts.SPXCowKiller.nodes;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.WebWalking;
import scripts.SPXCowKiller.Main;
import scripts.SPXCowKiller.Variables;
import scripts.SPXCowKiller.api.Node;

/**
 * Created by Sphiinx on 12/22/2015.
 */
public class BankHides extends Node {

    @Override
    public void execute() {
        Variables.STATUS = "Banking...";
        if (Banking.isInBank()) {
            System.out.println("Running 1");
            if (Banking.isBankScreenOpen()) {
                System.out.println("Running 2");
                Banking.depositAll();
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        return Inventory.getCount("Cowhide") < 1;
                    }
                }, General.random(750, 1000));
            } else {
                System.out.println("Running 3");
                Banking.openBank();
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        return Banking.isBankScreenOpen();
                    }
                }, General.random(750, 1000));
            }
        } else {
            System.out.println("Running 4");
            if (WebWalking.walkToBank()) {
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        return Banking.isInBank();
                    }
                }, General.random(750, 1000));
            }
        }
    }

    @Override
    public boolean validate() {
        return Variables.bankHides &&
                Inventory.isFull();
    }

}

