package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Auto - Alpha (TESTING)", group="Autonomous")
public class Auto_Alpha extends AutoTasks_Alpha
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        activate();

        waitForStart();
        resetTime();


    }
}
