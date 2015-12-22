package scripts.SPXCowKiller.nodes;

import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;
import scripts.SPXCowKiller.api.Node;

/**
 * Created by Sphiinx on 12/21/2015.
 */
public class WalkToCows extends Node {

    private final RSArea COW_PEN_AREA = new RSArea(new RSTile[]{
            new RSTile(3253, 3255, 0),
            new RSTile(3265, 3255, 0),
            new RSTile(3265, 3296, 0),
            new RSTile(3260, 3299, 0),
            new RSTile(3254, 3298, 0),
            new RSTile(3241, 3298, 0),
            new RSTile(3242, 3295, 0),
            new RSTile(3243, 3290, 0),
            new RSTile(3241, 3287, 0),
            new RSTile(3243, 3284, 0),
            new RSTile(3246, 3280, 0),
            new RSTile(3251, 3278, 0),
            new RSTile(3253, 3273, 0)
    });

    @Override
    public void execute() {
        WebWalking.walkTo(COW_PEN_AREA.getRandomTile());
    }

    @Override
    public boolean validate() {
        return !COW_PEN_AREA.contains(Player.getPosition());
    }

}

