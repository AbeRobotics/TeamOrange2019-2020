package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TaskBase_Beta
{

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    DcMotor lift;

    Servo claw;

    Direction forward = Direction.Forward;
    Direction backward = Direction.Backward;
    Direction left = Direction.Left;
    Direction right = Direction.Right;

    Position open = Position.Open;
    Position closed = Position.Closed;

    final double OPEN_CLAW = 0;
    final double CLOSED_CLAW = 1;

    protected double wheelPower = 1;

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

    public enum Position
    {
        Open, Closed
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

        lift = hardwareMap.dcMotor.get("lift");

        claw = hardwareMap.servo.get("claw");
        claw.setDirection(Servo.Direction.REVERSE);
        //claw.setPosition(claw.getPosition());

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

    public void move(Direction direction)
    {
        switch (direction)
        {
            case Forward:
                telemetry.addData("Forward Called Test", "True");
                telemetry.update();
                frontLeft.setPower(wheelPower);
                frontRight.setPower(wheelPower);
                backLeft.setPower(wheelPower);
                backRight.setPower(wheelPower);
                break;
            case Backward:
                telemetry.addData("Backward Called Test", "True");
                telemetry.update();
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

    public void setClawPosition(Position p)
    {
        switch (p)
        {
            case Open:
                claw.setPosition(OPEN_CLAW);
                break;
            case Closed:
                claw.setPosition(CLOSED_CLAW);
                break;
        }
    }

    public void performTask(Speed speed)
    {
        switch (speed)
        {
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