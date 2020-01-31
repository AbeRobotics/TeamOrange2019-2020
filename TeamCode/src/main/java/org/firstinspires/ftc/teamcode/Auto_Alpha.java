package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Auto - Alpha (TESTING)", group="Autonomous")
public class Auto_Alpha extends LinearOpMode
{
    private ElapsedTime runTime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException
    {
        Task_TestIMU_Alpha imuTest = new Task_TestIMU_Alpha();
        Task_TestMovement_General movementTest = new Task_TestMovement_General();

        imuTest.Init(telemetry, hardwareMap);

        waitForStart();

        /*while(movementTest.getStatus() == false)
        {
            movementTest.performTask(TaskBase.wheelSpeed.Fast);
            sleep(100);
        }*/

        while(imuTest.getStatus() == false)
        {
            imuTest.performTask(TaskBase.wheelSpeed.Fast);
            sleep(100);
        }
    }
}
