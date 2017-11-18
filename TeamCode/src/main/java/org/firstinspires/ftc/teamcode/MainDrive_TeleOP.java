package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by rommac100 on 9/10/17.
 */
@TeleOp(name="MainDrive Teleop - ServoTest", group= "Main")
public class MainDrive_TeleOP extends OpMode {
    HardwareTestingMain robot = new HardwareTestingMain();

    double joyStickleft = 0;
    double joyStickRight = 0;

    @Override
    public void init() {
        robot.init(hardwareMap);
        telemetry.addData("Say", "Hello World");
        telemetry.addData("Commence Teleop","");

    }

    @Override
    public void loop() {

        //weird pov Drive...
        joyStickleft = gamepad1.left_stick_y;
        joyStickRight = gamepad1.right_stick_y;

        double max;
        double leftPow;
        double rightPow;

        leftPow = joyStickleft;
        rightPow = joyStickRight;

        robot.leftDrive1.setPower(leftPow);
        robot.leftDrive2.setPower(leftPow);

        robot.rightDrive1.setPower(rightPow);
        robot.rightDrive2.setPower(rightPow);

        //robot.armMove(armPow);

    }

    @Override
    public void stop()
    {
    }
}
