package scripts.SPXCowKiller.nodes;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import scripts.SPXCowKiller.Variables;
import scripts.SPXCowKiller.api.Node;

import java.util.ArrayList;

/**
 * Created by Sphiinx on 12/22/2015.
 */
public class DropUnwanted extends Node {

    public DropUnwanted() {
        ArrayList<String> itemList = new ArrayList<String>();
        itemList.add("Raw beef");
        if (Variables.buryBones) {
            itemList.add("Cowhide");
        }
        if (Variables.bankHides) {
            itemList.add("Bones");
        }

        Variables.unwantedItems = new String[itemList.size()];
        Variables.unwantedItems = itemList.toArray(Variables.unwantedItems);
    }

    @Override
    public void execute() {
            Variables.STATUS = "Dropping...";
            Inventory.drop(Variables.unwantedItems);
            Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    return Inventory.getCount(Variables.unwantedItems) < 1;
                }
            }, General.random(750, 1000));
    }

    public boolean validate() {
        return Inventory.getCount(Variables.unwantedItems) > 0 &&
                !Player.getRSPlayer().isInCombat();
    }


}

