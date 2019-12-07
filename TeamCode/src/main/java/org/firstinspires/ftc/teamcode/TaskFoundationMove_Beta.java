package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TaskFoundationMove_Beta extends TaskBase_Beta
{
    private boolean statusCompletion;

    @Override
    public void Init(Telemetry telemetry, HardwareMap hardwareMap)
    {
        super.Init(telemetry, hardwareMap);
        statusCompletion = false;
    }

    @Override
    public void performTask(Speed speed)
    {
        super.performTask(speed);

        super.move(forward);
        sleep(1600);
        resetMotors();

        super.move(left);
        sleep(900);
        resetMotors();

        super.setClawPosition(closed);
        sleep(1000);

        super.move(backward);
        sleep(1600);
        resetMotors();

        statusCompletion = true;
    }

    public boolean getStatus() {
        return statusCompletion;
    }
}
