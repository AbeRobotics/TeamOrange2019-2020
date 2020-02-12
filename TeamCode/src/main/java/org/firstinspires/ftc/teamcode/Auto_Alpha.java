package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Auto - Alpha (TESTING)", group="Autonomous")
public class Auto_Alpha extends LinearOpMode
{
    private ElapsedTime runTime = new ElapsedTime();

    DcMotor frontLeft, frontRight, backLeft, backRight, arm, wrist;

    Servo dragger, right_claw, left_claw;

    ColorSensor colorSensor;

    ElapsedTime movementTracker = new ElapsedTime();

    BNO055IMU imu;

    final double OPEN_CLAW = 0.5, CLOSED_CLAW = 0, UP = 0.7, DOWN = 1;

    @Override
    public void runOpMode() throws InterruptedException
    {
        frontLeft = hardwareMap.dcMotor.get("front_left");
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight = hardwareMap.dcMotor.get("front_right");
        backLeft = hardwareMap.dcMotor.get("back_left");
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight = hardwareMap.dcMotor.get("back_right");

        telemetry.addData("Wheels:", "Initialized");
        telemetry.update();

        arm = hardwareMap.dcMotor.get("arm");
        wrist = hardwareMap.dcMotor.get("wrist");

        telemetry.addData("Arm:", "Initialized");
        telemetry.update();

        right_claw = hardwareMap.servo.get("right_claw");
        right_claw.setDirection(Servo.Direction.REVERSE);
        left_claw = hardwareMap.servo.get("left_claw");
        right_claw.setPosition(CLOSED_CLAW);
        left_claw.setPosition(CLOSED_CLAW);

        dragger = hardwareMap.servo.get("dragger");
        dragger.setPosition(UP);

        telemetry.addData("Servos:", "Initialized");
        telemetry.update();

        colorSensor = hardwareMap.get(ColorSensor.class, "sensor_color");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode                = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = false;

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
        telemetry.addData("IMU", "calibrating...");
        telemetry.update();
        while (!imu.isGyroCalibrated() && !isStopRequested())
        {
            sleep(100);
        }
        telemetry.addData("IMU", "calibrated.");
        telemetry.update();

        waitForStart();

        frontLeft.setPower(1);
        backLeft.setPower(1);
        frontRight.setPower(1);
        backRight.setPower(1);
        sleep(1330);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);

        sleep(2000);
        dragger.setPosition(DOWN);
        sleep(500);

        frontLeft.setPower(-1);
        backLeft.setPower(-1);
        frontRight.setPower(-1);
        backRight.setPower(-1);
        sleep(1420);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);

        sleep(1000);

        sleep(2000);
        dragger.setPosition(UP);
        sleep(1000);

        frontLeft.setPower(1);
        backLeft.setPower(-1);
        frontRight.setPower(-1);
        backRight.setPower(1);
        sleep((2700));
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
    }
}
