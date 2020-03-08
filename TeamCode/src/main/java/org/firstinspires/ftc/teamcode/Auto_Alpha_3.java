package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Alpha(Red Park Wall, NO DELAY)", group="Autonomous")
public class Auto_Alpha_3 extends AutoTasks_Alpha
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        activate();

        waitForStart();
        resetTime();

        lineMove(redLeft,false,false);
    }
}