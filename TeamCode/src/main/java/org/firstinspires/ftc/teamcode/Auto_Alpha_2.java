package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Alpha(Blue Foundation Bridge - DELAY)", group="Autonomous")
public class Auto_Alpha_2 extends AutoTasks_Alpha
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        activate();

        waitForStart();
        resetTime();

        expFoundationMove(RobotTeam.BlueLeft,true, true);
    }
}
