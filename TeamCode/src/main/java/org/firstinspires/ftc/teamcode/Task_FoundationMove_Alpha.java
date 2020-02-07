package org.firstinspires.ftc.teamcode;

public class Task_FoundationMove_Alpha extends TaskBase_Alpha
{
    private boolean statusCompletion;
    long ONE_SQUARE = 1260; //Time in milliseconds

    public Task_FoundationMove_Alpha() { statusCompletion = false; }

    public void performTask(wheelSpeed speed, Team team)
    {
        super.performTask(speed, armSpeed.Fast);

        move(Direction.Forward);
        sleep(ONE_SQUARE);
        resetMotors();

        setDraggerPosition(Position.Closed);
        move(Direction.Backward);
        sleep(ONE_SQUARE);
        resetMotors();

        setDraggerPosition(Position.Open);
        switch (team)
        {
            case Red:
                move(Direction.Left);
                sleep(ONE_SQUARE * 2);
                break;
            case Blue:
                move(Direction.Right);
                sleep(ONE_SQUARE * 2);
                break;
        }

        statusCompletion = true;
    }

    public boolean getStatus() { return statusCompletion; }
}

