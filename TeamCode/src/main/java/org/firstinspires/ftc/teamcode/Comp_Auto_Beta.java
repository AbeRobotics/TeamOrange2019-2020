package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Auto - Beta", group="Autonomous")
public class Comp_Auto_Beta extends LinearOpMode
{
    private ElapsedTime runTime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException
    {
        BlockGrabTask_Beta blockGrab = new BlockGrabTask_Beta();
        blockGrab.Init(telemetry, hardwareMap);
        blockGrab.performTask(TaskBase_Beta.Speed.Fast);
        while(blockGrab.getStatus() == false)
        {
            blockGrab.performTask(TaskBase_Beta.Speed.Fast);
            sleep(100);
        }
        sleep((30 - (int)getRuntime())*1000);
    }
}
