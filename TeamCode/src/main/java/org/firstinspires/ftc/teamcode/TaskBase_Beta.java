package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TaskBase_Beta
{

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    DcMotor lift;

    Servo claw;

    final double OPEN_CLAW = 0;
    final double CLOSED_CLAW = 1;

    public double wheelPower = 1;

    public Telemetry telemetry;
    private HardwareMap hardwareMap;

    public enum Speed
    {
        Fast, Intermediate, Slow;
    }

    public enum Direction
    {
        Left, Right, Forward, Backward;
    }

    public void Init(Telemetry telemetry, HardwareMap hardwareMap)
    {

        this.telemetry = telemetry;
        this.hardwareMap = hardwareMap;

        this.frontLeft = hardwareMap.dcMotor.get("front_left");
        this.frontRight = hardwareMap.dcMotor.get("front_right");
        this.frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        this.backLeft = hardwareMap.dcMotor.get("back_left");
        this.backRight = hardwareMap.dcMotor.get("back_right");
        this.backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        lift = hardwareMap.dcMotor.get("lift");

        claw = hardwareMap.servo.get("claw");
        claw.setDirection(Servo.Direction.REVERSE);
        //claw.setPosition(OPEN_CLAW);

        this.wheelPower = wheelPower;
    }

    public void halfTurn(Direction direction)
    {
        int directionCoefficient = 1;

        switch (direction)
        {
            case Left:
                directionCoefficient = 1;
            case Right:
                directionCoefficient = -1;
        }

        frontLeft.setPower(-1 * directionCoefficient);
        frontRight.setPower(-1 * directionCoefficient);
        backLeft.setPower(1 * directionCoefficient);
        backRight.setPower(1 * directionCoefficient);
    }

    public void move(Direction direction)
    {
        int directionCoefficient = 1;

        switch (direction)
        {
            case Forward:
                directionCoefficient = 1;
            case Backward:
                directionCoefficient = -1;
        }
    }

    public void performTask(Speed speed)
    {
        switch (speed)
        {
            case Fast:
                wheelPower = 1;

            case Slow:
                wheelPower = 0.2;

            case Intermediate:
                wheelPower = 0.5;

        }
    }

    public final void sleep(long milliseconds)
    {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public final void resetMotors()
    {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
}