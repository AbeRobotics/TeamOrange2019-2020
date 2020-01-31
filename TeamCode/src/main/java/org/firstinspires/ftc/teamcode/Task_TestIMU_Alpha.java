package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

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

        move(forward, 0.61);
        rotate(180, 0.3);
        move(forward, 0.61);
        rotate(90, 0.3);
        move(left, 0.61);
        move(right, 0.61);

        statusCompletion = true;
    }

    public boolean getStatus() {
        return statusCompletion;
    }
}
