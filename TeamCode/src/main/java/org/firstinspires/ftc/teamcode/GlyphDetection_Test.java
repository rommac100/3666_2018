package org.firstinspires.ftc.teamcode;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.corningrobotics.enderbots.endercv.CameraViewDisplay;

/**
 * Created by rommac100 on 9/13/17.
 */
@Autonomous(name="GlyphDetection_Test")
public class GlyphDetection_Test extends LinearOpMode {

    private String allianceColour = "";
    boolean knockBallStatus = false;

     Glyph_Process glyph_process;


    public void getAutonomousSettings() {
        SharedPreferences internalPreferences = PreferenceManager.getDefaultSharedPreferences(hardwareMap.appContext);

        //Red or Blue
        allianceColour = internalPreferences.getString("Alliance Colour", "");
        String tempBallStatus = "";
        tempBallStatus = internalPreferences.getString("Knock Ball", "");
        if (tempBallStatus.equals("Yes")) {
            knockBallStatus = true;
        } else if (tempBallStatus.equals("No")) {
            knockBallStatus = false;
        }

        telemetry.addData("Alliance Colour: ", allianceColour);
        telemetry.addData("Knockball Status: ", tempBallStatus);
        telemetry.update();
    }

    public void init_auto()
    {
        glyph_process = new Glyph_Process();

        glyph_process.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        glyph_process.setShowGlyphColour(Glyph_Process.Colour_Glyph.Dark);
        glyph_process.enable();
    }



    @Override
    public void runOpMode() throws InterruptedException {


        AutoTransitioner.transitionOnStop(this, "MainDrive Teleop");

        getAutonomousSettings();

        init_auto();
        waitForStart();
        while(opModeIsActive()) {
            telemetry.update();
            //wait before quitting (quitting clears telemetry)
            Thread.sleep(1000);
        }
        glyph_process.setShowGlyphColour(Glyph_Process.Colour_Glyph.Null);
        glyph_process.disable();
    }
}
