package scripts.SPXCowKiller;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Login;
import org.tribot.api2007.Skills;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Painting;
import scripts.SPXCowKiller.api.Node;
import scripts.SPXCowKiller.nodes.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Sphiinx on 12/21/2015.
 */
@ScriptManifest(authors = "Sphiinx", category = "Combat", name = "SPX Cow Killer", version = 0.1)
public class Main extends Script implements Painting {

    public final Color color1 = new Color(0, 169, 194);
    public final Color color2 = new Color(255, 255, 255);
    public final Font font1 = new Font("Segoe Script", 0, 20);
    public final long startTime = System.currentTimeMillis();
    public final Font font2 = new Font("Arial", 0, 15);
    public final Image img1 = getImage("http://i.imgur.com/fRrLAWr.png");
    public final RenderingHints antialiasing = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    public ArrayList<Node> nodes = new ArrayList<>();
    public GUI gui = new GUI();
    public int startLevels;
    public int startExp;
    public double version;
    public static String status;
    public static boolean buryBones;
    public static boolean bankHides;
    public static boolean guiComplete;

    @Override
    public void run() {
        initializeGui();
        getStartLevels();
        getStartExp();
        Collections.addAll(nodes, new BankHides(), new WalkToCows(), new PickupItems(), new BuryBones(), new KillCow(), new DropUnwanted());
        version = getClass().getAnnotation(ScriptManifest.class).version();
        loop(20, 40);
    }

    private void loop(int min, int max) {
        while (true) {
            for (final Node node : nodes) {
                if (node.validate()) {
                    node.execute();
                    General.sleep(min, max);
                }
            }
        }
    }

    public void initializeGui() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(50);
                    status = "Initializing...";
                    gui.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        do
            sleep(10);
        while (!guiComplete);
    }

    private void getStartLevels() {
        startLevels = Skills.getActualLevel(Skills.SKILLS.STRENGTH) +
                Skills.getActualLevel(Skills.SKILLS.ATTACK) +
                Skills.getActualLevel(Skills.SKILLS.DEFENCE);
    }

    private void getStartExp() {
        startExp = Skills.getXP(Skills.SKILLS.STRENGTH) +
                Skills.getXP(Skills.SKILLS.ATTACK) +
                Skills.getXP(Skills.SKILLS.DEFENCE);
    }

    public static Image getImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch (IOException e) {
            return null;
        }
    }

    public void onPaint(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        g.setRenderingHints(antialiasing);
        if (Login.getLoginState() == Login.STATE.INGAME) {

            long timeRan = System.currentTimeMillis() - startTime;
            int getCurrentLevels = Skills.getActualLevel(Skills.SKILLS.ATTACK) + Skills.getActualLevel(Skills.SKILLS.STRENGTH) + Skills.getActualLevel(Skills.SKILLS.DEFENCE);
            int getCurrentExp = Skills.getXP(Skills.SKILLS.STRENGTH) + Skills.getXP(Skills.SKILLS.ATTACK) + Skills.getXP(Skills.SKILLS.DEFENCE);
            int getGainedLevels = getCurrentLevels - startLevels;
            int getGainedExp = getCurrentExp - startExp;

            g.drawImage(img1, 2, 200, null);
            g.setFont(font1);
            g.setColor(color1);
            g.drawString("- Cow Killer", 58, 226);
            g.setFont(font2);
            g.setColor(color2);
            g.drawString("Runtime: " + Timing.msToString(timeRan), 11, 252);
            g.drawString("Levels Gained: " + getGainedLevels, 11, 272);
            g.drawString("Gained Exp: " + getGainedExp, 11, 292);
            g.drawString("Status: " + status, 11, 312);
            g.drawString("v" + version, 205, 330);
        }
    }

}

