package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
public class Team_Red extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    //Motor Name's
    private DcMotor FL;
    private DcMotor FR;
    private DcMotor BL;
    private DcMotor BR;

    //Servo Name's
    private CRServo leftArm;
    private CRServo rightArm;
    private CRServo wrist;
    private Servo claw;

    double leftarmPower, rightarmPower, wristPower, clawPower;

    public void init() {
        telemetry.addData("Status", "Initialized");

        //Declaring Motor Name's To The DcMotor Class
        FL  = hardwareMap.get(DcMotor.class, "FL");
        FR  = hardwareMap.get(DcMotor.class, "FR");
        BL  = hardwareMap.get(DcMotor.class, "BL");
        BR  = hardwareMap.get(DcMotor.class, "BR");

        //Motor's Direction Declaration
        FL.setDirection(DcMotor.Direction.FORWARD);
        BR.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.FORWARD);
        FR.setDirection(DcMotor.Direction.REVERSE);

        //Declaring Servo Name's To The Servo Class
        leftArm = hardwareMap.get(CRServo.class, "leftArm");
        rightArm = hardwareMap.get(CRServo.class, "rightArm");
        wrist = hardwareMap.get(CRServo.class, "wrist");
        claw = hardwareMap.get(Servo.class, "claw");

        telemetry.addData("Status", "Initialized");
    }

    public void init_loop() {
    }

    public void start() {
        runtime.reset();
    }

    public void loop() {

        //Stating The Left and Right Motor Power
        double leftPower;
        double rightPower;

        //Setting the Motors To a Joystick on The Gamepad
        double drive = -gamepad1.left_stick_y;
        double turn  =  gamepad1.left_stick_x;
        leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
        rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

        //Setting Motors to Associated Side
        FL.setPower(leftPower);
        FR.setPower(rightPower);
        BL.setPower(leftPower);
        BR.setPower(rightPower);

        //Setting Servo to Speed
        leftArm.setPower(leftarmPower);
        rightArm.setPower(rightarmPower);
        wrist.setPower(wristPower);

        if (gamepad1.right_stick_y > 0.1) {
            leftarmPower = .20;
            rightarmPower = -.20;
        } else if (gamepad1.right_stick_y < -0.1) {
            leftarmPower = -.20;
            rightarmPower = .20;
        } else {
            leftarmPower = 0;
            rightarmPower = 0;
        }

        if (gamepad1.right_trigger > 0.1) {
            wristPower = .20;
        } else if (gamepad1.left_trigger < -0.1){
            wristPower = -.20;
        } else{
            wristPower = 0.0;
        }

        if (gamepad1.x){
            claw.setPosition(0.25);
        } else if (gamepad1.b){
            claw.setPosition(0);
        }

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
    }

    public void stop() {
    }

}