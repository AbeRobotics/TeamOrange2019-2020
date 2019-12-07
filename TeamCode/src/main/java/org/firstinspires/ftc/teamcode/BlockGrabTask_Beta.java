package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class BlockGrabTask_Beta extends TaskBase_Beta{

    private boolean statusCompletion = false;

    @Override
    public void Init(Telemetry telemetry, HardwareMap hardwareMap)
    {
        super.Init(telemetry, hardwareMap);

    }

    @Override
    public void performTask(Speed speed)
    {
        super.performTask(speed);
        super.move(Direction.Forward);
        sleep(5000);
        resetMotors();
        BlockGrab();
        sleep(3000);
        super.move(Direction.Backward);
        resetMotors();
        super.halfTurn(Direction.Left);
        sleep(5000);
        resetMotors();
        super.move(Direction.Forward);
        sleep(5000);
        resetMotors();
        statusCompletion = true;
    }

    private void BlockGrab() {
        claw.setPosition(CLOSED_CLAW);
    }

    public boolean getStatus() {
        return statusCompletion;
    }
}
