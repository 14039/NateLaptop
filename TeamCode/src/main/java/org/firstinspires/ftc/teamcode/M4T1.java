package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Bot Two Tele Op", group="MecanumDrive")
public class M4T1 extends OpMode {
    private DcMotor leftFrontMotor;
    private DcMotor leftBackMotor;
    private DcMotor rightFrontMotor;
    private DcMotor rightBackMotor;

    private DcMotor rightLift;
    private DcMotor leftIntake, rightIntake;

    private Servo leftConveyor;
    private Servo rightConveyor;
    private Servo leftFlipper;
    private Servo rightFlipper;

    boolean reverse = false;
    ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);

    double x1, x2, y1, y2;

    public void init() {
        leftFrontMotor = hardwareMap.dcMotor.get("leftFront");
        leftBackMotor = hardwareMap.dcMotor.get("leftBack");
        rightFrontMotor = hardwareMap.dcMotor.get("rightFront");
        rightBackMotor = hardwareMap.dcMotor.get("rightBack");

        leftIntake = hardwareMap.dcMotor.get("leftIntake");
        rightIntake = hardwareMap.dcMotor.get("rightIntake");

     //   leftLift = hardwareMap.dcMotor.get("leftLift");
        rightLift = hardwareMap.dcMotor.get("rightLift");

      //  leftLift.setDirection(DcMotorSimple.Direction.REVERSE);
        rightLift.setDirection(DcMotorSimple.Direction.REVERSE);
        rightIntake.setDirection(DcMotorSimple.Direction.REVERSE);

     //   dumper = hardwareMap.servo.get("dumper");
        leftFlipper = hardwareMap.servo.get("leftFlipper");
        rightFlipper = hardwareMap.servo.get("rightFlipper");

        leftConveyor = hardwareMap.servo.get("leftConveyor");
        rightConveyor = hardwareMap.servo.get("rightConveyor");

     //   sensorArm = hardwareMap.servo.get("sensorArm");

        leftFrontMotor.setPower(0);
        leftBackMotor.setPower(0);
        rightFrontMotor.setPower(0);
        rightBackMotor.setPower(0);

        leftIntake.setPower(0);
        rightIntake.setPower(0);

       // leftLift.setPower(0);
        rightLift.setPower(0);

        leftConveyor.setPosition(0.5);
        rightConveyor.setPosition(0.5);
        rightFlipper.setPosition(.3);
        leftFlipper.setPosition(.7);
       // dumper.setPosition(1);

       // sensorArm.setPosition(1);
    }

    public void start() {
    }

    public void loop() {
        y2 = -gamepad1.left_stick_y;
        y1 = -gamepad1.right_stick_y;
        x1 = gamepad1.right_stick_x;
        x2 = gamepad1.left_stick_x;

        telemetry.addData("y1", y1);
        telemetry.addData("y2", y2);
        telemetry.addData("x1", x1);
        telemetry.addData("x2", x2);

   //     sensorArm.setPosition(1);

        if(gamepad1.b && timer.time() > 0.25) {
            reverse = !reverse;
        }

        if (!reverse) {
            leftFrontMotor.setPower(y2 + x2);
            leftBackMotor.setPower(y2 - x2);
            rightFrontMotor.setPower(-y1 + x1);
            rightBackMotor.setPower(-y1 - x1);

        } else {
            leftFrontMotor.setPower(y2 + x1);
            leftBackMotor.setPower(y2 - x1);
            rightFrontMotor.setPower(-y1 + x2);
            rightBackMotor.setPower(-y1 - x2);

        }
        if (gamepad2.left_bumper) {
            leftConveyor.setPosition(0);
            rightConveyor.setPosition(1);
        } else if(gamepad2.left_trigger > .2) {
            leftConveyor.setPosition(1);
            rightConveyor.setPosition(0);
        } else {
            leftConveyor.setPosition(0.5);
            rightConveyor.setPosition(0.5);
        }

        if (gamepad1.dpad_up || gamepad2.dpad_up) {
           // leftLift.setPower(0.7);
            rightLift.setPower(1);
        } else if (gamepad1.dpad_down || gamepad2.dpad_down) {
          //  leftLift.setPower(-0.7);
            rightLift.setPower(-1);
        } else {
          //  leftLift.setPower(0);
            rightLift.setPower(0);
        }

        leftIntake.setPower(gamepad2.left_stick_y);
        rightIntake.setPower(gamepad2.right_stick_y);

       if (gamepad1.left_trigger > .2 ||gamepad2.left_trigger > .2) {
              rightFlipper.setPosition(1);
              leftFlipper.setPosition(0);
       } else if (gamepad2.a) {
           rightFlipper.setPosition(.5);
           leftFlipper.setPosition(.5);
       } else{
           rightFlipper.setPosition(.3);
           leftFlipper.setPosition(.7);
       }
       // dumper.setPosition(1-gamepad1.left_trigger);

        telemetry.addData("reversed", reverse);
//        telemetry.addData("leftIntake", 0.75*gamepad2.left_stick_y);
//        telemetry.addData("rightIntake", 0.75*gamepad2.right_stick_y);

        telemetry.update();
    }

    public void stop() {
        leftFrontMotor.setPower(0);
        leftBackMotor.setPower(0);
        rightFrontMotor.setPower(0);
        rightBackMotor.setPower(0);

        rightIntake.setPower(0);
        leftIntake.setPower(0);

      //  leftLift.setPower(0);
        rightLift.setPower(0);

        leftConveyor.setPosition(0.5);
        rightConveyor.setPosition(0.5);
    }



}


