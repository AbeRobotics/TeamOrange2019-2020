package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Test Autonomous", group="Autonomous")
public class TestAuto extends LinearOpMode
{
    private ElapsedTime runTime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException
    {
        TestTask test = new TestTask();
        test.Init(telemetry, hardwareMap);
        test.performTask(TaskBase.wheelSpeed.Fast);
        while(test.getStatus() == false)
        {
            test.performTask(TaskBase.wheelSpeed.Fast);
            sleep(100);
        }
        sleep((30 - (int)getRuntime())*1000);
    }
}
