/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a K9 robot.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "left_drive"
 * Motor channel:  Right drive motor:        "right_drive"
 * Servo channel:  Servo to raise/lower arm: "arm"
 * Servo channel:  Servo to open/close claw: "claw"
 *
 * Note: the configuration of the servos is such that:
 *   As the arm servo approaches 0, the arm position moves up (away from the floor).
 *   As the claw servo approaches 0, the claw opens up (drops the game element).
 */
public class Hardware3666
{
    /* Public OpMode members. */
    public DcMotor leftDrive1   = null;
    public DcMotor rightDrive1  = null;
    public DcMotor leftDrive2 = null;
    public DcMotor rightDrive2 = null;

    public DcMotor winchMotor1 = null;
    public DcMotor winchMotor2 = null;

    public CRServo vexIntake1 = null;
    public CRServo vexIntake2 = null;

    public Servo intakeArm1 = null;
    public Servo intakeArm2 = null;

    public Servo colourArmServo = null;

    public ColorSensor armColourSensor = null;

    public BNO055IMU imu = null;

    private static final boolean enableDrive = true;
    private static final boolean enableVex = true;
    private static final boolean enableIntakeArms = true;
    public static final boolean enableColourArm = true;
    public static final boolean enableWinch = true;
    public static final boolean enableIMU = true;

    private static final double wheelDiameterInches = 2.5;
    private static final int ticksPerRev = 538;

    public static final double ticksPerInch = ticksPerRev/(wheelDiameterInches*Math.PI);

    /* Local OpMode members. */
    HardwareMap hwMap  = null;
    private ElapsedTime period  = new ElapsedTime();

    public enum Direction {
        FORWARD (+.8),
        BACKWARD (.8),
        CLOCKWISE (+1.0),
        COUNTERCLOCKWISE (-1.0);
        public final double value;
        Direction (double value) {this.value = value;}
    }


    /* Constructor */
    public Hardware3666() {
    }



    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // save reference to HW Map
        hwMap = ahwMap;

        // Define and Initialize Motors
        if (enableDrive) {
            leftDrive1 = hwMap.get(DcMotor.class, "leftDrive1");
            rightDrive1 = hwMap.get(DcMotor.class, "rightDrive1");
            leftDrive2 = hwMap.get(DcMotor.class, "leftDrive2");
            rightDrive2 = hwMap.get(DcMotor.class, "rightDrive2");
            leftDrive1.setDirection(DcMotor.Direction.FORWARD);
            leftDrive2.setDirection(DcMotor.Direction.FORWARD);
            rightDrive1.setDirection(DcMotor.Direction.REVERSE);
            rightDrive2.setDirection(DcMotor.Direction.REVERSE);
            leftDrive1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightDrive1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            leftDrive2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightDrive2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            leftDrive1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            leftDrive2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            rightDrive1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            rightDrive2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            leftDrive1.setPower(0);
            rightDrive1.setPower(0);
            leftDrive2.setPower(0);
            rightDrive2.setPower(0);
        }

        if (enableVex)
        {
            vexIntake1 = hwMap.get(CRServo.class, "vexIntake1");
            vexIntake2 = hwMap.get(CRServo.class, "vexIntake2");
            vexIntake1.setDirection(CRServo.Direction.FORWARD);
            vexIntake2.setDirection(CRServo.Direction.REVERSE);
            vexIntake1.setPower(0);
            vexIntake2.setPower(0);
        }


        if (enableIntakeArms)
        {
            intakeArm1 = hwMap.get(Servo.class, "intakeArm1");
            intakeArm2 = hwMap.get(Servo.class, "intakeArm2");
            intakeArm1.setPosition(0);
            intakeArm2.setPosition(1);
        }


        if (enableColourArm)
        {
            colourArmServo = hwMap.get(Servo.class, "colourArm");
            colourArmServo.setPosition(Servo.MIN_POSITION);
            armColourSensor = hwMap.colorSensor.get("colourSen");
        }

        if (enableWinch)
        {
            winchMotor1 = hwMap.get(DcMotor.class, "winchMotor1");
            winchMotor2 = hwMap.get(DcMotor.class, "winchMotor2");
            winchMotor1.setPower(0);
            winchMotor2.setPower(0);
        }

    }

    public void setDrivePow(double left, double right)
    {
        leftDrive1.setPower(left);
        leftDrive2.setPower(left);

        rightDrive1.setPower(right);
        rightDrive2.setPower(right);
    }

    public void initArmServo()
    {
        intakeArm1 = hwMap.get(Servo.class, "intakeArm1");
        intakeArm2 = hwMap.get(Servo.class, "intakeArm2");
        intakeArm1.setPosition(0);
        intakeArm2.setPosition(1);
    }

    public int distanceInches(double distanceInches)
    {
        return (int) (distanceInches*ticksPerInch);
    }

    public boolean enableDriveStatus()
    {
        return enableDrive;
    }

    public boolean enableVexStatus()
    {
        return enableVex;
    }

    public boolean enableIntakeArmsStatus()
    {
        return enableIntakeArms;
    }
}