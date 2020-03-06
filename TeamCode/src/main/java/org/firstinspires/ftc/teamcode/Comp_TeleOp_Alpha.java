package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="TeleOp - Comp - Alpha", group="LinearOpMode")

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
    Servo hookers;

    //Servo positions
    final double OPEN_CLAW = 1;
    final double CLOSED_CLAW = 0;
    final double UP = 1;
    final double DOWN = 0.5;

    //Motor powers
    final double WHEELPOWER = 1;
    final double ARMPOWER = 0.8;
    final double WRISTPOWER = 0.5;

    double STRAFECOEFFICIENT = 0;
    double POWERCOEFFICIENT = 1; //Changed from constant because I am using to control the speed of the robot -Devin

    boolean twoPlayersActive = true;

    String playerMode;

    boolean isNormalSpeed = true;


    @Override
    public void init()
    {
        telemetry.addData("SYSTEMS", "initializing...");
        telemetry.update();

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

        hookers = hardwareMap.servo.get("hookers");
        hookers.setPosition(UP);

        telemetry.addData("SYSTEMS", "ready.");
        telemetry.update();
    }

    @Override
    public void loop()
    {
        if (twoPlayersActive)
        {
            playerMode = "Two Players";
        }
        else
        {
            playerMode = "One Player";
        }

        telemetry.addData("PlayerMode:", playerMode);
        telemetry.update();

        arm.setPower(0);
        wrist.setPower(0);
        double frontLeftPower = gamepad1.left_stick_y;
        double backLeftPower = gamepad1.left_stick_y;
        double frontRightPower = gamepad1.right_stick_y;
        double backRightPower = gamepad1.right_stick_y;
        frontLeft.setPower(frontLeftPower * POWERCOEFFICIENT);
        backLeft.setPower(backLeftPower * POWERCOEFFICIENT);
        frontRight.setPower(frontRightPower * POWERCOEFFICIENT);
        backRight.setPower(backRightPower * POWERCOEFFICIENT);

        if(gamepad1.dpad_right)
        {
            frontLeft.setPower(-WHEELPOWER * STRAFECOEFFICIENT);
            backLeft.setPower(WHEELPOWER * STRAFECOEFFICIENT);
            frontRight.setPower(WHEELPOWER * STRAFECOEFFICIENT);
            backRight.setPower(-WHEELPOWER * STRAFECOEFFICIENT);
        }
        if(gamepad1.dpad_left)
        {
            frontLeft.setPower(WHEELPOWER * STRAFECOEFFICIENT);
            backLeft.setPower(-WHEELPOWER * STRAFECOEFFICIENT);
            frontRight.setPower(-WHEELPOWER * STRAFECOEFFICIENT);
            backRight.setPower(WHEELPOWER * STRAFECOEFFICIENT);
        }
        if(gamepad1.b) { hookers.setPosition(DOWN);}
        if(gamepad1.x) { hookers.setPosition(UP);}

        if(gamepad1.start && gamepad1.x) { twoPlayersActive = true; }
        if(gamepad1.start && gamepad1.y) { twoPlayersActive = false; }

        //Changed robot speed -Devin
        if (gamepad1.dpad_up) {isNormalSpeed = true; POWERCOEFFICIENT = 1.0; STRAFECOEFFICIENT = 1;}
        if (gamepad1.dpad_down) {isNormalSpeed = false; POWERCOEFFICIENT = 0.25; STRAFECOEFFICIENT = 0.5;}


        if (twoPlayersActive)
        {
            if (gamepad1.left_trigger > 0 || gamepad2.left_trigger > 0) {
                // Release Block
                right_claw.setPosition(OPEN_CLAW);
                left_claw.setPosition(OPEN_CLAW);
            }
            if (gamepad1.right_trigger > 0 || gamepad2.right_trigger > 0) {
                // Grab Block
                right_claw.setPosition(CLOSED_CLAW);
                left_claw.setPosition(CLOSED_CLAW);
            }

            wrist.setPower(gamepad2.right_stick_y * 0.5);
            arm.setPower(gamepad2.left_stick_y);
        }
        else
        {
            if (gamepad1.dpad_up)
            {
                wrist.setPower(WRISTPOWER);
            }
            if (gamepad1.dpad_down)
            {
                wrist.setPower(-WRISTPOWER);
            }
            while(gamepad1.y)
            {
                arm.setPower(ARMPOWER);
            }
            while(gamepad1.a)
            {
                arm.setPower(-ARMPOWER);
            }

            if (gamepad1.left_trigger > 0)
            {
                // Release Block
                right_claw.setPosition(OPEN_CLAW);
                left_claw.setPosition(OPEN_CLAW);
            }
            if (gamepad1.right_trigger > 0)
            {
                // Grab Block
                right_claw.setPosition(CLOSED_CLAW);
                left_claw.setPosition(CLOSED_CLAW);
            }
        }
    }
}
