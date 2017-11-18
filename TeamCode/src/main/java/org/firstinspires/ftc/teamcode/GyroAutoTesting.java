package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.util.Locale;

/**
 * Created by rommac100 on 9/27/17.
 */
@Autonomous(group="Omni_Bot", name="GyroAuto")
public class GyroAutoTesting extends LinearOpMode {
    HardwareOmni robot = new HardwareOmni();
    BNO055IMU imu;
    Orientation angles;
    Acceleration gravity;

    double kp = .005;


    public void initIMU()
    {
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        composeTelemetry();
    }

    public enum Direction {
        FORWARD (+1.0),
        BACKWARD (-1.0),
        CLOCKWISE (+1.0),
        COUNTERCLOCKWISE (-1.0);
        public final double value;
        Direction (double value) {this.value = value;}
    }

    public double getHeading() {return angles.firstAngle;}

    public double adjustAngle(double angle) {
        while (angle > 180) angle -= 360;
        while (angle <= -180) angle += 360;
        return angle;
    }

    public void turnP(double degrees, Direction direction, double timeout, double speed, double kp) {
        double targetAngle = adjustAngle(getHeading() + direction.value * degrees);
        double error;
        double power;

        do {
            error = adjustAngle(targetAngle - getHeading());
            power = kp * error;
            power = Range.clip(power, -speed, +speed);
            rotatePower(-power);
            idle();
            telemetry.update();
        } while (opModeIsActive() && error > 0.5);
       rotatePower(0);
    }


    public void initRobot()
    {
        robot.init(hardwareMap);
        telemetry.addData("Say", "GyroAuto Init");
        robot.setUpMotorPower(0);
        robot.leftMotor.setPower(0);

        initIMU();
    }

    public void rotatePower(double power)
    {
        robot.leftMotor.setPower(power);
        robot.rightMotor.setPower(power*-1);
        robot.upMotor.setPower(power*-1);
        robot.downMotor.setPower(power);
    }

    public void rotateAngle(double angle)
    {

        /*
        while (opModeIsActive() &&  imu.getSystemStatus().toShortString().equals("fusion"))
        {
            idle();
        }
        */

        while (opModeIsActive() &&  angles.firstAngle < angle)
        {
            rotatePower(-.15);

            telemetry.update();
           idle();
        }
        robot.setUpMotorPower(0);
        robot.setLeftMotorPower(0);
    }



    @Override
    public void runOpMode() throws InterruptedException {

        initRobot();
        telemetry.update();
        waitForStart();


        if(opModeIsActive())
        {
            sleep(1000);
            //rotateAngle(40);
            turnP(40, Direction.CLOCKWISE,4,.15,kp);
            telemetry.update();
        }
        robot.setUpMotorPower(0);
        robot.setLeftMotorPower(0);

    }

    void composeTelemetry() {

        // At the beginning of each telemetry update, grab a bunch of data
        // from the IMU that we will then display in separate lines.
        telemetry.addAction(new Runnable() { @Override public void run()
        {
            // Acquiring the angles is relatively expensive; we don't want
            // to do that in each of the three items that need that info, as that's
            // three times the necessary expense.
            angles   = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            gravity  = imu.getGravity();
        }
        });

        telemetry.addLine()
                .addData("status", new Func<String>() {
                    @Override public String value() {
                        return imu.getSystemStatus().toShortString();
                    }
                })
                .addData("calib", new Func<String>() {
                    @Override public String value() {
                        return imu.getCalibrationStatus().toString();
                    }
                });

        telemetry.addLine()
                .addData("heading", new Func<String>() {
                    @Override public String value() {
                        return formatAngle(angles.angleUnit, angles.firstAngle);
                    }
                })
                .addData("roll", new Func<String>() {
                    @Override public String value() {
                        return formatAngle(angles.angleUnit, angles.secondAngle);
                    }
                })
                .addData("pitch", new Func<String>() {
                    @Override public String value() {
                        return formatAngle(angles.angleUnit, angles.thirdAngle);
                    }
                });

        telemetry.addLine()
                .addData("grvty", new Func<String>() {
                    @Override public String value() {
                        return gravity.toString();
                    }
                })
                .addData("mag", new Func<String>() {
                    @Override public String value() {
                        return String.format(Locale.getDefault(), "%.3f",
                                Math.sqrt(gravity.xAccel*gravity.xAccel
                                        + gravity.yAccel*gravity.yAccel
                                        + gravity.zAccel*gravity.zAccel));
                    }
                });
    }

    //----------------------------------------------------------------------------------------------
    // Formatting
    //----------------------------------------------------------------------------------------------

    String formatAngle(AngleUnit angleUnit, double angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }

    String formatDegrees(double degrees){
        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
    }
}
