package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TaskBase
{

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

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

    public enum Position
    {
        Open, Closed
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

        this.wheelPower = wheelPower;
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