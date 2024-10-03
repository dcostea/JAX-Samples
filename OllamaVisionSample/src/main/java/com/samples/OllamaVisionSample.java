package com.samples;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.exceptions.OllamaBaseException;
import io.github.ollama4j.models.chat.OllamaChatMessageRole;
import io.github.ollama4j.models.chat.OllamaChatRequest;
import io.github.ollama4j.models.chat.OllamaChatRequestBuilder;
import io.github.ollama4j.models.chat.OllamaChatResult;
import io.github.ollama4j.types.OllamaModelType;
import io.github.ollama4j.models.chat.OllamaChatMessage;

//https://github.com/ollama4j/ollama4j/blob/main/docs/docs/apis-generate/chat.md
//https://ollama4j.github.io/ollama4j/apis-model-management/create-model

public class OllamaVisionSample {
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";
    
    public static void main(String[] args) throws OllamaBaseException, IOException, InterruptedException, URISyntaxException {
        OllamaAPI ollamaAPI = new OllamaAPI("http://127.0.0.1:11434");
        //ollamaAPI.setRequestTimeoutSeconds(10);

        String imagePath = "c:\\Temp\\RoverImages\\m12.jpeg";

        String model = OllamaModelType.LLAVA_PHI3;
        OllamaChatRequestBuilder builder = OllamaChatRequestBuilder.getInstance(model);

        List<byte[]> images = List.of(Files.readAllBytes(new File(imagePath).toPath()));

        ArrayList<OllamaChatMessage> messages = new ArrayList<>();

        messages.add(new OllamaChatMessage(OllamaChatMessageRole.SYSTEM, """
            You are an AI assistant for a Mars rover.
            Your objective is to analyze the images for obstacles and any hazard.
            """));

        messages.add(new OllamaChatMessage(OllamaChatMessageRole.USER, """
            The attached image is an aerial view of you, the rover in the image.
            Find a safe passage to the distant hills avoid any obstacles.
            """,
            images));

        OllamaChatRequest chatRequest = builder
            .withMessages(messages)
            .build();
        OllamaChatResult chatResult = ollamaAPI.chat(chatRequest);
        System.out.print("\n\n " + GREEN + "Robot > " + chatResult.getResponse() + RESET);

        List<OllamaChatMessage> history = chatResult.getChatHistory();

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
                chatResult = ollamaAPI.chat(chatRequest);
                System.out.print("\n\n " + GREEN + "Robot > " + chatResult.getResponse() + RESET);

                // update the history with the new response
                history = chatResult.getChatHistory();
            }
        }
    }
}
