package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Alpha(Red Foundation Wall - DELAY)", group="Autonomous")
public class Auto_Alpha_7 extends AutoTasks_Alpha
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        activate();

        waitForStart();
        resetTime();

        expFoundationMove(RobotTeam.RedRight, true, false);
    }
}
