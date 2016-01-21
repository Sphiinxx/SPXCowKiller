package scripts.SPXCowKiller.nodes;

import org.tribot.api.General;
import org.tribot.api2007.Equipment;
import scripts.SPXCowKiller.API.Framework.Node;
import scripts.SPXCowKiller.data.Variables;

/**
 * Created by Sphiinx on 1/20/2016.
 */
public class EmptyQuiver extends Node{

    public EmptyQuiver(Variables v) {
        super(v);
    }

    @Override
    public void execute() {
        General.println("We're out of arrows...");
        General.println("Stopping script...");
        vars.stopScript = true;
    }

    public boolean validate() {
        if (Equipment.getItem(Equipment.SLOTS.ARROW) == null) {
            return true;
        }
        return false;
    }
}

