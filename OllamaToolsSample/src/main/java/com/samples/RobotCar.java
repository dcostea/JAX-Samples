package com.samples;

import java.util.Map;
import java.util.Random;

class RobotCar {
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";

    public static String forward(Map<String, Object> arguments) {
        String distance = (arguments != null) ? arguments.getOrDefault("distance", "NONE").toString() : "NONE";
        System.out.println(GREEN + "Distance: " + distance + " - moving forward" + RESET);
        return "Robot car moved forward " + distance;
    }

    public static String backward(Map<String, Object> arguments) {
        String distance = (arguments != null) ? arguments.getOrDefault("distance", "NONE").toString() : "NONE";
        System.out.println(GREEN + "Distance: " + distance + " - moving backward" + RESET);
        return "Robot car moved backward " + distance;
    }

    public static String right(Map<String, Object> arguments) {
        String angle = (arguments != null) ? arguments.getOrDefault("angle", "NONE").toString() : "NONE";
        System.out.println(GREEN + "Angle: " + angle + " - turning right" + RESET);
        return "Robot car turned right " + angle;
    }

    public static String left(Map<String, Object> arguments) {
        String angle = (arguments != null) ? arguments.getOrDefault("angle", "NONE").toString() : "NONE";
        System.out.println(GREEN + "Angle: " + angle + " - turning left" + RESET);
        return "Robot car turned left " + angle;
    }

    public static String choose(Map<String, Object> arguments) {
        String direction = (arguments != null) 
            ? arguments.getOrDefault("direction", "none").toString() 
            : "none";
        if (direction.toUpperCase().contains("FORWARD")) {
            System.out.println(GREEN + "Direction: FORWARD" + RESET);
            return "Robot car goes forward!";
        }
        else if (direction.toUpperCase().contains("LEFT")) {
            System.out.println(GREEN + "Direction: LEFT" + RESET);
            return "Robot car goes left!";
        }
        else if (direction.toUpperCase().contains("RIGHT")) {
            System.out.println(GREEN + "Direction: RIGHT" + RESET);
            return "Robot car goes right!";
        }
        else if (direction.toUpperCase().contains("BACKWARD")) {
            System.out.println(GREEN + "Direction: BACKWARD" + RESET);
            return "Robot car goes backward!";
        }
        else {
            System.out.println(RED + "Direction: INVALID" + RESET);
            return "Robot car is resuming the direction!";
        }
    }

    public static String execute(Map<String, Object> arguments) {
        String action = arguments.get("action").toString();
        System.out.println(GREEN + "Sequence: " + action + RESET);
        String[] output = action.split(",");
        for (String command : output) {
            String[] parts = command.split(":");
            switch (parts[0].toUpperCase()) {
                case "FORWARD" -> System.out.println(GREEN + "Direction: " + parts[0].toUpperCase() + ", Distance: " + parts[1] + RESET);
                case "BACKWARD" -> System.out.println(GREEN + "Direction: " + parts[0].toUpperCase() + ", Distance: " + parts[1] + RESET);
                case "LEFT" -> System.out.println(GREEN + "Direction: " + parts[0].toUpperCase() + ", Angle: " + parts[1] + RESET);
                case "RIGHT" -> System.out.println(GREEN + "Direction: " + parts[0].toUpperCase() + ", Angle: " + parts[1] + RESET);
                default -> System.out.println(RED + "Invalid command: " + command.toUpperCase() + RESET);
            }
        }  
        return "Basic sequence: " + action;
    }

    public static String temperature(Map<String, Object> arguments) {
        Random random = new Random();
        int temperature = random.nextInt(11) + 20;
        System.out.println(GREEN + "Temperature: " + temperature + "°C" + RESET);
        return "The outside temperature is " + temperature + "°C";
    }

    public static String humidity(Map<String, Object> arguments) {
        Random random = new Random();
        int humidity = random.nextInt(10000);
        System.out.println(GREEN + "Humidity: " + humidity + " x" + RESET);
        return "The humidity is " + humidity + " x";
    }

    public static String distance(Map<String, Object> arguments) {
        Random random = new Random();
        String obstacle = arguments.get("obstacle").toString();
        int distance = random.nextInt(10000);
        System.out.println(GREEN + "Distance: " + distance + " meters to " + obstacle + RESET);
        return "The distance is " + distance + " meters to " + obstacle;
    }

    public static String windDirection(Map<String, Object> arguments) {
        String[] windDirections = {"North", "South", "East", "West", "Northeast", "Northwest", "Southeast", "Southwest"};
        Random random = new Random();
        int randomIndex = random.nextInt(windDirections.length);
        String direction = windDirections[randomIndex];
        System.out.println(GREEN + "Wind direction: " + direction + RESET);
        return "The wind direction is " + direction;
    }

    public static String windSpeed(Map<String, Object> arguments) {
        Random random = new Random();
        int speed = random.nextInt(1000);
        System.out.println(GREEN + "Wind speed: " + speed + " kmph" + RESET);
        return "The wind speed is " + speed + " kmph";
    }

    public static String luminosity(Map<String, Object> arguments) {
        Random random = new Random();
        int luminosity = random.nextInt(100000);
        System.out.println(GREEN + "Luminosity: " + luminosity + " lux" + RESET);
        return "The luminosity is " + luminosity + " lux";
    }
}
