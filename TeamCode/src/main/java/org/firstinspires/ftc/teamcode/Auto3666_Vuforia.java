package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.io.Closeable;

/**
 * Created by rommac100 on 11/17/17.
 */
@Autonomous(name="auto_vuforia_test")
public class Auto3666_Vuforia extends Auto3666 {

    OpenGLMatrix lastLocation = null;

    VuforiaLocalizer vuforia;

    VuforiaTrackables relicTrackables;
    VuforiaTrackable relicTemplate;

    boolean driveForward = true;


    public void initVuforia() {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AVVR6lD/////AAAAGaBoMQxcC0SLtMheM5OtO54R1JrkfN5XmPfiD6JY3kjv2FpL89mvwGDfdIjGHNn3IX0hqmsQShWJTFNUoZfdqIdwk4vc6KQhttMW7KG+2P+45CsiWNqQDC5K+N7rjDJFGXuMevVOr3ZzqV4Fz75qsLw50v8hEFSO8XodWGnYNpvKyR0qmQlr2Bn87n/Ket1FVn/5vFnmrOphctH1QOTk7JPSdW8z89Ag7cVgIbEUBThve0VqW9Ygh1yMm2m6RorTuGBxmTnanAwpoRCFI27j8cZGAXoELNeFPkkASTOc3Wy8pOOQ+zjKzS8HT41q0hdx+PNWHEnD5DduKsu/LzM+gbjyqdRxnYLAP6IVNxMVBA9L";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);

    }

    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }

    @Override
    public void runOpMode() throws InterruptedException {
        initRobot();
        initVuforia();
        telemetry.addData("Robot Init", "");
        telemetry.update();


        waitForStart();
        relicTrackables.activate();
        while (opModeIsActive()) {
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);

            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
                telemetry.addData("VuMark", "%s visible", vuMark);

                /* For fun, we also exhibit the navigational pose. In the Relic Recovery game,
                 * it is perhaps unlikely that you will actually need to act on this pose information, but
                 * we illustrate it nevertheless, for completeness. */
                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) relicTemplate.getListener()).getPose();
                telemetry.addData("Pose", format(pose));

                /* We further illustrate how to decompose the pose into useful rotational and
                 * translational components */
                if (pose != null) {
                    VectorF trans = pose.getTranslation();
                    Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

                    // Extract the X, Y, and Z components of the offset of the target relative to the robot
                    double tX = trans.get(0);
                    double tY = trans.get(1);
                    double tZ = trans.get(2);

                    // Extract the rotational components of the target relative to the robot
                    double rX = rot.firstAngle;
                    double rY = rot.secondAngle;
                    double rZ = rot.thirdAngle;

                    if (opModeIsActive() && tX < 200 && driveForward)
                    {
                        robot.setDrivePow(.8,.8);
                        telemetry.update();
                    }
                    else
                    {
                        robot.setDrivePow(0,0);
                        driveForward = false;
                    }


                }
                else {
                    telemetry.addData("VuMark", "not visible");
                }
                telemetry.update();
                idle();
            }

        }

    }
}
