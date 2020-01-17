package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class TaskBase_Alpha extends TaskBase
{

    DcMotor frontLeft, frontRight, backLeft, backRight, arm, wrist;

    Servo dragger, right_claw, left_claw;

    ColorSensor colorSensor;

    BNO055IMU imu;

    Orientation lastAngles = new Orientation();
    double globalAngle, power = .30, correction;

    final double OPEN_CLAW = 0.5, CLOSED_CLAW = 0, UP = 0.7, DOWN = 1;

    Position open = Position.Open, closed = Position.Closed;

    public Telemetry telemetry;
    private HardwareMap hardwareMap;

    public enum Position
    {
        Open, Closed
    }
    public enum Movement
    {
        Up, Down
    }

    public void Init(Telemetry telemetry, HardwareMap hardwareMap)
    {

        this.telemetry = telemetry;
        this.hardwareMap = hardwareMap;

        frontLeft = hardwareMap.dcMotor.get("front_left");
        frontRight = hardwareMap.dcMotor.get("front_right");
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft = hardwareMap.dcMotor.get("back_left");
        backRight = hardwareMap.dcMotor.get("back_right");
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        arm = hardwareMap.dcMotor.get("arm");
        wrist = hardwareMap.dcMotor.get("wrist");

        right_claw = hardwareMap.servo.get("right_claw");
        right_claw.setDirection(Servo.Direction.REVERSE);
        left_claw = hardwareMap.servo.get("left_claw");
        right_claw.setPosition(CLOSED_CLAW);
        left_claw.setPosition(CLOSED_CLAW);

        dragger = hardwareMap.servo.get("dragger");
        dragger.setPosition(UP);

        colorSensor = hardwareMap.get(ColorSensor.class, "sensor_color");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode                = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = false;

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
        telemetry.addData("Mode", "calibrating...");
        telemetry.update();
        while (!imu.isGyroCalibrated())
        {
            sleep(50);
        }
    }

    public void setClawPosition(Position p)
    {
        switch (p)
        {
            case Open:
                left_claw.setPosition(OPEN_CLAW);
                right_claw.setPosition(OPEN_CLAW);
                break;
            case Closed:
                left_claw.setPosition(CLOSED_CLAW);
                right_claw.setPosition(CLOSED_CLAW);
                break;
        }
    }

    public void setDraggerPosition(Position p)
    {
        switch (p)
        {
            case Open:
                dragger.setPosition(UP);
                break;
            case Closed:
                dragger.setPosition(DOWN);
                break;
        }
    }

    public void moveArm(Movement m, int t)
    {
        switch (m)
        {
            case Up:
                arm.setPower(armPower);
                sleep(t);
                resetMotors();
                break;
            case Down:
                arm.setPower(-armPower);
                sleep(t);
                resetMotors();
                break;
        }
    }


}