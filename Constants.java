package scripts.SPXCowKiller;

import org.tribot.api2007.types.RSArea;
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
    public static final RSArea EAST_LUMBRIDGE = new RSArea(new RSTile[]{
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
    public static final RSArea NORTH_LUMBRIDGE = new RSArea(new RSTile[]{
            new RSTile(3196, 3284, 0),
            new RSTile(3202, 3284, 0),
            new RSTile(3210, 3285, 0),
            new RSTile(3211, 3290, 0),
            new RSTile(3210, 3294, 0),
            new RSTile(3209, 3301, 0),
            new RSTile(3205, 3301, 0),
            new RSTile(3200, 3301, 0),
            new RSTile(3197, 3302, 0),
            new RSTile(3195, 3301, 0),
            new RSTile(3194, 3299, 0),
            new RSTile(3194, 3293, 0),
            new RSTile(3194, 3287, 0)
    });
    public static final RSArea RIVER_LUM = new RSArea(new RSTile[]{
            new RSTile(3155, 3316, 0),
            new RSTile(3160, 3315, 0),
            new RSTile(3165, 3320, 0),
            new RSTile(3172, 3318, 0),
            new RSTile(3180, 3316, 0),
            new RSTile(3186, 3315, 0),
            new RSTile(3190, 3312, 0),
            new RSTile(3196, 3309, 0),
            new RSTile(3202, 3310, 0),
            new RSTile(3206, 3314, 0),
            new RSTile(3204, 3321, 0),
            new RSTile(3197, 3330, 0),
            new RSTile(3190, 3336, 0),
            new RSTile(3180, 3343, 0),
            new RSTile(3167, 3342, 0),
            new RSTile(3155, 3346, 0),
            new RSTile(3154, 3341, 0),
            new RSTile(3155, 3335, 0),
            new RSTile(3154, 3328, 0),
            new RSTile(3154, 3322, 0)
    });
    public static final RSArea CRAFTING_GUILD = new RSArea(new RSTile[]{
            new RSTile(2923, 3276, 0),
            new RSTile(2928, 3281, 0),
            new RSTile(2929, 3285, 0),
            new RSTile(2926, 3290, 0),
            new RSTile(2920, 3292, 0),
            new RSTile(2916, 3291, 0),
            new RSTile(2915, 3289, 0),
            new RSTile(2918, 3286, 0),
            new RSTile(2922, 3281, 0)
    });
    public static final RSArea FALADOR = new RSArea(new RSTile[]{
            new RSTile(3022, 3298, 0),
            new RSTile(3029, 3299, 0),
            new RSTile(3036, 3300, 0),
            new RSTile(3040, 3299, 0),
            new RSTile(3042, 3302, 0),
            new RSTile(3042, 3312, 0),
            new RSTile(3038, 3313, 0),
            new RSTile(3030, 3312, 0),
            new RSTile(3023, 3312, 0),
            new RSTile(3022, 3309, 0)
    });
    public static final RSArea ARDOUGNE = new RSArea(new RSTile[]{
            new RSTile(2657, 3341, 0),
            new RSTile(2667, 3341, 0),
            new RSTile(2669, 3340, 0),
            new RSTile(2675, 3344, 0),
            new RSTile(2675, 3356, 0),
            new RSTile(2672, 3357, 0),
            new RSTile(2669, 3358, 0),
            new RSTile(2665, 3358, 0),
            new RSTile(2661, 3357, 0),
            new RSTile(2657, 3355, 0),
            new RSTile(2658, 3352, 0),
            new RSTile(2657, 3348, 0)
    });

}

