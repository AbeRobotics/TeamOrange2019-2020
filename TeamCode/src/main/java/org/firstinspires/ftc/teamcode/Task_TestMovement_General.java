package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Task_TestMovement_General extends TaskBase
{
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

        telemetry.addData("Forward Called Test", "True");
        telemetry.update();
        move(forward);
        sleep(2000);
        resetMotors();

        telemetry.addData("Left Called Test", "True");
        telemetry.update();
        move(left);
        sleep(2000);
        resetMotors();

        telemetry.addData("Right Called Test", "True");
        telemetry.update();
        move(right);
        sleep(2000);
        resetMotors();

        telemetry.addData("Backward Called Test", "True");
        telemetry.update();
        move(backward);
        sleep(2000);
        resetMotors();

        telemetry.addData("Left Turn Called Test", "True");
        telemetry.update();
        turn(left);
        sleep(2000);
        resetMotors();

        telemetry.addData("Right Turn Called Test", "True");
        telemetry.update();
        turn(right);
        sleep(2000);
        resetMotors();

        statusCompletion = true;
    }

    public boolean getStatus() {
        return statusCompletion;
    }
}
