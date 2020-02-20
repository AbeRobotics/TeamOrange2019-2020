package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class TaskBase_Alpha extends TaskBase
{
    DcMotor frontLeft, frontRight, backLeft, backRight, arm, wrist;

    Servo dragger, right_claw, left_claw;

    ColorSensor colorSensor;

    ElapsedTime movementTracker = new ElapsedTime();

    BNO055IMU imu;

    Orientation lastAngles = new Orientation();
    double globalAngle, correction;

    final double OPEN_CLAW = 0.5, CLOSED_CLAW = 0, UP = 0.7, DOWN = 1;

    long DRIFTCORRECTION = 2000; //Time (in milliseconds) for robot to stop moving once motors are off

    //Position open = Position.Open, closed = Position.Closed;

    public Telemetry telemetry;
    private HardwareMap hardwareMap;

    public enum Position
    {
        Open, Closed
    }
    public enum Movement
    {
        Up, Down
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

        telemetry.addData("Wheels:", "Initialized");
        telemetry.update();

        arm = hardwareMap.dcMotor.get("arm");
        wrist = hardwareMap.dcMotor.get("wrist");

        telemetry.addData("Arm:", "Initialized");
        telemetry.update();

        right_claw = hardwareMap.servo.get("right_claw");
        right_claw.setDirection(Servo.Direction.REVERSE);
        left_claw = hardwareMap.servo.get("left_claw");
        right_claw.setPosition(CLOSED_CLAW);
        left_claw.setPosition(CLOSED_CLAW);

        dragger = hardwareMap.servo.get("dragger1");
        dragger.setPosition(UP);

        telemetry.addData("Servos:", "Initialized");
        telemetry.update();

        colorSensor = hardwareMap.get(ColorSensor.class, "sensor_color");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode                = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = false;

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
        telemetry.addData("IMU", "calibrating...");
        telemetry.update();
        while (!imu.isGyroCalibrated())
        {
            sleep(100);
        }
        telemetry.addData("IMU", "calibrated.");
        telemetry.update();
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

    public void setDraggerPosition(Position p)
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

    public void moveArm(Movement m, int t)
    {
        switch (m)
        {
            case Up:
                arm.setPower(armPower);
                sleep(t);
                resetMotors();
                break;
            case Down:
                arm.setPower(-armPower);
                sleep(t);
                resetMotors();
                break;
        }
    }

    public void gyroMove(Direction direction, double distance) //distance is in meters
    {
        movementTracker.reset();

        switch (direction)
        {
            case Backward:
                frontLeft.setPower(wheelPower);
                frontRight.setPower(wheelPower);
                backLeft.setPower(wheelPower);
                backRight.setPower(wheelPower);

                sleep(300);

                while(movementTracker.seconds() < (distance/imu.getVelocity().xVeloc))
                {
                    /*
                    correction = checkDirection();

                    frontLeft.setPower(frontLeft.getPower() - correction);
                    frontRight.setPower(frontRight.getPower() + correction);
                    backLeft.setPower(backLeft.getPower() - correction);
                    backRight.setPower(backRight.getPower() + correction);
*/
                    sleep(10);
                }
                resetMotors();
                break;

            case Forward:

                frontLeft.setPower(-wheelPower);
                frontRight.setPower(-wheelPower);
                backLeft.setPower(-wheelPower);
                backRight.setPower(-wheelPower);

                sleep(300);

                while(movementTracker.seconds() < (distance/imu.getVelocity().xVeloc))
                {
                    /*
                    correction = checkDirection();

                    frontLeft.setPower(frontLeft.getPower() - correction);
                    frontRight.setPower(frontRight.getPower() + correction);
                    backLeft.setPower(backLeft.getPower() - correction);
                    backRight.setPower(backRight.getPower() + correction);
                     */
                    sleep(10);
                }

                resetMotors();
                break;

            case Left:
                frontLeft.setPower(-wheelPower);
                frontRight.setPower(wheelPower);
                backLeft.setPower(wheelPower);
                backRight.setPower(-wheelPower);

                sleep(300);

                while(movementTracker.seconds() < (distance/imu.getVelocity().zVeloc))
                {
                    /*
                    correction = checkDirection();

                    frontLeft.setPower(frontLeft.getPower() - correction);
                    frontRight.setPower(frontRight.getPower() + correction);
                    backLeft.setPower(backLeft.getPower() - correction);
                    backRight.setPower(backRight.getPower() + correction);
*/
                    sleep(10);
                }

                resetMotors();
                break;

            case Right:
                frontLeft.setPower(wheelPower);
                frontRight.setPower(-wheelPower);
                backLeft.setPower(-wheelPower);
                backRight.setPower(wheelPower);

                sleep(300);

                while(movementTracker.seconds() < (distance/imu.getVelocity().zVeloc))
                {
                    /*
                    correction = checkDirection();

                    frontLeft.setPower(frontLeft.getPower() - correction);
                    frontRight.setPower(frontRight.getPower() + correction);
                    backLeft.setPower(backLeft.getPower() - correction);
                    backRight.setPower(backRight.getPower() + correction);
*/
                    sleep(10);
                }

                resetMotors();
                break;
        }
    }

    private void resetAngle()
    {
        lastAngles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        globalAngle = 0;
    }

    /**
     * Get current cumulative angle rotation from last reset.
     * @return Angle in degrees. + = left, - = right.
     */
    private double getAngle()
    {
        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double deltaAngle = angles.firstAngle - lastAngles.firstAngle;

        if (deltaAngle < -180)
            deltaAngle += 360;
        else if (deltaAngle > 180)
            deltaAngle -= 360;

        globalAngle += deltaAngle;

        lastAngles = angles;

        return globalAngle;
    }

    /**
     * See if we are moving in a straight line and if not return a power correction value.
     * @return Power adjustment, + is adjust left - is adjust right.
     */
    private double checkDirection()
    {
        double correction, angle, gain = .10;

        angle = getAngle();

        if (angle == 0)
            correction = 0;             // no adjustment.
        else
            correction = -angle;        // reverse sign of angle for correction.

        correction = correction * gain;

        return correction;
    }

    /**
     * Rotate left or right the number of degrees. Does not support turning more than 180 degrees.
     * @param degrees Degrees to turn, + is left - is right
     */
    public void rotate(int degrees, double power)
    {
        double  leftPower, rightPower;

        // restart imu movement tracking.
        resetAngle();

        // getAngle() returns + when rotating counter clockwise (left) and - when rotating
        // clockwise (right).

        if (degrees < 0)
        {   // turn right.
            leftPower = power;
            rightPower = -power;
        }
        else if (degrees > 0)
        {   // turn left.
            leftPower = -power;
            rightPower = power;
        }
        else return;

        // set power to rotate.
        frontLeft.setPower(leftPower);
        frontRight.setPower(rightPower);
        backLeft.setPower(leftPower);
        backRight.setPower(rightPower);

        // rotate until turn is completed.
        if (degrees < 0)
        {
            // On right turn we have to get off zero first.
            while (getAngle() == 0) {}

            while (getAngle() > degrees) {}
        }
        else    // left turn.
            while (getAngle() < degrees) {}

        // turn the motors off.
        resetMotors();

        // wait for rotation to stop.
        sleep(1000);

        // reset angle tracking on new heading.
        resetAngle();
    }

}