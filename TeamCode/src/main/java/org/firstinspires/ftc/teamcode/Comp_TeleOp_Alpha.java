package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="TeleOp - Alpha", group="LinearOpMode")

public class Comp_TeleOp_Alpha extends OpMode
{
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    DcMotor arm;
    DcMotor wrist;

    Servo right_claw;
    Servo left_claw;

    final double OPEN_CLAW = 0.5;
    final double CLOSED_CLAW = 0;

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

        arm = hardwareMap.dcMotor.get("arm");
        wrist = hardwareMap.dcMotor.get("wrist");

        right_claw = hardwareMap.servo.get("right_claw");
        right_claw.setDirection(Servo.Direction.REVERSE);
        left_claw = hardwareMap.servo.get("left_claw");
        right_claw.setPosition(CLOSED_CLAW);
        left_claw.setPosition(CLOSED_CLAW);
    }

    @Override
    public void loop()
    {
        arm.setPower(0);
        wrist.setPower(0);

        frontLeft.setPower(gamepad1.left_stick_y * POWERCOEFFICIENT);
        backLeft.setPower(gamepad1.left_stick_y * POWERCOEFFICIENT);
        frontRight.setPower(gamepad1.right_stick_y * POWERCOEFFICIENT);
        backRight.setPower(gamepad1.right_stick_y * POWERCOEFFICIENT);

        if(gamepad1.dpad_right == true)
        {
            frontLeft.setPower(-1 * POWERCOEFFICIENT);
            backLeft.setPower(1 * POWERCOEFFICIENT);
            frontRight.setPower(1 * POWERCOEFFICIENT);
            backRight.setPower(-1 * POWERCOEFFICIENT);
        }
        if(gamepad1.dpad_left == true)
        {
            frontLeft.setPower(1 * POWERCOEFFICIENT);
            backLeft.setPower(-1 * POWERCOEFFICIENT);
            frontRight.setPower(-1 * POWERCOEFFICIENT);
            backRight.setPower(1 * POWERCOEFFICIENT);
        }

        if (gamepad1.left_trigger > 0 || (gamepad2.left_trigger > 0 && !(gamepad1.right_trigger > 0)))
        {
            // Release Block
            right_claw.setPosition(OPEN_CLAW);
            left_claw.setPosition(OPEN_CLAW);
        }
        if (gamepad1.right_trigger > 0 || (gamepad2.right_trigger > 0 && !(gamepad1.left_trigger > 0)))
        {
            // Grab Block
            right_claw.setPosition(CLOSED_CLAW);
            left_claw.setPosition(CLOSED_CLAW);
        }

        wrist.setPower(gamepad2.right_stick_y);
        arm.setPower(gamepad2.left_stick_y);

        //DEPRECATED SOLO CONTROLS

        /*
        if (gamepad1.dpad_up)
        {
            wrist.setPower(1);
        }
        if (gamepad1.dpad_down)
        {
            wrist.setPower(-1);
        }
        while(gamepad1.y == true)
        {
            arm.setPower(1);
        }
        while(gamepad1.a == true)
        {
            arm.setPower(-1);
        }
        */
    }
}
