package org.firstinspires.ftc.teamcode;

import android.os.AsyncTask;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TestTask extends TaskBase{

    private boolean statusCompletion = false;

    @Override
    public void Init(Telemetry telemetry, HardwareMap hardwareMap)
    {
        super.Init(telemetry, hardwareMap);

    }

    public void performTask(wheelSpeed speed)
    {
        super.performTask(speed, null);
        move360(wheelPower);
        sleep(5000);
        resetMotors();
        statusCompletion = true;
    }

    private void move360(double power) {
        frontLeft.setPower(power);
        frontRight.setPower(-power);
        backLeft.setPower(power);
        backRight.setPower(-power);
    }

    public boolean getStatus() {
        return statusCompletion;
    }
}
