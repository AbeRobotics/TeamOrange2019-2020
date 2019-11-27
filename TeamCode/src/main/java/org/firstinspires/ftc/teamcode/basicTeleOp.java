package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="Basic TeleOp", group="LinearOpMode")

public class basicTeleOp extends OpMode
{
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    double leftPower;
    double rightPower;

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
    }

    @Override
    public void loop()
    {
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);

        while(gamepad1.left_stick_y != 0)
        {
            frontLeft.setPower(gamepad1.left_stick_y * POWERCOEFFICIENT);
            backLeft.setPower(gamepad1.left_stick_y * POWERCOEFFICIENT);
        }
        while(gamepad1.right_stick_y != 0)
        {
            frontRight.setPower(gamepad1.right_stick_y * POWERCOEFFICIENT);
            backRight.setPower(gamepad1.right_stick_y * POWERCOEFFICIENT);
        }
        while(gamepad1.dpad_left == true)
        {
            frontLeft.setPower(-1 * POWERCOEFFICIENT);
            backLeft.setPower(1 * POWERCOEFFICIENT);
            frontRight.setPower(1 * POWERCOEFFICIENT);
            backRight.setPower(-1 * POWERCOEFFICIENT);
        }
        while(gamepad1.dpad_right == true)
        {
            frontLeft.setPower(1 * POWERCOEFFICIENT);
            backLeft.setPower(-1 * POWERCOEFFICIENT);
            frontRight.setPower(-1 * POWERCOEFFICIENT);
            backRight.setPower(1 * POWERCOEFFICIENT);
        }

    }
}
