package com.samples;

import io.github.ollama4j.tools.Tools;

public class RobotCarTools {

    public static Tools.ToolSpecification forwardToolSpecification = Tools.ToolSpecification.builder()
        .functionName("forward")
        .functionDescription("Basic command: Moves the robot car forward.")
        .toolDefinition(RobotCar::forward)
        .properties(new Tools.PropsBuilder()
            .withProperty("distance", Tools.PromptFuncDefinition.Property.builder()
                .type("string")
                .description("The distance to move the robot car.")
                .required(false).build())
            .build())
        .build();
        
    public static Tools.ToolSpecification backwardToolSpecification = Tools.ToolSpecification.builder()
        .functionName("backward")
        .functionDescription("Basic command: Moves the robot car backward.")
        .toolDefinition(RobotCar::backward)
        .properties(new Tools.PropsBuilder()
            .withProperty("distance", Tools.PromptFuncDefinition.Property.builder()
                .type("string")
                .description("The distance to move the robot car.")
                .required(false).build())
            .build())
        .build();

    public static Tools.ToolSpecification leftToolSpecification = Tools.ToolSpecification.builder()
        .functionName("left")
        .functionDescription("Basic command: Turns the robot car anti-clockwise.")
        .toolDefinition(RobotCar::left)
        .properties(new Tools.PropsBuilder()
            .withProperty("angle", Tools.PromptFuncDefinition.Property.builder()
                .type("string")
                .description("The angle (°) to rotate the robot car.")
                .required(false).build())
            .build())
        .build();

    public static Tools.ToolSpecification rightToolSpecification = Tools.ToolSpecification.builder()
        .functionName("right")
        .functionDescription("Basic command: Turns the robot car clockwise.")
        .toolDefinition(RobotCar::right)
        .properties(new Tools.PropsBuilder()
            .withProperty("angle", Tools.PromptFuncDefinition.Property.builder()
                .type("string")
                .description("The angle (°) to rotate the robot car.")
                .required(false).build())
            .build())
        .build();

    public static Tools.ToolSpecification executeSequenceToolSpecification = Tools.ToolSpecification.builder()
        .functionName("execute")
        .functionDescription("Executes a sequence of movements such as: left, right, forward, and backward.")
        .toolDefinition(RobotCar::execute)
        .properties(new Tools.PropsBuilder()
            .withProperty("action", Tools.PromptFuncDefinition.Property.builder()
                .type("string")
                .description("The movements sequence.")
                .required(true).build())
            .build())
        .build();

    public static Tools.ToolSpecification chooseToolSpecification = Tools.ToolSpecification.builder()
        .functionName("choose")
        .functionDescription("Chooses the direction to go for robot car.")
        .toolDefinition(RobotCar::choose)
        .properties(new Tools.PropsBuilder()
            .withProperty("direction", Tools.PromptFuncDefinition.Property.builder()
                .type("string")
                .description("The direction where robot car is heading.")
                .required(true).build())
            .build())
        .build();

    public static Tools.ToolSpecification temperatureToolSpecification = Tools.ToolSpecification.builder()
        .functionName("temperature")
        .functionDescription("Robot car reads the outside temperature.")
        .toolDefinition(RobotCar::temperature)
        .properties(new Tools.PropsBuilder().build())
        .build();

    public static Tools.ToolSpecification humidityToolSpecification = Tools.ToolSpecification.builder()
        .functionName("humidity")
        .functionDescription("Robot car reads the air humidity.")
        .toolDefinition(RobotCar::humidity)
        .properties(new Tools.PropsBuilder().build())
        .build();

    public static Tools.ToolSpecification luminosityToolSpecification = Tools.ToolSpecification.builder()
        .functionName("luminosity")
        .functionDescription("Gets the outside luminosity readings from robot car sensor.")
        .toolDefinition(RobotCar::luminosity)
        .properties(new Tools.PropsBuilder().build())
        .build();

    public static Tools.ToolSpecification distanceToolSpecification = Tools.ToolSpecification.builder()
        .functionName("distance")
        .functionDescription("Robot car reads the distance to an obstacle.")
        .toolDefinition(RobotCar::distance)
        .properties(new Tools.PropsBuilder()
            .withProperty("obstacle", Tools.PromptFuncDefinition.Property.builder()
                .type("string")
                .description("The obstacle to check the distance to it.")
                .required(true).build())
            .build())
        .properties(new Tools.PropsBuilder().build())
        .build();

    public static Tools.ToolSpecification windDirectionToolSpecification = Tools.ToolSpecification.builder()
        .functionName("wind_direction")
        .functionDescription("Robot car reads the wind direction.")
        .toolDefinition(RobotCar::windDirection)
        .properties(new Tools.PropsBuilder().build())
        .build();

    public static Tools.ToolSpecification windSpeedToolSpecification = Tools.ToolSpecification.builder()
        .functionName("wind_speed")
        .functionDescription("Robot car reads the wind speed.")
        .toolDefinition(RobotCar::windSpeed)
        .properties(new Tools.PropsBuilder().build())
        .build();
}
