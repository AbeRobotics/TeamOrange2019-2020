package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Task_TestIMU_Alpha extends TaskBase_Alpha
{
    private boolean statusCompletion;

    @Override
    public void Init(Telemetry telemetry, HardwareMap hardwareMap)
    {
        super.Init(telemetry, hardwareMap);
        statusCompletion = false;
    }

    public boolean getStatus() {
        return statusCompletion;
    }
}
