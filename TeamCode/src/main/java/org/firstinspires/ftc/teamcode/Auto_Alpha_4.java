package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Auto - Alpha (FOUNDATION RED)", group="Autonomous")
public class Auto_Alpha_4 extends AutoTasks_Alpha
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        activate();

        waitForStart();
        resetTime();

        foundationMoveBasic(RobotTeam.RedRight);
    }
}
