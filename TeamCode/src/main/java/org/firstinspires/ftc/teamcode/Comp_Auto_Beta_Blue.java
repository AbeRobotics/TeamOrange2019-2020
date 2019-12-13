package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Auto - Beta, Blue Team", group="Autonomous")
public class Comp_Auto_Beta_Blue extends LinearOpMode
{
    private ElapsedTime runTime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException
    {
        TaskFoundationMove_Beta baseMove = new TaskFoundationMove_Beta();
        TaskBlockGrab_Beta blockGrab = new TaskBlockGrab_Beta();

        waitForStart();
        resetStartTime();


        blockGrab.Init(telemetry, hardwareMap);
        blockGrab.performTask(TaskBase_Beta.Speed.Fast);
        while(blockGrab.getStatus() == false)
        {
            blockGrab.performTask(TaskBase_Beta.Speed.Fast, TaskBase_Beta.Team.Blue);
            sleep(500);
        }

        /*
        baseMove.Init(telemetry, hardwareMap);
        while(baseMove.getStatus() == false)
        {
            baseMove.performTask(TaskBase_Beta.Speed.Fast, TaskBase_Beta.Team.Blue);
            sleep(500);
        }
        */
        sleep((30 - (int)getRuntime())*1000);
    }
}
