package scripts.SPXCowKiller;

import com.allatori.annotations.DoNotRename;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.*;
import scripts.SPXCowKiller.data.Vars;
import scripts.SPXCowKiller.tasks.*;
import scripts.TaskFramework.framework.Task;
import scripts.TribotAPI.AbstractScript;
import scripts.TribotAPI.game.utiity.Utility07;
import scripts.TribotAPI.gui.GUI;
import scripts.TribotAPI.painting.paint.Calculations;
import scripts.TribotAPI.painting.paint.SkillData;
import scripts.TribotAPI.painting.paint.enums.DataPosition;

import java.awt.*;

/**
 * Created by Sphiinx on 12/21/2015.
 * Re-written by Sphiinx on 7/28/2016.
 */
@ScriptManifest(authors = "Sphiinx", category = "Combat", name = "[SPX] Cow Killer", version = 1.6)
@DoNotRename
public class Main extends AbstractScript implements Painting, EventBlockingOverride, MessageListening07, Ending {


    @Override
    protected GUI getGUI() {
        return new GUI(getClass().getResource("GUI.fxml"));
    }

    @Override
    public void addTasks(Task... tasks) {
        super.addTasks(new OutOfAmmo(), new DepositItems(), new WithdrawItems(), new EatFood(), new BuryBones(), new PickupItems(), new TravelToCowPen(), new KillCow());
    }

    @Override
    public void onPaint(Graphics g) {
        super.onPaint(g);
        paint_manager.drawGeneralData("Cows killed: ", Vars.get().cows_killed + Calculations.getPerHour(Vars.get().cows_killed, this.getRunningTime()), DataPosition.TWO, g);
        if (Vars.get().bury_bones)
            paint_manager.drawGeneralData("Bones buried: ", Vars.get().bones_buried + Calculations.getPerHour(Vars.get().bones_buried, this.getRunningTime()), DataPosition.FOUR, g);
        if (Vars.get().bank_hides)
            paint_manager.drawGeneralData("Hides looted: ", Vars.get().hides_looted + Calculations.getPerHour(Vars.get().hides_looted, this.getRunningTime()), DataPosition.FIVE, g);

        paint_manager.drawGeneralData("Status: ", task_manager.getStatus() + Utility07.getLoadingPeriods(), DataPosition.THREE, g);
    }

    @Override
    public void duelRequestReceived(String s, String s1) {

    }

    @Override
    public void playerMessageReceived(String s, String s1) {

    }

    @Override
    public void serverMessageReceived(String s) {
        if (s.contains("no ammo left") || s.contains("You do not have enough"))
            Vars.get().out_of_ammo = true;
    }

    @Override
    public void tradeRequestReceived(String s) {

    }

    @Override
    public void personalMessageReceived(String s, String s1) {

    }

    @Override
    public void clanMessageReceived(String s, String s1) {

    }

    @Override
    public void onEnd() {
        SignatureData.sendSignatureData(this.getRunningTime() / 1000, Vars.get().cows_killed, SkillData.getTotalLevelsGained(), SkillData.getTotalExperienceGained());
        super.onEnd();
    }

}