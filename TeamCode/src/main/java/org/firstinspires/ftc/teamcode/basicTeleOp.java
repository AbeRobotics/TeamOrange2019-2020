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
            frontLeft.setPower(gamepad1.left_stick_y);
            backLeft.setPower(gamepad1.left_stick_y);
            frontRight.setPower(gamepad1.left_stick_y);
            backRight.setPower(gamepad1.left_stick_y);
        }
        while(gamepad1.left_stick_x != 0)
        {
            frontLeft.setPower(gamepad1.left_stick_x);
            backLeft.setPower(gamepad1.left_stick_x);
            frontRight.setPower(gamepad1.left_stick_x * -1);
            backRight.setPower(gamepad1.left_stick_x * -1);
        }
        while(gamepad1.right_stick_x != 0)
        {
            frontLeft.setPower(gamepad1.right_stick_x * -1);
            backLeft.setPower(gamepad1.right_stick_x);
            frontRight.setPower(gamepad1.right_stick_x);
            backRight.setPower(gamepad1.right_stick_x * -1);
        }

    }
}
