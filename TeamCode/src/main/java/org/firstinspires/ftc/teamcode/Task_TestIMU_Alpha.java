package org.firstinspires.ftc.teamcode;

public class Task_TestIMU_Alpha extends TaskBase_Alpha
{
    private boolean statusCompletion;

    public Task_TestIMU_Alpha()
    {
        statusCompletion = false;
    }

    public void performTask(wheelSpeed speed)
    {
        super.performTask(speed, armSpeed.Fast);

        gyroMove(forward, 0.61);
        gyroMove(left, 0.61);
        gyroMove(backward, 0.61);
        gyroMove(right, 0.61);

        statusCompletion = true;
    }

    public boolean getStatus() {
        return statusCompletion;
    }
}
