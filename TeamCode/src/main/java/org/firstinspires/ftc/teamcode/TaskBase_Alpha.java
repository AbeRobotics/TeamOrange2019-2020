package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TaskBase_Alpha extends TaskBase
{

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    DcMotor arm;
    DcMotor wrist;
    Servo lift;

    Servo right_claw;
    Servo left_claw;

    final double OPEN_CLAW = 0.5;
    final double CLOSED_CLAW = 0;
    final double UP = 0.7;
    final double DOWN = 1;

    Position open = Position.Open;
    Position closed = Position.Closed;

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

        lift = hardwareMap.servo.get("lift");
        lift.setPosition(UP);
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
}