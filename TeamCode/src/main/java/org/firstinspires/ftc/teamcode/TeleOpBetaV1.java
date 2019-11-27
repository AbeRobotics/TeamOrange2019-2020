package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Basic TeleOp", group="LinearOpMode")

public class TeleOpBetaV1 extends OpMode
{
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor arm;
    DcMotor wrist;
    Servo right_claw;
    Servo left_claw;

    double leftPower;
    double rightPower;

    static final double OPEN_CLAW = 0.4;
    static final double CLOSED_CLAW = 0.5;


    @Override
    public void init()
    {
        frontLeft = hardwareMap.dcMotor.get("front_left");
        frontRight = hardwareMap.dcMotor.get("front_right");
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft = hardwareMap.dcMotor.get("back_left");
        backRight = hardwareMap.dcMotor.get("back_right");
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        arm = hardwareMap.dcMotor.get("arm_motor");
        wrist = hardwareMap.dcMotor.get("wrist_motor");
        right_claw = hardwareMap.servo.get("right_claw");
        //right_claw.setDirection(Servo.Direction.REVERSE);
        left_claw = hardwareMap.servo.get("left_claw");

        //TODO Reverse(s)?
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
        while(gamepad1.left_trigger != 0)
        {
            //Rotate left
            frontLeft.setPower(gamepad1.left_trigger * -1);
            backLeft.setPower(gamepad1.left_trigger);
            frontRight.setPower(gamepad1.left_trigger);
            backRight.setPower(gamepad1.left_trigger * -1);
        }
        while (gamepad1.right_trigger != 0)
        {
            //Rotate right
            frontLeft.setPower(gamepad1.right_trigger);
            backLeft.setPower(gamepad1.right_trigger * -1);
            frontRight.setPower(gamepad1.right_trigger * -1);
            backRight.setPower(gamepad1.right_trigger);
        }
        while (gamepad1.a)
        {
            // Release Block
            right_claw.setPosition(OPEN_CLAW);
            left_claw.setPosition(OPEN_CLAW);
        }
        while (gamepad1.b)
        {
            // Grab Block
            right_claw.setPosition(CLOSED_CLAW);
            left_claw.setPosition(CLOSED_CLAW);
        }
        while (gamepad1.right_stick_y != 0)
        {
            // Lift Block
            arm.setPower(gamepad1.right_stick_y);
        }
        while (gamepad1.dpad_up)
        {
            wrist.setPower(1);
        }
        while (gamepad1.dpad_down)
        {
            wrist.setPower(-1);
        }
    }
}
