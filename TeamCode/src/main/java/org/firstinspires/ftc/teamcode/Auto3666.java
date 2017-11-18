package org.firstinspires.ftc.teamcode;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by rommac100 on 10/15/17
 * Just a template for 3666's Auto. Copy and edit as needed. Encoder drive is included.
 */

public class Auto3666 extends LinearOpMode {

    public Hardware3666 robot = new Hardware3666();
    private ElapsedTime runtime = new ElapsedTime();

    public String allianceColour = "";
    public boolean knockBallStatus = false;

    public void initRobot()
    {
        robot.init(hardwareMap);

    }

    public void getAutonomousSettings() {
        SharedPreferences internalPreferences = PreferenceManager.getDefaultSharedPreferences(hardwareMap.appContext);

        //Red or Blue
        allianceColour = internalPreferences.getString("Alliance Colour", "");
        String tempBallStatus = "";
        tempBallStatus = internalPreferences.getString("Knock Ball", "");
        if (tempBallStatus.toLowerCase().equals("y")) {
            knockBallStatus = true;
        } else if (tempBallStatus.toLowerCase().equals("n")) {
            knockBallStatus = false;
        }

        telemetry.addData("Alliance Colour: ", allianceColour);
        telemetry.addData("Knockball Status: ", tempBallStatus);
        telemetry.update();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        initRobot();

        telemetry.addData("Robot Init","");
        telemetry.update();

        waitForStart();

        while (opModeIsActive())
        {
            telemetry.update();
            idle();
        }

    }

    public void turnDegrees(double degrees)
    {

    }

    public int inchToTics(double distance)
    {
        return (int)(distance*robot.ticksPerInch);
    }

    //Glyph side is forward
    public void encoderDrive(int leftDistance, int rightDistance, Hardware3666.Direction direction) {

        //Forward or Reverse in Motion

        //Sets encoders states to be runtoPos and Braking on
        if (robot.leftDrive1.getMode() != DcMotor.RunMode.RUN_TO_POSITION) {
            robot.leftDrive1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.leftDrive2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.rightDrive1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.rightDrive2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.leftDrive1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.leftDrive2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.rightDrive1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightDrive2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.leftDrive1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            robot.leftDrive2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            robot.rightDrive1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            robot.rightDrive2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        if (direction == Hardware3666.Direction.BACKWARD) {
            robot.leftDrive1.setTargetPosition(leftDistance + robot.leftDrive1.getCurrentPosition());
            robot.leftDrive2.setTargetPosition(leftDistance + robot.leftDrive2.getCurrentPosition());

            robot.rightDrive1.setTargetPosition(rightDistance + robot.rightDrive1.getCurrentPosition());
            robot.rightDrive2.setTargetPosition(rightDistance + robot.rightDrive2.getCurrentPosition());
        } else if (direction == Hardware3666.Direction.FORWARD) {
            robot.leftDrive1.setTargetPosition(-leftDistance + robot.leftDrive1.getCurrentPosition());
            robot.leftDrive2.setTargetPosition(-leftDistance + robot.leftDrive2.getCurrentPosition());

            robot.rightDrive1.setTargetPosition(-rightDistance + robot.rightDrive1.getCurrentPosition());
            robot.rightDrive2.setTargetPosition(-rightDistance + robot.rightDrive2.getCurrentPosition());
        }


        robot.setDrivePow(direction.value, direction.value);
        //loop until the motor is no longer busy - finished the traveling
        while (robot.leftDrive2.isBusy() && robot.rightDrive1.isBusy() && opModeIsActive()) {
            telemetry.addData("Distance Traveled", robot.leftDrive2.getCurrentPosition());
            telemetry.update();
            idle();
        }
        robot.setDrivePow(0, 0);

        //reset motors to their normal behavior, float, runw/oEncoder
        robot.leftDrive1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftDrive2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.rightDrive1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDrive2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.leftDrive1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.leftDrive2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        robot.rightDrive1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.rightDrive2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        robot.leftDrive1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        robot.leftDrive2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        robot.rightDrive1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        robot.rightDrive2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }
}
