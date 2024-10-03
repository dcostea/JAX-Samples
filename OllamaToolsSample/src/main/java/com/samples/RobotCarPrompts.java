package com.samples;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.ollama4j.tools.Tools;

public class RobotCarPrompts {
    public static String findSafePassagePrompt;
    static {
        try {
            findSafePassagePrompt = new Tools.PromptBuilder()
                .withToolSpecification(RobotCarTools.chooseToolSpecification)
                .withPrompt("""
                    You are a robot car AI assistant responsible for the safety of the robot car.

                    You can go in three different directions:
                    - Forward: In front there is a large crater
                    - Left: In left there is no obstacle
                    - Right: In right is a large boulder

                    Which direction is safe for the robot car to go?
                    """)
                .build();
        } catch (JsonProcessingException e) {
            System.out.println("Error processing JSON when building the prompt: " + e.getMessage());
        }
    }

    public static String findPrompt;
    static {
        try {
            findPrompt = new Tools.PromptBuilder()
                .withToolSpecification(RobotCarTools.chooseToolSpecification)
                .withPrompt("""   
                    You are a robot car AI assistant responsible for the safety of the robot car.

                    Outside temperature is almost freezing temperature, still the water is not frozen.

                    Decide What is the next safest direction to go for the robot car.
                    Show direction, do not explain, proceed immediately to that direction.
                    """)
                .build();
        } catch (JsonProcessingException e) {
            System.out.println("Error processing JSON when building the prompt: " + e.getMessage());
        }
    }

    public static String breakdownComplexMovementPrompt;
    static {
        try {
            breakdownComplexMovementPrompt = new Tools.PromptBuilder()
                //.withToolSpecification(RobotCarTools.executeSequenceToolSpecification)
                .withToolSpecification(RobotCarTools.leftToolSpecification)
                .withToolSpecification(RobotCarTools.rightToolSpecification)
                .withToolSpecification(RobotCarTools.forwardToolSpecification)
                .withToolSpecification(RobotCarTools.backwardToolSpecification)
                .withPrompt("""
                    You are an AI assistant specialized in robotics and motion planning.
                    The robot car can move in these directions: forward, backward, left, and right.
                    The forward and backward directions are followed by a distance in meters.
                    The left and right directions are followed by an angle in degrees.

                    ## Output schema:
                    DIRECTION1:VALUE1,DIRECTION2:VALUE2,...,DIRECTIONN:VALUEN

                    ## Task to break down
                    Go in circle for 360 degrees.
                    
                    Show the sequence of basic movements as resulting from breaking down the task.
                    Execute the broken down tasks (the basic movements).
                    """)
                .build();
        } catch (JsonProcessingException e) {
            System.out.println("Error processing JSON when building the prompt: " + e.getMessage());
        }
    }

    public static String detectIntentionPrompt;
    static {
        try {
            detectIntentionPrompt = new Tools.PromptBuilder()
                .withToolSpecification(RobotCarTools.leftToolSpecification)
                .withToolSpecification(RobotCarTools.rightToolSpecification)
                .withToolSpecification(RobotCarTools.forwardToolSpecification)
                .withToolSpecification(RobotCarTools.backwardToolSpecification)
                .withPrompt("""
                    You are an AI assistant specialized in robotics and motion planning.
                    The robot car can move in these directions: forward, backward, left, and right.
                    The forward and backward directions are followed by a distance in meters.
                    The left and right directions are followed by an angle in degrees.

                    Which is the intention of the next task?

                    ## Output schema:
                    DIRECTION1:VALUE1,DIRECTION2:VALUE2,...,DIRECTIONN:VALUEN

                    # Task:
                    Go in circle for 360 degrees.
                """)
                .build();
        } catch (JsonProcessingException e) {
            System.out.println("Error processing JSON when building the prompt: " + e.getMessage());
        }
    }

    public static String sequenceOfReadingsPrompt;
    static {
        try {
            sequenceOfReadingsPrompt = new Tools.PromptBuilder()
                .withToolSpecification(RobotCarTools.luminosityToolSpecification)
                .withToolSpecification(RobotCarTools.temperatureToolSpecification)
                .withToolSpecification(RobotCarTools.distanceToolSpecification)
                //.withToolSpecification(RobotCarTools.windDirectionToolSpecification)
                //.withToolSpecification(RobotCarTools.windSpeedToolSpecification)
                .withPrompt("""
                    You are an AI assistant controlling a robot car.
                    
                    # Tasks:
                    1. Reads the temperature.
                    2. Reads again the temperature.
                    3. Gets the luminosity.
                """)
                .build();
        } catch (JsonProcessingException e) {
            System.out.println("Error processing JSON when building the prompt: " + e.getMessage());
        }
    }

    public static String goPrompt;
    static {
        try {
            goPrompt = new Tools.PromptBuilder()
                .withToolSpecification(RobotCarTools.leftToolSpecification)
                .withToolSpecification(RobotCarTools.rightToolSpecification)
                .withToolSpecification(RobotCarTools.forwardToolSpecification)
                .withToolSpecification(RobotCarTools.backwardToolSpecification)
                .withPrompt("""
                    You are an AI assistant controlling a robot car.
                    
                    # Tasks:
                    1. Go left 45 degrees.
                    2. Go forward 100 meters.
                    3. Go back 10 meters.
                """)
                .build();
        } catch (JsonProcessingException e) {
            System.out.println("Error processing JSON when building the prompt: " + e.getMessage());
        }
    }

    public static String smartGoPrompt;
    static {
        try {
            smartGoPrompt = new Tools.PromptBuilder()
                .withToolSpecification(RobotCarTools.leftToolSpecification)
                .withToolSpecification(RobotCarTools.rightToolSpecification)
                .withToolSpecification(RobotCarTools.forwardToolSpecification)
                .withToolSpecification(RobotCarTools.backwardToolSpecification)
                .withPrompt("""
                    You are an AI assistant controlling a robot car.
                    
                    # Task:
                    Go on a square path of 10 by 10 meters.
                    Split this task in a sequence of into basic movements and execute them.
                """)
                .build();
        } catch (JsonProcessingException e) {
            System.out.println("Error processing JSON when building the prompt: " + e.getMessage());
        }
    }
}
