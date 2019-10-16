import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

//Gives the name and group of the program for location on phone
@TeleOp(name="Sample TeleOp", group="LinearOpMode")

public class sampleTeleOp extends OpMode
{
	//instance variables for each motor
	DcMotor frontLeft;
	DcMotor frontRight;
	DcMotor backLeft;
	DcMotor backRight;
	
	double leftPower;
	double rightPower;
	
	@overide
	//initializes motors, servos, etc.
	//will show as the "intit" button on phone
	public void init()
	{
		//initializes each motor and assigns it a name on the phone
		frontLeft = hardwareMap.dcMotor.get("front_left");
		frontRight = hardwareMap.dcMotor.get("front_right");
		frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
		backLeft = hardwareMap.dcMotor.get("back_left");
		backRight = hardwareMap.dcMotor.get("back_right");
		backRight.setDirection(DcMotorSimple.Direction.REVERSE);
	}
	
	@overide
	//everything inside this method is looped until STOP is pressed on phone
	//will show as the play button on the phone
	public void loop()
	{
		leftPower = gamepad1.left_stick_y;
		rightPower = gamepad1.right_stick_y;
		
		//4 wheel drive
		frontLeft.setPower(leftPower);
		backLeft.setPower(leftPower);
		frontRight.setPower(rightPower);
		backRight.setPower(rightPower);
	}
}
