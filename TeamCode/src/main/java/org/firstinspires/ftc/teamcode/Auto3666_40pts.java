package org.firstinspires.ftc.teamcode;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by The Byronator on 10/15/17
 * Auto-40pts knocks off jewel and parks
 */
@Autonomous(name="auto_40pts")
public class Auto3666_40pts extends Auto3666 {

    //Hardware3666 robot = new Hardware3666();
    private ElapsedTime runtime = new ElapsedTime();

    int jewelValue;
    public double inchesDriven = 0;
    @Override
    public void getAutonomousSettings() {
      super.getAutonomousSettings();
    }

    /*
    public void initRobot()
    {
        super.robot.init();
        /*
        robot.init(hardwareMap);
        robot.leftDrive1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftDrive2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.rightDrive1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDrive2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.leftDrive1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.leftDrive2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        robot.rightDrive1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightDrive2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        robot.leftDrive1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        robot.leftDrive2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        robot.rightDrive1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        robot.rightDrive2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        */



    public void moveColourArmOut()
    {
        robot.colourArmServo.setPosition(Servo.MAX_POSITION);
    }

    public void moveColourArmIn()
    {
        robot.colourArmServo.setPosition(Servo.MIN_POSITION);
    }

    public int checkColour()
    {
        int redVal = robot.armColourSensor.red();
        int blueVal = robot.armColourSensor.blue();

        //false = red, blue = true
        if (redVal > blueVal)
        {
            if (allianceColour.equals("b"))
            {
                encoderDrive(inchToTics(5),inchToTics(5), Hardware3666.Direction.FORWARD);
            }
            else
            {
                encoderDrive(inchToTics(5),inchToTics(5), Hardware3666.Direction.FORWARD);
                inchesDriven = -5;
            }
            return 0;
        }
        else if (blueVal > redVal)
        {
            if (allianceColour.equals("r"))
            {
                encoderDrive(inchToTics(5),inchToTics(5), Hardware3666.Direction.BACKWARD);
            }
            /*
            else
            {
                encoderDrive(inchToTics(5),inchToTics(5), Hardware3666.Direction.FORWARD);
            }
            */
            return 1;
        }
        return 2;
    }

    @Override
    public void runOpMode() throws InterruptedException {


        telemetry.addData("Robot Init","");
        telemetry.update();

        AutoTransitioner.transitionOnStop(this, "MainDrive_Teleop_3666");
        getAutonomousSettings();
        waitForStart();
        initRobot();
        if (opModeIsActive())
        {
            telemetry.update();
            moveColourArmIn();
            moveColourArmOut();
            //Moves arm out and waits a second then reads colour.
            sleep(1000);
            jewelValue = checkColour();
            moveColourArmIn();
            sleep(300);
            if (allianceColour.equals("r"))
            {
                encoderDrive(inchToTics(48+inchesDriven*2),inchToTics(48+inchesDriven*2), Hardware3666.Direction.FORWARD);
            }
            else
            {
                encoderDrive(inchToTics(48),inchToTics(48), Hardware3666.Direction.FORWARD);
            }
            idle();
        }

        /*
        moveColourArmIn();
        moveColourArmOut();
        */
        while(opModeIsActive())
        {
            telemetry.addData("Color Value", jewelValue);
            telemetry.addData("colour r: ", robot.armColourSensor.red());
            telemetry.addData("colour b: ", robot.armColourSensor.blue());
            telemetry.update();
        }


    }
}
