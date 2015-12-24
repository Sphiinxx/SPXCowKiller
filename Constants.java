package scripts.SPXCowKiller;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Sphiinx on 12/24/2015.
 */
public class Constants {

    public static final Color color1 = new Color(0, 169, 194);
    public static final Color color2 = new Color(255, 255, 255);
    public static final Font font1 = new Font("Segoe Script", 0, 20);
    public static final long startTime = System.currentTimeMillis();
    public static final Font font2 = new Font("Arial", 0, 15);
    public static final Image img1 = getImage("http://i.imgur.com/fRrLAWr.png");
    public static final RenderingHints antialiasing = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    public static Image getImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch (IOException e) {
            return null;
        }
    }

}

