package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Alpha(Red Foundation Wall - NO DELAY)", group="Autonomous")
public class Auto_Alpha_11 extends AutoTasks_Alpha
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        activate();

        waitForStart();
        resetTime();

        expFoundationMove(RobotTeam.RedRight,false, false);
    }
}
