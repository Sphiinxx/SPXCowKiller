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

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Sphiinx on 12/21/2015.
 */
@ScriptManifest(authors = "Sphiinx", category = "Combat", name = "SPX Chicken Killer")
public class Main extends Script {

    public static ArrayList<Node> nodes = new ArrayList<>();
    private final int RANDOM_MOUSE_SPEED = General.random(100, 120);

    @Override
    public void run() {
        Mouse.setSpeed(RANDOM_MOUSE_SPEED);
        Collections.addAll(nodes, new WalkToCows(), new BuryBones(), new KillCow(), new PickupItems());
        loop(20, 40);
        // Start of our loop
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
        // End of our loop
    }
}

