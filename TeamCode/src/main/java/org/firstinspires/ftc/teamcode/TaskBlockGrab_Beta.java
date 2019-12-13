package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TaskBlockGrab_Beta extends TaskBase_Beta
{

    private boolean statusCompletion;

    @Override
    public void Init(Telemetry telemetry, HardwareMap hardwareMap)
    {
        super.Init(telemetry, hardwareMap);
        statusCompletion = false;

    }

    //@Override
    public void performTask(Speed speed, Team team)
    {
        super.performTask(speed);

        move(forward);
        sleep(1800);
        resetMotors();

        setClawPosition(closed);
        sleep(1800);

        move(backward);
        sleep(900);
        resetMotors();
        switch (team)
        {
            case Red:
                move(right);
                sleep(2400);
                resetMotors();

                setClawPosition(open);
                sleep(1800);

                move(left);
                sleep(1200);
                resetMotors();

            case Blue:
                move(left);
                sleep(2400);
                resetMotors();

                setClawPosition(open);
                sleep(1800);

                move(right);
                sleep(1200);
                resetMotors();
        }
        statusCompletion = true;
    }

    public boolean getStatus() {
        return statusCompletion;
    }
}
