package scripts.SPXCowKiller;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.Login;
import org.tribot.api2007.Skills;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.*;
import scripts.SPXCowKiller.data.Constants;
import scripts.SPXCowKiller.data.Vars;
import scripts.SPXCowKiller.framework.Task;
import scripts.SPXCowKiller.framework.TaskManager;
import scripts.SPXCowKiller.gui.GUI;
import scripts.SPXCowKiller.tasks.EatFood;
import scripts.SPXCowKiller.tasks.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sphiinx on 12/21/2015.
 */
@ScriptManifest(authors = "Sphiinx", category = "Combat", name = "[SPX] Cow Killer", version = 0.4)
public class Main extends Script implements MessageListening07, Painting, MouseSplinePainting, MousePainting, Ending {

    public GUI gui = new GUI();
    private TaskManager taskManager = new TaskManager();

    @Override
    public void run() {
        Vars.reset();
        initializeGui();
        getStartLevels();
        getStartExp();
        addCollection();
        Vars.get().version = getClass().getAnnotation(ScriptManifest.class).version();
        loop(100, 150);
    }

    private void addCollection() {
        taskManager.addTask(new WithdrawItems(), new DepositItems(), new EatFood(), new WalkToCowPen(), new PickupItems(), new BuryBones(), new KillCow(), new DropUnwanted(), new EmptyQuiver());
    }

    private void loop(int min, int max) {
        while (!Vars.get().stopScript) {
            Task task = taskManager.getValidTask();
            if (task != null) {
                Vars.get().status = task.toString();
                task.execute();
                General.sleep(min, max);
            }
        }
    }

    public void initializeGui() {
        EventQueue.invokeLater(() -> {
            try {
                sleep(50);
                Vars.get().status = "Initializing...";
                gui.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        do
            sleep(10);
        while (!Vars.get().guiComplete);
    }

    private void getStartLevels() {
        Vars.get().startLevels = Skills.getActualLevel(Skills.SKILLS.STRENGTH) +
                Skills.getActualLevel(Skills.SKILLS.ATTACK) +
                Skills.getActualLevel(Skills.SKILLS.DEFENCE);
    }

    private void getStartExp() {
        Vars.get().startExp = Skills.getXP(Skills.SKILLS.STRENGTH) +
                Skills.getXP(Skills.SKILLS.ATTACK) +
                Skills.getXP(Skills.SKILLS.DEFENCE);
    }

    public void onPaint(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        g.setRenderingHints(Constants.ANTIALIASING);
        if (Login.getLoginState() == Login.STATE.INGAME) {

            Vars.get().timeRan = System.currentTimeMillis() - Constants.START_TIME;
            int getCurrentLevels = Skills.getActualLevel(Skills.SKILLS.ATTACK) + Skills.getActualLevel(Skills.SKILLS.STRENGTH) + Skills.getActualLevel(Skills.SKILLS.DEFENCE);
            int getCurrentExp = Skills.getXP(Skills.SKILLS.STRENGTH) + Skills.getXP(Skills.SKILLS.ATTACK) + Skills.getXP(Skills.SKILLS.DEFENCE);
            Vars.get().getGainedLevels = getCurrentLevels - Vars.get().startLevels;
            Vars.get().getGainedExp = getCurrentExp - Vars.get().startExp;

            g.setColor(Constants.BLACK_COLOR);
            g.fillRoundRect(11, 220, 200, 110, 8, 8); // SPXMouse07 background
            g.setColor(Constants.RED_COLOR);
            g.drawRoundRect(9, 218, 202, 112, 8, 8); // Red outline
            g.fillRoundRect(13, 223, 194, 22, 8, 8); // Title background
            g.setFont(Constants.TITLE_FONT);
            g.setColor(Color.WHITE);
            g.drawString("[SPX] Cow Killer", 18, 239);
            g.setFont(Constants.TEXT_FONT);
            g.drawString("Runtime: " + Timing.msToString(Vars.get().timeRan), 14, 260);
            g.drawString("Levels Gained: " + Vars.get().getGainedLevels, 14, 276);
            g.drawString("Gained Exp: " + Vars.get().getGainedExp, 14, 293);
            g.drawString("Status: " + Vars.get().status, 14, 310);
            g.drawString("v" + Vars.get().version, 185, 326);

        }
    }

    @Override
    public void paintMouse(Graphics graphics, Point point, Point point1) {
        graphics.setColor(Color.BLACK);
        graphics.drawRect(Mouse.getPos().x - 13, Mouse.getPos().y - 13, 27, 27); // Square rectangle Stroke
        graphics.drawRect(Mouse.getPos().x, Mouse.getPos().y - 512, 1, 500); // Top y axis Line Stroke
        graphics.drawRect(Mouse.getPos().x, Mouse.getPos().y + 13, 1, 500); // Bottom y axis Line Stroke
        graphics.drawRect(Mouse.getPos().x + 13, Mouse.getPos().y, 800, 1); // Right x axis line Stroke
        graphics.drawRect(Mouse.getPos().x - 812, Mouse.getPos().y, 800, 1); // left x axis line Stroke
        graphics.fillOval(Mouse.getPos().x - 3, Mouse.getPos().y - 3, 7, 7); // Center dot stroke
        graphics.setColor(Constants.RED_COLOR);
        graphics.drawRect(Mouse.getPos().x - 12, Mouse.getPos().y - 12, 25, 25); // Square rectangle
        graphics.drawRect(Mouse.getPos().x, Mouse.getPos().y - 512, 0, 500); // Top y axis Line
        graphics.drawRect(Mouse.getPos().x, Mouse.getPos().y + 13, 0, 500); // Bottom y axis Line
        graphics.drawRect(Mouse.getPos().x + 13, Mouse.getPos().y, 800, 0); // Right x axis line
        graphics.drawRect(Mouse.getPos().x - 812, Mouse.getPos().y, 800, 0); // left x axis line
        graphics.fillOval(Mouse.getPos().x - 2, Mouse.getPos().y - 2, 5, 5); // Center dot
    }

    @Override
    public void paintMouseSpline(Graphics graphics, ArrayList<Point> arrayList) {
    }

    @Override
    public void tradeRequestReceived(String s) {

    }

    @Override
    public void serverMessageReceived(String s) {
        if (s.contains("no ammo left")) {
            Vars.get().outOfArrows = true;
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

    @Override
    public void onEnd() {
        DynamicSignature.sendSignatureData(Vars.get().timeRan / 1000, Vars.get().cowsKilled, Vars.get().getGainedLevels, Vars.get().getGainedExp);
    }

}

