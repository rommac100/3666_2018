package org.firstinspires.ftc.teamcode;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.corningrobotics.enderbots.endercv.CameraViewDisplay;


/**
 * Created by rommac100 on 9/13/17.
 */
@Autonomous(name="BallDetectionTesting")
public class BallColourTest_2 extends LinearOpMode {

    private String allianceColour = "";
    boolean knockBallStatus = false;

     BallColour_Process ballColour_process;


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
        ballColour_process = new BallColour_Process();
        ballColour_process.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        ballColour_process.setShowBallColour(BallColour_Process.Colour_Ball.RED);
        ballColour_process.enable();
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
        ballColour_process.setShowBallColour(BallColour_Process.Colour_Ball.Null);
        ballColour_process.disable();
    }
}
