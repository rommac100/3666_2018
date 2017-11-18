package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by rommac100 on 9/10/17.
 */
@TeleOp(name="MainDrive_Omni Teleop", group= "Main")
public class MainDrive_Omni extends OpMode {

    HardwareOmni robot = new HardwareOmni();

    double joyStickleft = 0;
    double joyStickRight = 0;

    double joyStickLeftLeft = 0;

    @Override
    public void init() {
        robot.init(hardwareMap);
        telemetry.addData("Say", "Hello World");
        telemetry.addData("Commence Teleop","");
        robot.upMotor.setPower(0);
        robot.downMotor.setPower(0);
        robot.rightMotor.setPower(0);
        robot.leftMotor.setPower(0);


    }

    @Override
    public void loop() {
        //weird pov Drive...
        joyStickleft = Math.pow(gamepad1.left_stick_y,3);
        joyStickRight = Math.pow(gamepad1.right_stick_x,3);

        joyStickLeftLeft = Math.pow(gamepad1.left_stick_x,3);
        double max;
        double upPow = joyStickleft;
        double leftPow = joyStickLeftLeft;
        double downPow = upPow;
        double rightPow = leftPow;


        if (joyStickRight != 0)
        {
            leftPow = joyStickRight;
            rightPow = joyStickRight*-1;
            upPow = joyStickRight*-1;
            downPow = joyStickRight;
        }





        //leftPow = joyStickleft+ joyStickRight;
        //rightPow = joyStickleft-joyStickRight;


        max = Math.max(Math.abs(upPow), Math.abs(leftPow));
        if (max > 1.0)
        {
            upPow /= max;
            leftPow /= max;
        }

        max = Math.max(Math.abs(downPow), Math.abs(rightPow));
        if (max >1.0)
        {
            downPow /=max;
            rightPow /=max;
        }

        robot.rightMotor.setPower(rightPow);
        robot.upMotor.setPower(upPow);
        robot.downMotor.setPower(downPow);
        robot.leftMotor.setPower(leftPow);
        telemetry.addData("leftPow", leftPow);
        telemetry.addData("upPow", upPow);
        telemetry.addData("rightPow",rightPow);
        telemetry.addData("downPow", downPow);

    }

    @Override
    public void stop()
    {
        robot.setLeftMotorPower(0);
        robot.setUpMotorPower(0);
    }
}
