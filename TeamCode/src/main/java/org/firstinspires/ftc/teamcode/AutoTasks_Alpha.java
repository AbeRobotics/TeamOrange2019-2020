package org.firstinspires.ftc.teamcode;

public abstract class AutoTasks_Alpha extends AutoMethods_Alpha
{
    double colorMin = 75;
    double colorMax = 85;

    private void moveToObject() {encoderDrive2Wheel(forward,17,17,4);}

    public void foundationMove(RobotTeam team)
    {
        switch (team)
        {
            case BlueLeft:
                moveSideways(left, 600);
                break;
            case RedRight:
                moveSideways(right, 600);
                break;
        }

        encoderDrive2Wheel(forward,17,17,4);

        sleep(1000);
        setDraggerPosition(down);
        sleep(500);

        encoderDrive2Wheel(forward,-20,-20,3); //Extra distance needed due dragging foundation

        //To correct build platform alignment
        switch (team)
        {

            case BlueLeft:
                encoderDrive2Wheel(forward, -2, 2, 3); //To correct building platform
                sleep(1000);
                //moveSideways(right, 400);
                encoderDrive2Wheel(right,5,5,3);
                sleep(1000);
                encoderDrive2Wheel(forward, -2, 2, 3); //To correct building platform
                sleep(1000);
                setDraggerPosition(up);
                sleep(1000);
                encoderDrive2Wheel(forward, 2, -2, 3); //To correct building platform
                break;
            case RedRight:
                //encoderDrive2Wheel(right,2,2,2);
                encoderDrive2Wheel(forward, 2, -2, 3); //To correct building platform
                sleep(1000);
                //moveSideways(left, 400);
                encoderDrive2Wheel(left,5,5,3);
                sleep(1000);
                encoderDrive2Wheel(forward, 2, -2, 3); //To correct building platform
                sleep(1000);
                setDraggerPosition(up);
                sleep(1000);
                encoderDrive2Wheel(forward, -2, 2, 3);
                break;
        }

        sleep(1000);

        ServoPosition open = ServoPosition.Open;
        setClawPosition(open);

        switch (team)
        {
            case RedRight:
                //moveSideways(left, 2600); //Added -Devin
                encoderDrive2Wheel(left,5,5,3);
                break;
            case BlueLeft:
                //moveSideways(right, 2600); //Added -Devin
                encoderDrive2Wheel(right,5,5,3);
                break;
        }
    }

    public void foundationMoveBasic(RobotTeam team)
    {
        //encoderDrive2Wheel(forward,6,6,3); //Comment Out to test -Devin

        switch (team)
        {
            case BlueLeft:
                //encoderDrive2Wheel(left,2,2,2);
                moveSideways(left, 600);
                break;
            case RedRight:
                //encoderDrive2Wheel(right,2,2,2);
                moveSideways(right, 600);
                break;
        }

        moveToObject();

        sleep(1000);
        setDraggerPosition(down);
        sleep(500);

        encoderDrive2Wheel(forward,-20,-20,3); //Extra distance needed due dragging foundation

        //To correct build platform alignment
        switch (team)
        {

            case BlueLeft:
                //encoderDrive2Wheel(left,2,2,2);
                encoderDrive2Wheel(forward, -2, 2, 3); //To correct building platform
                sleep(1000);
                moveSideways(right, 400);
                sleep(1000);
                encoderDrive2Wheel(forward, -2, 2, 3); //To correct building platform
                sleep(1000);
                setDraggerPosition(up);
                sleep(1000);
                encoderDrive2Wheel(forward, 2, -2, 3); //To correct building platform
                break;
            case RedRight:
                //encoderDrive2Wheel(right,2,2,2);
                encoderDrive2Wheel(forward, 2, -2, 3); //To correct building platform
                sleep(1000);
                moveSideways(left, 400);
                sleep(1000);
                encoderDrive2Wheel(forward, 2, -2, 3); //To correct building platform

                sleep(1000);
                setDraggerPosition(up);
                sleep(1000);
                encoderDrive2Wheel(forward, -2, 2, 3);
                break;
        }

        sleep(1000);

        ServoPosition open = ServoPosition.Open;
        setClawPosition(open);

        switch (team)
        {
            case RedRight:
                moveSideways(left, 2600); //Added -Devin
                //encoderDrive2Wheel(left,10,10,5); //Commented Out to test -Devin
                break;
            case BlueLeft:
                moveSideways(right, 2600); //Added -Devin
                //encoderDrive2Wheel(right,10,10,5); //Commented Out to test -Devin
                break;
        }
    }

    public void lineMove(RobotTeam team)
    {
        encoderDrive2Wheel(forward,1,1,1);

        switch (team)
        {
            case RedLeft:
                move(right, 1420);
                break;
            case BlueLeft:
                move(right, 1420);
                break;
            case RedRight:
                move(left, 1420);
                break;
            case BlueRight:
                move(left, 1420);
                break;
        }
    }

    public void armTest()
    {
        encoderArmDrive(ArmMovement.Up, 180,5);
        moveWrist(ArmMovement.Down,3000);
        encoderArmDrive(ArmMovement.Up, 40,3);

    }

    public void blockMove()
    {
        moveToObject();
        encoderDrive2Wheel(backward,3,3,2);

        encoderArmDrive(ArmMovement.Up, 180,3);
        setClawPosition(closed);

        encoderArmDrive(ArmMovement.Down, 15,2);
        encoderDrive2Wheel(backward,2,2,2);
        encoderDrive4Wheel(right,10,10,4);

    }

    public void colorSensorTest()
    {
        boolean blockFound = false;
        double hsv;
        //Only works if the robot is standing adjacent to the blocks
        while(!isStopRequested() || !blockFound)
        {
             //Set to RGB color currentColor
            // If currentColor == Yellow
            // Move in the direction that the color sensor is facing
            // else if (currentColor == Black)
            hsv = getColor();
            if (hsv < colorMax && hsv > colorMin)
            {
                blockFound = true;
            }
        }
    }

    public void gyroMoveTest()
    {
        encoderDrive2Wheel(forward,12,  12, 4.0);  // S1: Forward 47 Inches with 5 Sec timeout
        encoderDrive2Wheel(forward,12, -12, 4.0);  // S2: Turn Right 12 Inches with 4 Sec timeout
        encoderDrive2Wheel(forward,12, 12, 4.0);  // S3: Reverse 24 Inches with 4 Sec timeout

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }
}
