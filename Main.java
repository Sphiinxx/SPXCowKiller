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

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Sphiinx on 12/21/2015.
 */
@ScriptManifest(authors = "Sphiinx", category = "Combat", name = "[SPX] Cow Killer", version = 0.1)
public class Main extends Script implements Painting {

    private Variables variables = new Variables();
    private ArrayList<Node> nodes = new ArrayList<>();
    public GUI gui = new GUI(variables);

    @Override
    public void run() {
        initializeGui();
        getStartLevels();
        getStartExp();
        Collections.addAll(nodes, new DepositItems(variables), new WalkToCows(variables), new PickupItems(variables), new BuryBones(variables), new KillCow(variables), new DropUnwanted(variables));
        variables.version = getClass().getAnnotation(ScriptManifest.class).version();
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
                    variables.status = "Initializing...";
                    gui.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        do
            sleep(10);
        while (!variables.guiComplete);
    }

    private void getStartLevels() {
        variables.startLevels = Skills.getActualLevel(Skills.SKILLS.STRENGTH) +
                Skills.getActualLevel(Skills.SKILLS.ATTACK) +
                Skills.getActualLevel(Skills.SKILLS.DEFENCE);
    }

    private void getStartExp() {
        variables.startExp = Skills.getXP(Skills.SKILLS.STRENGTH) +
                Skills.getXP(Skills.SKILLS.ATTACK) +
                Skills.getXP(Skills.SKILLS.DEFENCE);
    }

    public void onPaint(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        g.setRenderingHints(Constants.antialiasing);
        if (Login.getLoginState() == Login.STATE.INGAME) {

            long timeRan = System.currentTimeMillis() - Constants.startTime;
            int getCurrentLevels = Skills.getActualLevel(Skills.SKILLS.ATTACK) + Skills.getActualLevel(Skills.SKILLS.STRENGTH) + Skills.getActualLevel(Skills.SKILLS.DEFENCE);
            int getCurrentExp = Skills.getXP(Skills.SKILLS.STRENGTH) + Skills.getXP(Skills.SKILLS.ATTACK) + Skills.getXP(Skills.SKILLS.DEFENCE);
            int getGainedLevels = getCurrentLevels - variables.startLevels;
            int getGainedExp = getCurrentExp - variables.startExp;

            g.drawImage(Constants.img1, 2, 200, null);
            g.setFont(Constants.font1);
            g.setColor(Constants.color1);
            g.setFont(Constants.font2);
            g.setColor(Constants.color2);
            g.drawString("Runtime: " + Timing.msToString(timeRan), 11, 252);
            g.drawString("Levels Gained: " + getGainedLevels, 11, 272);
            g.drawString("Gained Exp: " + getGainedExp, 11, 292);
            g.drawString("Status: " + variables.status, 11, 312);
            g.drawString("v" + variables.version, 205, 330);
        }
    }

}

