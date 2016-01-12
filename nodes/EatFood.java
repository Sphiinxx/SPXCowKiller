package scripts.SPXCowKiller.nodes;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Combat;
import org.tribot.api2007.Inventory;

import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSItem;
import scripts.SPXCowKiller.API.Framework.Node;
import scripts.SPXCowKiller.Variables;

/**
 * Created by Sphiinx on 1/10/2016.
 */
public class EatFood extends Node {

    public EatFood(Variables v) {
        super(v);
    }

    @Override
    public void execute() {
        RSItem[] food = Inventory.find(vars.foodName);
        if (Clicking.click("Eat", food[0])) {
            Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    General.sleep(100);
                    return Player.getAnimation() == -1;
                }
            }, General.random(750, 1000));
        }
    }

    @Override
    public String toString() {
        return "Eating food...";
    }

    @Override
    public boolean validate() {
        return vars.foodName.length() > 0 && Inventory.getCount(vars.foodName) > 0 && Combat.getHPRatio() < vars.eatPercentage;
    }

}

