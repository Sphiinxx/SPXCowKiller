package scripts.SPXCowKiller.data;


import org.tribot.api2007.types.RSArea;

/**
 * Created by Sphiinx on 12/24/2015.
 */
public class Vars {

    public static Vars vars;

    public static Vars get() {
        return vars == null ? vars = new Vars() : vars;
    }

    public static void reset() {
        vars = null;
    }

    public RSArea area;

    public boolean buryBones;
    public boolean bankHides;
    public boolean guiComplete;
    public boolean outOfArrows;
    public boolean stopScript;

    public int startLevels;
    public int startExp;
    public int eatPercentage = 50;
    public int cowsKilled;
    public int getGainedExp;
    public int getGainedLevels;
    public double version;
    public long timeRan;

    public String status;
    public String foodName;

}

