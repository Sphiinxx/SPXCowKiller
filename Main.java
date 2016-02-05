package scripts.SPXCowKiller;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.Login;
import org.tribot.api2007.Skills;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.MessageListening07;
import org.tribot.script.interfaces.MousePainting;
import org.tribot.script.interfaces.MouseSplinePainting;
import org.tribot.script.interfaces.Painting;
import scripts.SPXCowKiller.data.Constants;
import scripts.SPXCowKiller.data.Variables;
import scripts.SPXCowKiller.gui.GUI;
import scripts.SPXCowKiller.tasks.EatFood;
import scripts.SPXCowKiller.API.Framework.Task;
import scripts.SPXCowKiller.tasks.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Sphiinx on 12/21/2015.
 */
@ScriptManifest(authors = "Sphiinx", category = "Combat", name = "[SPX] Cow Killer", version = 0.4)
public class Main extends Script implements MessageListening07, Painting {

    private Variables variables = new Variables();
    private ArrayList<Task> tasks = new ArrayList<>();
    public GUI gui = new GUI(variables);

    @Override
    public void run() {
        initializeGui();
        getStartLevels();
        getStartExp();
        Collections.addAll(tasks, new WithdrawItems(variables), new DepositItems(variables), new EatFood(variables), new WalkToCowPen(variables),
                new PickupItems(variables), new BuryBones(variables), new KillCow(variables), new DropUnwanted(variables), new EmptyQuiver(variables));
        variables.version = getClass().getAnnotation(ScriptManifest.class).version();
        loop(100, 150);
    }

    private void loop(int min, int max) {
        while (!variables.stopScript) {
            for (final Task task : tasks) {
                if (task.validate()) {
                    variables.status = task.toString();
                    task.execute();
                    General.sleep(min, max);
                }
            }
        }
    }

    public void initializeGui() {
        EventQueue.invokeLater(() -> {
            try {
                sleep(50);
                variables.status = "Initializing...";
                gui.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
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
        g.setRenderingHints(Constants.ANTIALIASING);
        if (Login.getLoginState() == Login.STATE.INGAME) {

            long timeRan = System.currentTimeMillis() - Constants.START_TIME;
            int getCurrentLevels = Skills.getActualLevel(Skills.SKILLS.ATTACK) + Skills.getActualLevel(Skills.SKILLS.STRENGTH) + Skills.getActualLevel(Skills.SKILLS.DEFENCE);
            int getCurrentExp = Skills.getXP(Skills.SKILLS.STRENGTH) + Skills.getXP(Skills.SKILLS.ATTACK) + Skills.getXP(Skills.SKILLS.DEFENCE);
            int getGainedLevels = getCurrentLevels - variables.startLevels;
            int getGainedExp = getCurrentExp - variables.startExp;

            g.setColor(Constants.BLACK_COLOR);
            g.fillRoundRect(11, 220, 200, 110, 8, 8); // Paint background
            g.setColor(Constants.RED_COLOR);
            g.drawRoundRect(9, 218, 202, 112, 8, 8); // Red outline
            g.fillRoundRect(13, 223, 194, 22, 8, 8); // Title background
            g.setFont(Constants.TITLE_FONT);
            g.setColor(Color.WHITE);
            g.drawString("[SPX] Cow Killer", 18, 239);
            g.setFont(Constants.TEXT_FONT);
            g.drawString("Runtime: " + Timing.msToString(timeRan), 14, 260);
            g.drawString("Levels Gained: " + getGainedLevels, 14, 276);
            g.drawString("Gained Exp: " + getGainedExp, 14, 293);
            g.drawString("Status: " + variables.status, 14, 310);
            g.drawString("v" + variables.version, 185, 326);

        }
    }



    @Override
    public void tradeRequestReceived(String s) {

    }

    @Override
    public void serverMessageReceived(String s) {
        if (s.contains("no ammo left")) {
            variables.outOfArrows = true;
        }
    }

    @Override
    public void playerMessageReceived(String s, String s1) {
    }

    @Override
    public void personalMessageReceived(String s, String s1) {

    }

    @Override
    public void duelRequestReceived(String s, String s1) {

    }

    @Override
    public void clanMessageReceived(String s, String s1) {

    }
}

