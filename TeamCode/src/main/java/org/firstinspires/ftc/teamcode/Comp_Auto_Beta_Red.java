package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Auto - Beta, Red Team", group="Autonomous")
public class Comp_Auto_Beta_Red extends LinearOpMode
{
    private ElapsedTime runTime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException
    {
        /*
        TaskBlockGrab_Beta blockGrab = new TaskBlockGrab_Beta();
        blockGrab.Init(telemetry, hardwareMap);
        blockGrab.performTask(TaskBase_Beta.Speed.Fast);
        while(blockGrab.getStatus() == false)
        {
            blockGrab.performTask(TaskBase_Beta.Speed.Fast);
            sleep(100);
        }
         */
        TaskFoundationMove_Beta baseMove = new TaskFoundationMove_Beta();
        baseMove.Init(telemetry, hardwareMap);
        while(baseMove.getStatus() == false)
        {
            baseMove.performTask(TaskBase_Beta.Speed.Fast, TaskBase_Beta.Team.Red);
            sleep(100);
        }
        sleep((30 - (int)getRuntime())*1000);
    }
}
