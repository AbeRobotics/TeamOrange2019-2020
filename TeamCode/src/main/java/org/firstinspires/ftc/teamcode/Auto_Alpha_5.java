package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Alpha(Blue Park Wall, NO DELAY)", group="Autonomous")
public class Auto_Alpha_5 extends AutoTasks_Alpha
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        activate();

        waitForStart();
        resetTime();

        lineMove(blueRight,false,false);
    }
}
