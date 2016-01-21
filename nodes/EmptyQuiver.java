package scripts.SPXCowKiller.nodes;

import org.tribot.api2007.WebWalking;
import org.tribot.script.interfaces.MessageListening07;
import scripts.SPXCowKiller.API.Framework.Node;
import scripts.SPXCowKiller.data.Variables;

/**
 * Created by Sphiinx on 1/20/2016.
 */
public class EmptyQuiver extends Node implements MessageListening07 {

    public EmptyQuiver(Variables v) {
        super(v);
    }

    @Override
    public void execute() {
        //TODO Something here
    }

    @Override
    public String toString() {
        return "Stopping script...";
    }

    @Override
    public void personalMessageReceived(String s, String s1) {

    }

    @Override
    public void duelRequestReceived(String s, String s1) {

    }

    @Override
    public void tradeRequestReceived(String s) {

    }

    @Override
    public void clanMessageReceived(String s, String s1) {

    }

    @Override
    public void serverMessageReceived(String s) {
        if (s.contains("out of arrows.")) {
            vars.hasArrows = true;
        }
    }

    @Override
    public void playerMessageReceived(String s, String s1) {

    }


    public boolean validate() {
        return vars.hasArrows;
    }
}

