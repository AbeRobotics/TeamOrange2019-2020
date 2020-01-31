package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Task_FoundationMove_Beta extends TaskBase_Beta
{
    private boolean statusCompletion;

    @Override
    public void Init(Telemetry telemetry, HardwareMap hardwareMap)
    {
        super.Init(telemetry, hardwareMap);
        statusCompletion = false;
    }

    public void performTask(wheelSpeed speed, Team team)
    {
        super.performTask(speed, null);

        move(forward);
        sleep(1600);
        resetMotors();

        switch (team)
        {
            case Red:
                move(left);
                sleep(900);
                resetMotors();
                break;
            case Blue:
                move(right);
                sleep(900);
                resetMotors();
                break;
        }

        setClawPosition(closed);
        sleep(1000);

        move(backward);
        sleep(1600);
        resetMotors();

        statusCompletion = true;
    }

    public boolean getStatus() {
        return statusCompletion;
    }
}
