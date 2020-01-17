package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class TaskBase_Beta extends TaskBase
{

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    DcMotor lift;

    Servo claw;

    Position open = Position.Open;
    Position closed = Position.Closed;

    final double OPEN_CLAW = 0;
    final double CLOSED_CLAW = 1;

    public Telemetry telemetry;
    private HardwareMap hardwareMap;

    public enum Position
    {
        Open, Closed
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

        this.wheelPower = wheelPower;
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
}