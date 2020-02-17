package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public abstract class AutoMethods_Alpha extends LinearOpMode
{
    private DcMotor frontLeft, frontRight, backLeft, backRight, arm, wrist;

    private Servo dragger, right_claw, left_claw;

    private ColorSensor colorSensor;

    private float hsvValues[] = {0F,0F,0F};

    private final float values[] = hsvValues;

    private BNO055IMU imu;

    private Orientation lastAngles = new Orientation();

    private double globalAngle, correction, wheelPower = 1, armPower = 1;

    private final double OPEN_CLAW = 0.5, CLOSED_CLAW = 0, UP = 0.7, DOWN = 1;

    private static final double     COUNTS_PER_MOTOR_REV    = 1440;  // eg: TETRIX Motor Encoder
    private static final double     DRIVE_GEAR_REDUCTION    = 2.0;     // This is < 1.0 if geared UP
    private static final double     WHEEL_DIAMETER_INCHES   = 4.0;     // For figuring circumference
    private static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

    private RobotDirection currentDirection;

    private static ElapsedTime runTime, encoderRunTime;

    RobotDirection forward = RobotDirection.Forward;
    RobotDirection backward = RobotDirection.Backward;
    RobotDirection left = RobotDirection.Left;
    RobotDirection right = RobotDirection.Right;
    RobotDirection rotating = RobotDirection.Rotating;

    ServoPosition open = ServoPosition.Open;
    ServoPosition closed = ServoPosition.Closed;
    ServoPosition up = ServoPosition.Open;
    ServoPosition down = ServoPosition.Closed;

    RobotTeam redLeft = RobotTeam.RedLeft;
    RobotTeam blueLeft = RobotTeam.BlueLeft;
    RobotTeam redRight = RobotTeam.RedRight;
    RobotTeam blueRight = RobotTeam.BlueRight;

    public enum AutoSpeed
    {
        Fast, Intermediate, Slow
    }

    public enum RobotDirection
    {
        Left, Right, Forward, Backward, Rotating
    }
    public enum ServoPosition
    {
        Open, Closed
    }
    public enum ArmMovement
    {
        Up, Down
    }
    public enum RobotTeam
    {
        RedLeft, RedRight, BlueLeft, BlueRight
    }

    public void activate() //Initialize all motors, servos and sensors
    {
        runTime = new ElapsedTime();
        encoderRunTime = new ElapsedTime();

        frontLeft = hardwareMap.dcMotor.get("front_left");
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight = hardwareMap.dcMotor.get("front_right");
        backLeft = hardwareMap.dcMotor.get("back_left");
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight = hardwareMap.dcMotor.get("back_right");

        arm = hardwareMap.dcMotor.get("arm");
        wrist = hardwareMap.dcMotor.get("wrist");

        right_claw = hardwareMap.servo.get("right_claw");
        right_claw.setDirection(Servo.Direction.REVERSE);
        left_claw = hardwareMap.servo.get("left_claw");
        right_claw.setPosition(CLOSED_CLAW);
        left_claw.setPosition(CLOSED_CLAW);

        dragger = hardwareMap.servo.get("dragger");
        dragger.setPosition(UP);

        colorSensor = hardwareMap.get(ColorSensor.class, "sensor_color");

        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        telemetry.addData("Path0",  "Starting at %7d :%7d",
                frontLeft.getCurrentPosition(),
                frontRight.getCurrentPosition());
        telemetry.update();

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode                = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = false;

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
        telemetry.addData("IMU", "calibrating...");
        telemetry.update();
        while (!isStopRequested() && !imu.isGyroCalibrated())
        {
            sleep(100);
        }
        telemetry.addData("IMU", "calibrated.");
        telemetry.update();
    }

    public double getTime()
    {
        return runTime.seconds();
    }
    public void resetTime()
    {
        runTime.reset();
    }

    public void setWheelSpeed(AutoSpeed s) //Set the speed of the wheels
    {
        switch (s)
        {
            case Fast:
                wheelPower = 1;
                break;
            case Intermediate:
                wheelPower = 0.5;
                break;
            case Slow:
                wheelPower = 0.3;
                break;
        }
    }

    public void setArmSpeed(AutoSpeed s) //Set the speed of the arm
    {
        switch (s)
        {
            case Fast:
                armPower = 1;
                break;
            case Intermediate:
                armPower = 0.5;
                break;
            case Slow:
                armPower = 0.3;
                break;
        }
    }

    private void setPower(RobotDirection d, double p) //Private methode to change power of the wheels
    {
        telemetry.addData("Setting power:", wheelPower);
        telemetry.update();
        switch (d)
        {
            case Forward:
                frontLeft.setPower(p);
                frontRight.setPower(p);
                backLeft.setPower(p);
                backRight.setPower(p);
                currentDirection = forward;
                break;
            case Backward:
                frontLeft.setPower(-p);
                frontRight.setPower(-p);
                backLeft.setPower(-p);
                backRight.setPower(-p);
                currentDirection = backward;
                break;
            case Left:
                frontLeft.setPower(-p);
                frontRight.setPower(p);
                backLeft.setPower(p);
                backRight.setPower(-p);
                currentDirection = left;
                break;
            case Right:
                frontLeft.setPower(p);
                frontRight.setPower(-p);
                backLeft.setPower(-p);
                backRight.setPower(p);
                currentDirection = right;
                break;
        }
    }
    private void setLeftPower(RobotDirection d, double p) //Private methode to change power of the wheels on the left side of the robot
    {
        try
        {
            switch (d)
            {
                case Forward:
                    frontLeft.setPower(p);
                    backLeft.setPower(p);
                    currentDirection = rotating;
                    break;
                case Backward:
                    frontLeft.setPower(-p);
                    backLeft.setPower(-p);
                    currentDirection = rotating;
                    break;
            }
        }
        catch (Exception e)
        {
            telemetry.addData("ERROR:", "Wrong enum 1");
            telemetry.update();
        }

    }
    private void setRightPower(RobotDirection d, double p) //Private methode to change power of the wheels on the right side of the robot
    {
        try
        {
            switch (d)
            {
                case Forward:
                    frontRight.setPower(p);
                    backRight.setPower(p);
                    currentDirection = rotating;
                    break;
                case Backward:
                    frontRight.setPower(-p);
                    backRight.setPower(-p);
                    currentDirection = rotating;
                    break;
            }
        }
        catch (Exception e)
        {
            telemetry.addData("ERROR:", "Wrong enum 2");
            telemetry.update();
        }

    }

    private void resetMotors() //Private methode to turn off all wheels
    {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
    private void resetArm() //Private methode to turn off motors connected to arm
    {
        arm.setPower(0);
        wrist.setPower(0);
    }
    public void resetAll() //Backup methode to Turn off all motors
    {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        arm.setPower(0);
        wrist.setPower(0);
    }

    private void speedUp(RobotDirection d) //(TESTING REQUIRED) Private methode to gently accelerate the robot in d direction
    {
        int i = 0;
        while (i <= 5 && !isStopRequested())
        {
            setPower(d, (0.2 + (0.2 * i)));
            i++;
            sleep(300);
        }

    }
    private void slowDown(RobotDirection d) //(TESTING REQUIRED) Private methode to gently deaccelerate the robot in d direction
    {
        int i = 0;
        while (i <= 5 && !isStopRequested())
        {
            setPower(d, (1 - (0.2 * i)));
            i++;
            sleep(300);
        }
    }

    public void move(long t) //Wrapper methode to move robot forward for t milliseconds
    {
        setPower(forward, wheelPower);
        sleep(t);
        resetMotors();
    }
    public void move(RobotDirection d, long t) //Wrapper methode to move robot in d direction for t milliseconds
    {
        setPower(d, wheelPower);
        sleep(t);
        resetMotors();
    }
    public void accMove(RobotDirection d, long t) //(TESTING REQUIRED) Wrapper methode to move robot in d direction for t milliseconds while accelerating and deaccelerating to max speed
                                                  // NOTE: Max speed of 1 is static and will not change based on current wheelpower
    {
        speedUp(d);
        sleep(t);
        slowDown(d);
        resetMotors();
    }
    public void moveSideways(RobotDirection d, long t)
    {
        switch (d)
        {
            case Left:
                frontLeft.setPower(-wheelPower);
                frontRight.setPower(wheelPower);
                backLeft.setPower(wheelPower);
                backRight.setPower(-wheelPower);
                currentDirection = left;
                sleep(t);
                resetMotors();
                break;
            case Right:
                frontLeft.setPower(wheelPower);
                frontRight.setPower(-wheelPower);
                backLeft.setPower(-wheelPower);
                backRight.setPower(wheelPower);
                currentDirection = right;
                sleep(t);
                resetMotors();
                break;
        }
    }

    public void setClawPosition(ServoPosition p) //Set position of claws in p position
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

    public void setDraggerPosition(ServoPosition p) //Set position of dragger in p position
    {
        switch (p)
        {
            case Open:
                dragger.setPosition(UP);
                break;
            case Closed:
                dragger.setPosition(DOWN);
                break;
        }
    }

    public void moveArm(ArmMovement m, int t) //Move arm in m direction for t milliseconds
    {
        switch (m)
        {
            case Up:
                arm.setPower(armPower);
                sleep(t);
                resetArm();
                break;
            case Down:
                arm.setPower(-armPower);
                sleep(t);
                resetArm();
                break;
        }
    }

    public void moveWrist(ArmMovement m, int t) //Move wrist in m direction for t milliseconds
    {
        switch (m)
        {
            case Up:
                wrist.setPower(armPower);
                sleep(t);
                resetArm();
                break;
            case Down:
                wrist.setPower(-armPower);
                sleep(t);
                resetArm();
                break;
        }
    }

    public void turn(RobotDirection d, long t) //Turn robot in d direction for t milliseconds
    {
        try
        {
            switch (d)
            {
                case Left:

                    switch (currentDirection)
                    {
                        case Forward:
                            setLeftPower(currentDirection, wheelPower / 2);
                            sleep(t);
                            resetMotors();
                            break;
                        case Backward:
                            setRightPower(currentDirection, wheelPower / 2);
                            sleep(t);
                            resetMotors();
                            break;
                    }

                case Right:

                    switch (currentDirection)
                    {
                        case Forward:
                            setRightPower(currentDirection, wheelPower / 2);
                            sleep(t);
                            resetMotors();
                            break;
                        case Backward:
                            setLeftPower(currentDirection, wheelPower / 2);
                            sleep(t);
                            resetMotors();
                            break;
                    }
            }
        }
        catch (Exception e)
        {
            telemetry.addData("ERROR:", "Wrong enum 3");
            telemetry.update();
        }

    }

    public void rotate(RobotDirection d, long t) //Rotate robot in d direction for t milliseconds
    {
        try
        {
            switch (d)
            {
                case Left:
                    setLeftPower(backward, wheelPower);
                    setRightPower(forward, wheelPower);
                    sleep(t);
                    resetMotors();
                    break;
                case Right:
                    setLeftPower(forward, wheelPower);
                    setRightPower(backward, wheelPower);
                    sleep(t);
                    resetMotors();
                    break;
            }
        }
        catch (Exception e)
        {
            telemetry.addData("ERROR:", "Wrong enum 4");
            telemetry.update();
        }
    }

    public void getColor() //Gets the current values that the color sensor detects and updates them via telemetry the driver station
    {
        // convert the RGB values to HSV values.
        Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);

        // send the info back to driver station using telemetry function.
        telemetry.addData("Clear", colorSensor.alpha());
        telemetry.addData("Red  ", colorSensor.red());
        telemetry.addData("Green", colorSensor.green());
        telemetry.addData("Blue ", colorSensor.blue());
        telemetry.addData("Hue", hsvValues[0]);
        telemetry.update();telemetry.update();
    }

    public void encoderDrive(RobotDirection d, double leftInches, double rightInches, double timeoutS) //(TESTING REQUIRED) Uses encodes to move left side of robot leftInches in inches and right side of robot rightInches in inches
                                                                                     // while terminating movement if current elapsed time is longer than timeoutS in seconds
    {

        int newFrontLeftTarget;
        int newFrontRightTarget;
        int newBackLeftTarget;
        int newBackRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newFrontLeftTarget = frontLeft.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newFrontRightTarget = frontRight.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newBackLeftTarget = backLeft.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newBackRightTarget = backRight.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            frontLeft.setTargetPosition(newFrontLeftTarget);
            frontRight.setTargetPosition(newFrontRightTarget);
            backLeft.setTargetPosition(newBackLeftTarget);
            backRight.setTargetPosition(newBackRightTarget);

            // Turn On RUN_TO_POSITION
            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // resetMotors the timeout time and start motion.
            encoderRunTime.reset();

            switch (d)
            {
                case Forward:
                    frontLeft.setPower(Math.abs(wheelPower));
                    frontRight.setPower(Math.abs(wheelPower));
                    backLeft.setPower(Math.abs(wheelPower));
                    backRight.setPower(Math.abs(wheelPower));
                    break;
                case Backward:
                    frontLeft.setPower(Math.abs(-wheelPower));
                    frontRight.setPower(Math.abs(-wheelPower));
                    backLeft.setPower(Math.abs(-wheelPower));
                    backRight.setPower(Math.abs(-wheelPower));
                    break;
                case Left:
                    frontLeft.setPower(Math.abs(-wheelPower));
                    frontRight.setPower(Math.abs(wheelPower));
                    backLeft.setPower(Math.abs(wheelPower));
                    backRight.setPower(Math.abs(-wheelPower));
                case Right:
                    frontLeft.setPower(Math.abs(wheelPower));
                    frontRight.setPower(Math.abs(-wheelPower));
                    backLeft.setPower(Math.abs(-wheelPower));
                    backRight.setPower(Math.abs(wheelPower));
            }

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (encoderRunTime.seconds() < timeoutS) &&
                    (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newFrontLeftTarget,  newFrontRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        frontLeft.getCurrentPosition(),
                        frontRight.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            resetMotors();

            // Turn off RUN_TO_POSITION
            frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(250);
        }
    }
}