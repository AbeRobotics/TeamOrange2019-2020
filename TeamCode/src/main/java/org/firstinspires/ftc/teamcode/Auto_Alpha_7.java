package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Alpha(FOUNDATION RED - DELAY)", group="Autonomous")
public class Auto_Alpha_7 extends AutoTasks_Alpha
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        activate();

        waitForStart();
        resetTime();

        sleep(10000);

        foundationMoveBasic(RobotTeam.RedRight);
    }
}
