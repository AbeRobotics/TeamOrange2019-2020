package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class Task_TestColorSensor_Alpha extends TaskBase_Alpha
{
    private boolean statusCompletion;
    private final double COLORLIMIT = 0.1;

    @Override
    public void Init(Telemetry telemetry, HardwareMap hardwareMap)
    {
        super.Init(telemetry, hardwareMap);
        statusCompletion = false;
    }

    public void performTask()
    {
        while (colorSensor.red() > COLORLIMIT && colorSensor.green() > COLORLIMIT && colorSensor.blue() > COLORLIMIT)
        {
            statusCompletion = false;
        }
        statusCompletion = true;
    }

    public boolean getStatus() { return statusCompletion; }
}
