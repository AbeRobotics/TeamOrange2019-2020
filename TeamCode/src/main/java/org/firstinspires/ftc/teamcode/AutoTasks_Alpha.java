package org.firstinspires.ftc.teamcode;

public abstract class AutoTasks_Alpha extends AutoMethods_Alpha
{
    public void foundationMove(RobotTeam team)
    {
        move(forward, 1330);

        sleep(1500);
        setDraggerPosition(down);
        sleep(500);

        move(backward, 1420);

        sleep(1000);
        setDraggerPosition(up);
        sleep(500);

        switch (team)
        {
            case RedRight:
                move(left, 2700);
                break;
            case BlueLeft:
                move(right, 2700);
                break;
        }
    }

    public void lineMove(RobotTeam team)
    {
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

    public void colorSensorTest()
    {
        while(!isStopRequested())
        {
            getColor();
        }
    }

    public void gyroMoveTest()
    {
        encoderDrive(48,  48, 5.0);  // S1: Forward 47 Inches with 5 Sec timeout
        encoderDrive(12, -12, 4.0);  // S2: Turn Right 12 Inches with 4 Sec timeout
        encoderDrive(-24, -24, 4.0);  // S3: Reverse 24 Inches with 4 Sec timeout

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }
}
