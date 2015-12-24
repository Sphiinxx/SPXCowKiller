package scripts.SPXCowKiller.nodes;

import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;
import scripts.SPXCowKiller.Main;
import scripts.SPXCowKiller.api.Node;

/**
 * Created by Sphiinx on 12/21/2015.
 */
public class WalkToCows extends Node {

    private final RSArea COW_PEN_AREA = new RSArea(new RSTile[]{
            new RSTile(3254, 3256, 0),
            new RSTile(3264, 3256, 0),
            new RSTile(3264, 3296, 0),
            new RSTile(3258, 3298, 0),
            new RSTile(3243, 3297, 0),
            new RSTile(3244, 3292, 0),
            new RSTile(3242, 3286, 0),
            new RSTile(3245, 3282, 0),
            new RSTile(3250, 3280, 0),
            new RSTile(3253, 3276, 0),
            new RSTile(3255, 3270, 0)
    });

    @Override
    public void execute() {
        Main.status = "Walking to cows...";
        WebWalking.walkTo(COW_PEN_AREA.getRandomTile());
    }

    @Override
    public boolean validate() {
        return !COW_PEN_AREA.contains(Player.getPosition()) &&
                !Inventory.isFull();
    }

}

