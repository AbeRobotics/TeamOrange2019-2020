package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class TaskBase
{

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    Direction forward = Direction.Forward;
    Direction backward = Direction.Backward;
    Direction left = Direction.Left;
    Direction right = Direction.Right;

    protected double wheelPower = 1;
    protected double armPower = 1;

    public Telemetry telemetry;
    private HardwareMap hardwareMap;

    public enum wheelSpeed
    {
        Fast, Intermediate, Slow
    }

    public enum armSpeed
    {
        Fast, Intermediate, Slow
    }

    public enum Direction
    {
        Left, Right, Forward, Backward
    }

    public enum Team
    {
        Red, Blue
    }

    public void Init(Telemetry telemetry, HardwareMap hardwareMap)
    {

        this.telemetry = telemetry;
        this.hardwareMap = hardwareMap;

        //Initialize Wheels

        this.frontLeft = hardwareMap.dcMotor.get("front_left");
        this.frontRight = hardwareMap.dcMotor.get("front_right");
        this.backLeft = hardwareMap.dcMotor.get("back_left");
        this.backRight = hardwareMap.dcMotor.get("back_right");

        //Reverse Wheels

        this.backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        this.frontRight.setDirection(DcMotorSimple.Direction.REVERSE);

        this.wheelPower = wheelPower;
    }

    public void halfTurn(Direction direction)
    {
        int directionCoefficient = 0;

        switch (direction)
        {
            case Left:
                directionCoefficient = 1;
                break;
            case Right:
                directionCoefficient = -1;
                break;
        }

        frontLeft.setPower(-wheelPower * directionCoefficient);
        frontRight.setPower(wheelPower * directionCoefficient);
        backLeft.setPower(-wheelPower * directionCoefficient);
        backRight.setPower(wheelPower * directionCoefficient);

        sleep(1500);
    }

    public void turn(Direction direction)
    {
        int directionCoefficient = 0;

        switch (direction)
        {
            case Left:
                directionCoefficient = 1;
                break;
            case Right:
                directionCoefficient = -1;
                break;
        }

        frontLeft.setPower(-wheelPower * directionCoefficient);
        frontRight.setPower(wheelPower * directionCoefficient);
        backLeft.setPower(-wheelPower * directionCoefficient);
        backRight.setPower(wheelPower * directionCoefficient);
    }

    public void move(Direction direction)
    {
        switch (direction)
        {
            case Backward:
                frontLeft.setPower(wheelPower);
                frontRight.setPower(wheelPower);
                backLeft.setPower(wheelPower);
                backRight.setPower(wheelPower);
                break;
            case Forward:
                frontLeft.setPower(-wheelPower);
                frontRight.setPower(-wheelPower);
                backLeft.setPower(-wheelPower);
                backRight.setPower(-wheelPower);
                break;
            case Left:
                frontLeft.setPower(-wheelPower);
                frontRight.setPower(wheelPower);
                backLeft.setPower(wheelPower);
                backRight.setPower(-wheelPower);
                break;
            case Right:
                frontLeft.setPower(wheelPower);
                frontRight.setPower(-wheelPower);
                backLeft.setPower(-wheelPower);
                backRight.setPower(wheelPower);
                break;
        }
    }

    public void performTask(wheelSpeed s1, armSpeed s2)
    {
        if (s1 != null)
        {
            switch (s1) {
                case Fast:
                    wheelPower = 1;
                    break;
                case Slow:
                    wheelPower = 0.2;
                    break;
                case Intermediate:
                    wheelPower = 0.5;
                    break;
            }
        }

        if (s2 != null)
        {
            switch (s2) {
                case Fast:
                    armPower = 1;
                    break;
                case Slow:
                    armPower = 0.2;
                    break;
                case Intermediate:
                    armPower = 0.5;
                    break;
            }
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