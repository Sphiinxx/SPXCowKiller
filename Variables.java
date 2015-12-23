package scripts.SPXCowKiller;
import org.tribot.api2007.Skills;
import scripts.SPXCowKiller.api.Node;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Sphiinx on 12/22/2015.
 */
public class Variables {

    public static String[] pickupItems;
    public static String[] unwantedItems;
    public static String SCRIPT_VERSION = "v0.1";
    public static String STATUS;
    public static final Color color1 = new Color(0, 169, 194);
    public static final Color color2 = new Color(255, 255, 255);
    public static final Font font1 = new Font("Segoe Script", 0, 20);
    public static final long startTime = System.currentTimeMillis();
    public static final Font font2 = new Font("Arial", 0, 15);
    public static final Image img1 = getImage("http://i.imgur.com/fRrLAWr.png");
    public static final RenderingHints antialiasing = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    public static ArrayList<Node> nodes = new ArrayList<>();
    public static GUI gui = new GUI();
    public static boolean buryBones;
    public static boolean bankHides;
    public static boolean guiComplete;
    public static int startLevels;
    public static int startExp;

    public static Image getImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch (IOException e) {
            return null;
        }
    }

}

