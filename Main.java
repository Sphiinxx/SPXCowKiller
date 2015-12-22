package scripts.SPXCowKiller;

import org.tribot.api.General;
import org.tribot.api.input.Mouse;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import scripts.SPXCowKiller.api.Node;
import scripts.SPXCowKiller.nodes.BuryBones;
import scripts.SPXCowKiller.nodes.KillCow;
import scripts.SPXCowKiller.nodes.PickupItems;
import scripts.SPXCowKiller.nodes.WalkToCows;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Sphiinx on 12/21/2015.
 */
@ScriptManifest(authors = "Sphiinx", category = "Combat", name = "SPX Cow Killer")
public class Main extends Script {

    public static ArrayList<Node> nodes = new ArrayList<>();

    @Override
    public void run() {

        GUI gui = new GUI();
        gui.setVisible(true);
        while (gui.isVisible()) {
            sleep(300);
        }
        gui.setVisible(false);

        Collections.addAll(nodes, new WalkToCows(), new BuryBones(), new KillCow(), new PickupItems());

        loop(20, 40);
    }

    private void loop(int min, int max) {
        while (true) {
            for (final Node node : nodes) {
                if (node.validate()) {
                    node.execute();
                    General.sleep(General.random(min, max));
                }
            }
        }
    }
}

