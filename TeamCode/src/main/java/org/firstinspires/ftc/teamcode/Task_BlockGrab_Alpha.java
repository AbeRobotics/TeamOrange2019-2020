package org.firstinspires.ftc.teamcode;

public class Task_BlockGrab_Alpha extends TaskBase_Alpha
{
    private boolean statusCompletion;
    long ONE_SQUARE = 1260; //Time in milliseconds

    public Task_BlockGrab_Alpha() { statusCompletion = false; }

    public void performTask(wheelSpeed speed, Team team)
    {
        super.performTask(speed, armSpeed.Fast);

        move(forward,ONE_SQUARE);

        switch (team)
        {
            case Red:
                move(Direction.Right, ONE_SQUARE * 2);
                break;
            case Blue:
                move(Direction.Left, ONE_SQUARE * 2);
                break;
        }

        statusCompletion = true;
    }

    public boolean getStatus() { return statusCompletion; }
}
