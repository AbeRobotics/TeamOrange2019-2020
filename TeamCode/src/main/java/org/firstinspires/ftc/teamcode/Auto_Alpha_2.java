package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Auto - Alpha (TESTING 2)", group="Autonomous")
public class Auto_Alpha_2 extends AutoTasks_Alpha
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        activate();

        waitForStart();
        resetTime();

        foundationMove(blueLeft);

        sleep((30 - (int)getTime())*1000);
    }
}
