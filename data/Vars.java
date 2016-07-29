package scripts.SPXCowKiller.data;

import scripts.SPXCowKiller.data.enums.Location;

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

    public boolean bury_bones;
    public boolean bank_hides;
    public boolean gui_complete;
    public boolean out_of_ammo;
    public boolean should_pickup_item;
    
    public int cows_killed;
    public int bones_looted;
    public int hides_looted;
    public int bones_buried;

    public String food_name;

    public Location location;

}

