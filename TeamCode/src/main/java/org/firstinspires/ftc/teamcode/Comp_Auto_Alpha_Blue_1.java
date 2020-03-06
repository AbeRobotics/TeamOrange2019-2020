package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

//@Autonomous(name="OLD AUTO - Alpha, Blue Team, Foundation", group="Autonomous")
public class Comp_Auto_Alpha_Blue_1 extends LinearOpMode
{
    private ElapsedTime runTime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException
    {
        Task_FoundationMove_Alpha moveBase = new Task_FoundationMove_Alpha();
        moveBase.Init(telemetry, hardwareMap);
        while(moveBase.getStatus() == false)
        {
            moveBase.performTask(TaskBase.wheelSpeed.Intermediate, TaskBase.Team.Blue);
            sleep(100);
        }

        sleep((30 - (int)getRuntime())*1000);
    }
}
