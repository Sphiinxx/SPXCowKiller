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
import java.util.Collections;

/**
 * Created by Sphiinx on 12/21/2015.
 */
@ScriptManifest(authors = "Sphiinx", category = "Combat", name = "SPX Cow Killer")
public class Main extends Script implements Painting {

    @Override
    public void run() {
        initializeGui();
        getStartLevels();
        getStartExp();
        Collections.addAll(Variables.nodes, new BankHides(), new WalkToCows(), new PickupItems(), new BuryBones(), new KillCow(), new DropUnwanted());
        loop(20, 40);
    }

    private void loop(int min, int max) {
        while (true) {
            for (final Node node : Variables.nodes) {
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
                    Variables.STATUS = "Initializing...";
                    Variables.gui.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        do
            sleep(10);
        while (!Variables.guiComplete);
    }

    private void getStartLevels() {
        Variables.startLevels = Skills.getActualLevel(Skills.SKILLS.STRENGTH) +
                Skills.getActualLevel(Skills.SKILLS.ATTACK) +
                Skills.getActualLevel(Skills.SKILLS.DEFENCE);
    }

    private void getStartExp() {
        Variables.startExp = Skills.getXP(Skills.SKILLS.STRENGTH) +
                Skills.getXP(Skills.SKILLS.ATTACK) +
                Skills.getXP(Skills.SKILLS.DEFENCE);
    }

    public void onPaint(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        g.setRenderingHints(Variables.antialiasing);
        if (Login.getLoginState() == Login.STATE.INGAME) {

            long timeRan = System.currentTimeMillis() - Variables.startTime;
            int getCurrentLevels = Skills.getActualLevel(Skills.SKILLS.ATTACK) + Skills.getActualLevel(Skills.SKILLS.STRENGTH) + Skills.getActualLevel(Skills.SKILLS.DEFENCE);
            int getCurrentExp = Skills.getXP(Skills.SKILLS.STRENGTH) + Skills.getXP(Skills.SKILLS.ATTACK) + Skills.getXP(Skills.SKILLS.DEFENCE);
            int getGainedLevels = getCurrentLevels - Variables.startLevels;
            int getGainedExp = getCurrentExp - Variables.startExp;

            g.drawImage(Variables.img1, 2, 200, null);
            g.setFont(Variables.font1);
            g.setColor(Variables.color1);
            g.drawString("- Cow Killer", 58, 226);
            g.setFont(Variables.font2);
            g.setColor(Variables.color2);
            g.drawString("Runtime: " + Timing.msToString(timeRan), 11, 252);
            g.drawString("Levels Gained: " + getGainedLevels, 11, 272);
            g.drawString("Gained Exp: " + getGainedExp, 11, 292);
            g.drawString("Status: " + Variables.STATUS, 11, 312);
            g.drawString(Variables.SCRIPT_VERSION, 205, 330);
        }
    }

}

