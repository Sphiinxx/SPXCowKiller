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

    private String[] unwantedItems;

    public DropUnwanted(Variables v) {
        super(v);
        ArrayList<String> itemList = new ArrayList<String>();
        itemList.add("Raw beef");
        if (vars.buryBones) {
            itemList.add("Cowhide");
        }
        if (vars.bankHides) {
            itemList.add("Bones");
        }

        unwantedItems = new String[itemList.size()];
        unwantedItems = itemList.toArray(unwantedItems);
    }

    @Override
    public void execute() {
        if (Inventory.drop(unwantedItems) > 0) {
            Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    return Inventory.getCount(unwantedItems) < 1;
                }
            }, General.random(750, 1000));
        }
    }
    @Override
    public String toString(){
        return "Dropping items...";
    }


    public boolean validate() {
        return Inventory.getCount(unwantedItems) > 0 &&
                !Player.getRSPlayer().isInCombat();
    }

}

