package org.firstinspires.ftc.teamcode;

public abstract class AutoTasks_Alpha extends AutoMethods_Alpha
{
    public void foundationMove(RobotTeam team)
    {
        encoderDrive(forward,6,6,4);

        switch (team)
        {
            case BlueLeft:
                encoderDrive(left,4,4,2);
                break;
            case RedRight:
                encoderDrive(right,4,4,2);
                break;
        }

        encoderDrive(forward,9,9,4);

        sleep(1000);
        setDraggerPosition(down);
        sleep(500);

        encoderDrive(forward,-17,-17,3); //Extra distance needed due dragging foundation

        sleep(1000);
        setDraggerPosition(up);
        sleep(500);

        switch (team)
        {
            case BlueLeft:
                moveSideways(right, 2000);
                encoderDrive(forward,10,10,2);
                moveSideways(left, 1800);
                encoderDrive(forward,-9,-9,2);
                moveSideways(right, 1200);
                break;
            case RedRight:
                moveSideways(left, 2000);
                encoderDrive(forward,10,10,2);
                moveSideways(right, 1800);
                encoderDrive(forward,-9,-9,2);
                moveSideways(left, 1200);break;
        }
    }

    public void foundationMoveBasic(RobotTeam team)
    {
        encoderDrive(forward,6,6,3);

        switch (team)
        {
            case BlueLeft:
                encoderDrive(left,4,4,2);
                break;
            case RedRight:
                encoderDrive(right,4,4,2);
                break;
        }

        encoderDrive(forward,9,9,4);

        sleep(1000);
        setDraggerPosition(down);
        sleep(500);

        encoderDrive(forward,-17,-17,3); //Extra distance needed due dragging foundation

        sleep(1000);
        setDraggerPosition(up);
        sleep(500);

        switch (team)
        {
            case BlueLeft:
                encoderDrive(left,10,10,5);
                break;
            case RedRight:
                encoderDrive(right,10,10,5);
                break;
        }
    }

    public void lineMove(RobotTeam team)
    {
        encoderDrive(forward,1,1,1);

        switch (team)
        {
            case RedLeft:
                moveSideways(right, 1200);
                break;
            case BlueLeft:
                move(right, 1420);
                break;
            case RedRight:
                moveSideways(left, 1200);
                break;
            case BlueRight:
                move(left, 1420);
                break;
        }
    }

    public void colorSensorTest()
    {
        //Only works if the robot is standing adjacent to the blocks
        while(!isStopRequested())
        {
            getColor(); //Set to RGB color currentColor
            // If currentColor == Yellow
            // Move in the direction that the color sensor is facing
            // else if (currentColor == Black)

        }
    }

    public void gyroMoveTest()
    {
        encoderDrive(forward,12,  12, 4.0);  // S1: Forward 47 Inches with 5 Sec timeout
        encoderDrive(forward,12, -12, 4.0);  // S2: Turn Right 12 Inches with 4 Sec timeout
        encoderDrive(forward,12, 12, 4.0);  // S3: Reverse 24 Inches with 4 Sec timeout

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }
}
