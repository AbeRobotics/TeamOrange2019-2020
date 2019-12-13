package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="TeleOp - Beta", group="LinearOpMode")

public class Comp_TeleOp_Beta extends OpMode
{
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    DcMotor lift;

    Servo claw;

    final double OPEN_CLAW = 0.3;
    final double CLOSED_CLAW = 1;

    final double POWERCOEFFICIENT = 1.5;

    @Override
    public void init()
    {
        frontLeft = hardwareMap.dcMotor.get("front_left");
        frontRight = hardwareMap.dcMotor.get("front_right");
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft = hardwareMap.dcMotor.get("back_left");
        backRight = hardwareMap.dcMotor.get("back_right");
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        lift = hardwareMap.dcMotor.get("lift");

        claw = hardwareMap.servo.get("claw");
        claw.setDirection(Servo.Direction.REVERSE);

        boolean open = false;
    }

    @Override
    public void loop()
    {
        lift.setPower(0);

        frontLeft.setPower(gamepad1.left_stick_y * POWERCOEFFICIENT);
        backLeft.setPower(gamepad1.left_stick_y * POWERCOEFFICIENT);
        frontRight.setPower(gamepad1.right_stick_y * POWERCOEFFICIENT);
        backRight.setPower(gamepad1.right_stick_y * POWERCOEFFICIENT);

        while(gamepad1.dpad_right == true)
        {
            frontLeft.setPower(-1 * POWERCOEFFICIENT);
            backLeft.setPower(1 * POWERCOEFFICIENT);
            frontRight.setPower(1 * POWERCOEFFICIENT);
            backRight.setPower(-1 * POWERCOEFFICIENT);
        }
        while(gamepad1.dpad_left == true)
        {
            frontLeft.setPower(1 * POWERCOEFFICIENT);
            backLeft.setPower(-1 * POWERCOEFFICIENT);
            frontRight.setPower(-1 * POWERCOEFFICIENT);
            backRight.setPower(1 * POWERCOEFFICIENT);
        }
        if(gamepad1.a == true)
        {
            lift.setPower(0.5);
        }
        else if(gamepad1.y == true)
        {
            lift.setPower(-0.5);
        }
        else
        {
            lift.setPower(0);
        }

        if(gamepad1.b)
        {
            claw.setPosition(CLOSED_CLAW);
        }
        if(gamepad1.x)
        {
            claw.setPosition(OPEN_CLAW);
        }

    }
}
