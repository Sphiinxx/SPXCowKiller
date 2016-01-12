package scripts.SPXCowKiller;

import org.tribot.api.General;
import org.tribot.api2007.Skills;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import scripts.SPXCowKiller.nodes.EatFood;
import scripts.SPXCowKiller.API.Framework.Node;
import scripts.SPXCowKiller.nodes.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Sphiinx on 12/21/2015.
 */
@ScriptManifest(authors = "Sphiinx", category = "Combat", name = "[SPX] Cow Killer", version = 0.4)
public class Main extends Script {

    private Variables variables = new Variables();
    private ArrayList<Node> nodes = new ArrayList<>();
    public GUI gui = new GUI(variables);

    @Override
    public void run() {
        initializeGui();
        getStartLevels();
        getStartExp();
        Collections.addAll(nodes, new WithdrawItems(variables), new DepositItems(variables), new EatFood(variables), new WalkToCowPen(variables), new PickupItems(variables), new BuryBones(variables), new KillCow(variables), new DropUnwanted(variables));
        variables.version = getClass().getAnnotation(ScriptManifest.class).version();
        loop(100, 150);
    }

    private void loop(int min, int max) {
        while (!variables.stopScript) {
            for (final Node node : nodes) {
                if (node.validate()) {
                    variables.status = node.toString();
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


}

