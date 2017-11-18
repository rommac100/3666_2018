package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by rommac100 on 9/10/17.
 */
@TeleOp(name="GyroTelemetry", group = "Testing")
public class GyroTelemetry extends LinearOpMode {

    public Internal_Gyroscope gyroscope;

    @Override
    public void runOpMode() throws InterruptedException {

            gyroscope = new Internal_Gyroscope();
        gyroscope.init(hardwareMap.appContext);




        waitForStart();
        while(opModeIsActive())
        {
            telemetry.addData("X: ", gyroscope.getRotation()[0]);
            telemetry.addData("Y: ", gyroscope.getRotation()[1]);
            telemetry.addData("Z: ", gyroscope.getRotation()[2]);

            Thread.sleep(5);
            telemetry.update();
        }
    }
}
