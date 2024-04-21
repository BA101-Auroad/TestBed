package frc.robot.subsystems;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.commands.FollowPathHolonomic;
import com.pathplanner.lib.path.PathPlannerPath;
import com.pathplanner.lib.util.HolonomicPathFollowerConfig;
import com.pathplanner.lib.util.PIDConstants;
import com.pathplanner.lib.util.ReplanningConfig;
import com.team5430.util.SwerveModule;
import com.pathplanner.lib.pathfinding.*;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TestBed extends SubsystemBase{
    
    public TestBed(){    // Configure AutoBuilder last
  AutoBuilder.configureHolonomic(null, // Robot pose supplier
  null,  // Method to reset odometry (will be called if your auto has a starting pose)
  null, // ChassisSpeeds supplier. MUST BE ROBOT RELATIVE
  null, // Method that will drive the robot given ROBOT RELATIVE ChassisSpeeds
  null, 
  null, 
  null
  );    
    new HolonomicPathFollowerConfig( // HolonomicPathFollowerConfig, this should likely live in your Constants class
      new PIDConstants(0.0, 0.0, 0.0),  //Drive PID constants
      new PIDConstants(5.0,0.0,0.0), //Rotation PID constants
      4.5, //max module speed in m/s
      0.4, // Drive base radius in meters. Distance from robot center to furthest module.
      new ReplanningConfig() // Default path replanning config. See the API for the options here
    );

  }
  public Command followPathCommand(String pathName) {
    PathPlannerPath path = PathPlannerPath.fromPathFile(pathName);
   return new FollowPathHolonomic(path,
    null,
   null,
    null,
    null, 
    null,       
    null);
    
  }
//constants
    private double kP = .15;
    private double driveGearRatio;
    private double angleGearRatio;

    SwerveModule module = new SwerveModule(0, 1);

    public void motorConfig(){
        module.SetGains(kP, kP);
        module.setAngleRatio(angleGearRatio);
        module.setDriveRatio(driveGearRatio);
    }

    public void drive(double direction, double power){
        module.setAngle(direction);
        module.setThrottle(power);
    }

}
