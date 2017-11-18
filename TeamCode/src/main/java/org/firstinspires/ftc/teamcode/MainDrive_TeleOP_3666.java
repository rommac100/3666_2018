package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by rommac100 on 9/10/17.
 */
@TeleOp(name="MainDrive_Teleop_3666", group= "Main")
public class MainDrive_TeleOP_3666 extends OpMode {

    Hardware3666 robot = new Hardware3666();

    double joyStickleft = 0;
    double joyStickRight = 0;
    double joyStickWinch = 0;

    boolean hasStarted = false;

    double armPosition = .5;

    private static final boolean enableDrive = true;
    private static final boolean enableVex = true;
    private static final boolean enableIntakeArms = true;
    public static final boolean enableColourArm = true;
    private static final boolean enableWinch = true;

    

    @Override
    public void init() {
        robot.init(hardwareMap);
        telemetry.addData("Say", "Hello World");
        telemetry.addData("Commence Teleop","");

        //enables theses control systems think ifdefs in c

        if (enableDrive)
        {
            robot.leftDrive1.setPower(0);
            robot.rightDrive1.setPower(0);
            robot.leftDrive2.setPower(0);
            robot.rightDrive2.setPower(0);
        }

        if(enableVex)
        {
            robot.vexIntake1.setPower(0);
            robot.vexIntake2.setPower(0);
        }

        if (enableIntakeArms)
        {
            //robot.initArmServo();
            robot.intakeArm1.setPosition(0);
            robot.intakeArm2.setPosition(1);
            //hasStarted = true;
        }
    }

    public void initActual()
    {

    }

    @Override
    public void loop() {

        //weird pov Drive...
        joyStickRight = gamepad1.right_stick_y;
        joyStickleft = gamepad1.left_stick_y;
        joyStickWinch = gamepad2.left_stick_y;
        double max;
        double leftPow;
        double rightPow;
        double vexIntakePow1;
        double vexIntakePow2;
        double winchPower;

    // reversed due to the incompetence of Byron
        leftPow = joyStickRight;
        rightPow = joyStickleft;

        winchPower = joyStickWinch;

        max = Math.max(Math.abs(leftPow), Math.abs(rightPow));
        if (max > 1.0)
        {
            leftPow /= max;
            rightPow /= max;
        }

        if (enableVex && gamepad2.left_bumper)
        {
            vexIntakePow2 = .5;
        }
        else if (enableVex && gamepad2.left_trigger > 0)
        {
            vexIntakePow2 = -.5;
        }
        else
        {
            vexIntakePow2 = 0;
        }

        if (enableVex && gamepad2.right_bumper)
        {
            vexIntakePow1 = .5;
        }
        else if (enableVex && gamepad2.right_trigger > 0)
        {
            vexIntakePow1 = -.5;
        }
        else
        {
            vexIntakePow1 = 0;

        }

        if (enableIntakeArms && gamepad2.b)
        {
            robot.intakeArm1.setPosition(0);
            robot.intakeArm2.setPosition(1);
        }
        else if (enableIntakeArms && gamepad2.y)
        {
            robot.intakeArm1.setPosition(.3);
            robot.intakeArm2.setPosition(.7);
        }
        else if (enableIntakeArms && gamepad2.x)
        {
            robot.intakeArm1.setPosition(1);
            robot.intakeArm2.setPosition(0);
        }

        if (enableDrive) {
            robot.leftDrive1.setPower(rightPow);
            robot.rightDrive1.setPower(leftPow);
            robot.leftDrive2.setPower(rightPow);
            robot.rightDrive2.setPower(leftPow);
            telemetry.addData("leftPow", leftPow);
            telemetry.addData("rightPow", rightPow);
        }
        if (enableVex)
        {
            robot.vexIntake1.setPower(vexIntakePow1);
            robot.vexIntake2.setPower(vexIntakePow2);
            telemetry.addData("vexIntake1 Pow: ",robot.vexIntake1.getPower());
            telemetry.addData("vexIntake2 Pow: ", robot.vexIntake2.getPower());
        }

        if (enableIntakeArms)
        {
            telemetry.addData("armIntake1 Pow: ", robot.intakeArm2.getPosition());
            telemetry.addData("armIntake2 Pow: ", robot.intakeArm2.getPosition());
        }

        if (enableWinch)
        {
            robot.winchMotor1.setPower(winchPower);
            robot.winchMotor2.setPower(winchPower);
            telemetry.addData("winchPow1 Pow: ", robot.winchMotor1.getPower());
            telemetry.addData("winchPow2 Pow: ", robot.winchMotor2.getPower());
        }




    }

    @Override
    public void stop()
    {
        /*
            robot.vexIntake1.setPower(0);
            robot.vexIntake2.setPower(0);
*/
    }
}
