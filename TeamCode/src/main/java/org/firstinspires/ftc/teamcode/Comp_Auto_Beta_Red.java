package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

//@Autonomous(name="OLD AUTO - Beta, Red Team", group="Autonomous")
public class Comp_Auto_Beta_Red extends LinearOpMode
{
    private ElapsedTime runTime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException
    {
        /*
        Task_BlockGrab_Beta blockGrab = new Task_BlockGrab_Beta();
        blockGrab.Init(telemetry, hardwareMap);
        blockGrab.performTask(TaskBase_Beta.Speed.Fast);
        while(blockGrab.getStatus() == false)
        {
            blockGrab.performTask(TaskBase.Speed.Fast);
            sleep(100);
        }
         */
        Task_FoundationMove_Beta baseMove = new Task_FoundationMove_Beta();
        baseMove.Init(telemetry, hardwareMap);
        while(baseMove.getStatus() == false)
        {
            baseMove.performTask(TaskBase.wheelSpeed.Fast, TaskBase.Team.Red);
            sleep(100);
        }
        sleep((30 - (int)getRuntime())*1000);
    }
}
