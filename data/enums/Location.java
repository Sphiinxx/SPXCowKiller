package scripts.spxcowkiller.data.enums;

import org.tribot.api2007.types.RSTile;

/**
 * Created by Sphiinx on 1/13/2016.
 */
public enum Location {

    EAST_LUMBRIDGE(new RSTile(3259, 3272, 0)),
    NORTH_LUMBRIDGE(new RSTile(3201, 3292, 0)),
    RIVER_LUM(new RSTile(3175, 3328)),
    CRAFTING_GUILD(new RSTile(2922, 3289, 0)),
    FALADOR(new RSTile(3032, 3310, 0)),
    ARDOUGNE(new RSTile(2673, 3349, 0));

    private final RSTile TILE;

    Location(RSTile tile) {
        this.TILE = tile;
    }

    public RSTile getTile() {
        return TILE;
    }

}

