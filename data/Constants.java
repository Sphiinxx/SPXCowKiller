package scripts.SPXCowKiller.data;

import org.tribot.api2007.types.RSTile;

import java.awt.*;

/**
 * Created by Sphiinx on 12/24/2015.
 */
public class Constants {

    public static final long START_TIME = System.currentTimeMillis();
    public static final Color RED_COLOR = new Color(214, 39, 39, 240);
    public static final Color BLACK_COLOR = new Color(0, 0, 0, 100);
    public static final Font TITLE_FONT = new Font("Arial Bold", 0, 15);
    public static final Font TEXT_FONT = new Font("Arial", 0, 12);
    public static final RenderingHints ANTIALIASING = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    public static final RSTile SAFE_ZONE = new RSTile(3222, 3217, 0);

}

