package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Alpha(FOUNDATION RED - EXPERIMENTAL)", group="Autonomous")
public class Auto_Alpha_8 extends AutoTasks_Alpha
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        activate();

        waitForStart();
        resetTime();

        foundationMove(RobotTeam.RedRight);
    }
}
