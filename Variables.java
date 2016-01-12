package scripts.SPXCowKiller;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

/**
 * Created by Sphiinx on 12/24/2015.
 */
public class Variables {

    public boolean buryBones;
    public boolean bankHides;
    public boolean guiComplete;
    public boolean stopScript;
    public int startLevels;
    public int startExp;
    public int eatPercentage = 50;
    public double version;
    public String status;
    public String location;
    public String foodName;
    public RSArea currentLocation = new RSArea(new RSTile[]{});

}

