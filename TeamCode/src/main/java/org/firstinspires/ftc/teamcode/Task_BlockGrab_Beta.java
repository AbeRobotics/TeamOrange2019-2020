package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Task_BlockGrab_Beta extends TaskBase_Beta{

    private boolean statusCompletion;

    @Override
    public void Init(Telemetry telemetry, HardwareMap hardwareMap)
    {
        super.Init(telemetry, hardwareMap);
        statusCompletion = false;

    }

    public void performTask(wheelSpeed speed)
    {
        super.performTask(speed, null);

        super.move(forward);
        sleep(3000);
        resetMotors();

        super.setClawPosition(closed);
        sleep(3000);

        super.move(backward);
        sleep(3000);
        resetMotors();

        super.halfTurn(left);
        resetMotors();

        super.move(forward);
        sleep(3000);
        resetMotors();

        statusCompletion = true;
    }

    public boolean getStatus() {
        return statusCompletion;
    }
}
