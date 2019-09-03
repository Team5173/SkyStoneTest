package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.CRServo;

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
    private CRServo arm;

    double armPower;

    public void init() {
        telemetry.addData("Status", "Initialized");

        //Motor Names set to DcMotor Classes
        leftDrive  = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");

        //Motors given a direction
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        //SERVO names classes
        clamp = hardwareMap.get(Servo.class,"clamp");
        arm = hardwareMap.get(CRServo.class,"arm");
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
        arm.setPower(armPower);

        if (gamepad1.right_stick_y > 0.1) {
            armPower = .20;
        } else if (gamepad1.right_stick_y < -0.1) {
            armPower = -.20;
        } else {
            armPower = 0;
        }
        if (gamepad1.x){
            clamp.setPosition(0.25);
        } else if (gamepad1.b){
            clamp.setPosition(0);
        }
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
    }

    public void stop() {
    }

}
