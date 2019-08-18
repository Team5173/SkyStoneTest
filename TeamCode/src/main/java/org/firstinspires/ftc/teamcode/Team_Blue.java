package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Team Blue Code", group="Iterative Opmode")
public class Team_Blue extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    //Motors
    private DcMotor leftDrive;
    private DcMotor rightDrive;

    //Servos
    private Servo clamp;


    public void init() {
        telemetry.addData("Status", "Initialized");

        //Motor Names set to DcMotor Classes
        leftDrive  = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");

        //Motors given a direction
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
    }

    public void init_loop() {
    }

    public void start() {
        runtime.reset();
    }

    public void loop() {

        //All the Motor Powers and Joystick Calculations
        double leftPower;
        double rightPower;

        double drive = -gamepad1.left_stick_y;
        double turn  =  gamepad1.right_stick_x;
        leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
        rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

        leftDrive.setPower(leftPower);
        rightDrive.setPower(rightPower);



        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
    }

    public void stop() {
    }

}
