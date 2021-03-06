package org.usfirst.frc.team3130.robot;


import edu.wpi.first.wpilibj.Joystick.ButtonType;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Joystick.ButtonType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;


/**
 * This is a demo program showing the use of the RobotDrive class, specifically it 
 * contains the code necessary to operate a robot with tank drive.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're inexperienced,
 * don't. Unless you know what you are doing, complex code will be much more difficult under
 * this system. Use IterativeRobot or Command-Based instead if you're new.
 */
public class Robot extends SampleRobot {
    RobotDrive myRobot;  // class that handles basic drive operations
    Joystick driveStick;  // set to ID 1 in DriverStation
    CANTalon talonLFront, talonLRear, talonRFront, talonRRear;

    Relay flashlight; 


    
    public Robot() {
    	talonLFront = new CANTalon(3);
    	talonLRear = new CANTalon(4);
    	talonRFront = new CANTalon(5);
    	talonRRear = new CANTalon(6);
        myRobot = new RobotDrive(talonLFront,talonLRear,talonRFront,talonRRear);
        myRobot.setExpiration(0.1);
        driveStick = new Joystick(1);
        flashlight = new Relay(3);

    }
    
    /**
     * Runs the motors with tank steering.
     */
    public void operatorControl() {
    	
        myRobot.setSafetyEnabled(true);
        while (isOperatorControl() && isEnabled()) {

        	myRobot.arcadeDrive(driveStick.getY() * -1 , -1 * driveStick.getX());
        	if (driveStick.getButton(ButtonType.kTrigger)) {
        		flashlight.set(Value.kForward);
        	}
        	else {
        		flashlight.set(Value.kOff);
        	}

            Timer.delay(0.005);		// wait for a motor update time
        }
    }

}
