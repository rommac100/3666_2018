package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Byronator on 10/15/17
 * Just a template for 3666's Auto. Copy and edit as needed. Encoder drive is included.
 */
@Autonomous(name="encoderTest")
public class Auto3666_EncoderTest extends Auto3666 {

    Hardware3666 robot = new Hardware3666();
    private ElapsedTime runtime = new ElapsedTime();

    /*
    public void initRobot()
    {
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

    }
    */

    @Override
    public void runOpMode() throws InterruptedException {
        initRobot();

        telemetry.addData("Robot Init","");
        telemetry.update();

        waitForStart();

        if (opModeIsActive())
        {

            encoderDrive(inchToTics(30),inchToTics(30), Hardware3666.Direction.BACKWARD);
            telemetry.update();




            idle();
        }

    }

  /*  public int inchToTics(double distance)
    {
        return (int)(distance*robot.ticksPerInch);
    }
    */
/*
    public void encoderDrive(int leftDistance, int rightDistance, Hardware3666.Direction direction)
    {

        //Forward or Reverse in Motion

        //Sets encoders states to be runtoPos and Braking on
        if (robot.leftDrive1.getMode() != DcMotor.RunMode.RUN_TO_POSITION)
        {
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
        if (direction.value > 0) {
            robot.leftDrive1.setTargetPosition(leftDistance+robot.leftDrive1.getCurrentPosition());
            robot.leftDrive2.setTargetPosition(leftDistance+robot.leftDrive2.getCurrentPosition());

            robot.rightDrive1.setTargetPosition(rightDistance+robot.rightDrive1.getCurrentPosition());
            robot.rightDrive2.setTargetPosition(rightDistance+robot.rightDrive2.getCurrentPosition());
        }
        else
        {
            robot.leftDrive1.setTargetPosition(-leftDistance+robot.leftDrive1.getCurrentPosition());
            robot.leftDrive2.setTargetPosition(-leftDistance+robot.leftDrive2.getCurrentPosition());

            robot.rightDrive1.setTargetPosition(-rightDistance+robot.rightDrive1.getCurrentPosition());
            robot.rightDrive2.setTargetPosition(-rightDistance+robot.rightDrive2.getCurrentPosition());
        }


        robot.setDrivePow(direction.value,direction.value);
        //loop until the motor is no longer busy - finished the traveling
        while (robot.leftDrive2.isBusy() && robot.rightDrive1.isBusy() && opModeIsActive())
        {
            telemetry.addData("Distance Traveled", robot.leftDrive2.getCurrentPosition());
            telemetry.update();
            idle();
        }
        robot.setDrivePow(0,0);

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
    */

}
