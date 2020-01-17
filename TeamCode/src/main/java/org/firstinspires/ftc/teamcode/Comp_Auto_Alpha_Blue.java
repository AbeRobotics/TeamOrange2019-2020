package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Auto - Alpha, Blue Team", group="Autonomous")
public class Comp_Auto_Alpha_Blue extends LinearOpMode
{
    private ElapsedTime runTime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException
    {
        Task_TestMovement_General moveTest = new Task_TestMovement_General();
        moveTest.Init(telemetry, hardwareMap);
        while(moveTest.getStatus() == false)
        {
            moveTest.performTask(TaskBase.wheelSpeed.Intermediate);
            sleep(100);
        }
    }
}
