package com.samples;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.exceptions.OllamaBaseException;
import io.github.ollama4j.models.chat.OllamaChatMessageRole;
import io.github.ollama4j.models.chat.OllamaChatRequest;
import io.github.ollama4j.models.chat.OllamaChatRequestBuilder;
import io.github.ollama4j.models.chat.OllamaChatResult;
import io.github.ollama4j.models.chat.OllamaChatMessage;
import io.github.ollama4j.utils.Options;
import io.github.ollama4j.utils.OptionsBuilder;

//https://github.com/ollama4j/ollama4j/blob/main/docs/docs/apis-generate/chat.md
//https://ollama4j.github.io/ollama4j/apis-model-management/create-model

public class OllamaChatSample {
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";

    public static void main(String[] args) throws OllamaBaseException, IOException, InterruptedException, URISyntaxException {
        OllamaAPI ollamaAPI = new OllamaAPI("http://127.0.0.1:11434");
        //ollamaAPI.setRequestTimeoutSeconds(10);

        String model = "mistral-nemo";
        //String model = "llama3.2";
        //String model = "phi-3-medium-128k-instruct";
        //String model = "phi-3.5-mini";
        //String model = "phi3";
        //String model = "phi3.5";

        OllamaChatRequestBuilder builder = OllamaChatRequestBuilder.getInstance(model);

        ArrayList<OllamaChatMessage> messages = new ArrayList<>();

        messages.add(new OllamaChatMessage(OllamaChatMessageRole.SYSTEM, """
            You are an AI assistant controlling a robot car.
            You take direction from a human operator and execute the commands.

            ## Rules:
            - The permitted basic directions are: FORWARD, BACKWARD, LEFT, RIGHT.
            - FORWARD and BACKWARD are followed by the distance in meters.
            - LEFT and RIGHT are followed by the angle in degrees.
            - When asked to perform a task identify if it falls into one of the permitted basic DIRECTION.
            - When asked to perform a more complex task which does not fall into one of the permitted basic DIRECTIONS, break it down into permitted basic DIRECTION.

            ## Output schema:
            DIRECTION_1:VALUE_1,DIRECTION_2:VALUE_2,...,DIRECTION_n:VALUE_n
            (where the DIRECTION is one of the permitted basic directions and where VALUE is a distance in meters or an angle in degrees, depending on the direction type)
            
            Respond only with the list of permitted basic directions and their values.
            Never add information, comments or reasoning to the responses.
            """));

        Options options = new OptionsBuilder()
            .setTemperature(0.1f)
            .setSeed(42)
            .build();

        OllamaChatRequest chatRequest = builder
            .withMessages(messages)
            .withOptions(options)
            .build();
        List<OllamaChatMessage> history = chatRequest.getMessages();

        try (Scanner scanner = new Scanner(System.in)) 
        {
            while (true) 
            {
                // read a new command from the user
                System.out.print("\n\n  " + RED + "User > " + RESET);
                String userInput = scanner.nextLine();
                if (userInput.equals("exit")) break;

                // prepare the request with the new command
                chatRequest = builder
                    .withMessages(history)
                    .withMessage(OllamaChatMessageRole.USER, userInput)
                    .build();

                // send the request to the model
                OllamaChatResult chatResult = ollamaAPI.chat(chatRequest);
                System.out.print("\n\n " + GREEN + "Robot > " + chatResult.getResponse() + RESET);

                // update the history with the new response
                history = chatResult.getChatHistory();
            }
        }
    }
}