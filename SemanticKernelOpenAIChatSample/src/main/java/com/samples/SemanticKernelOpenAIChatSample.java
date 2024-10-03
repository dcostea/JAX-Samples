package com.samples;

import java.io.IOException;
import java.net.URISyntaxException;

import com.azure.ai.openai.OpenAIAsyncClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.core.credential.AzureKeyCredential;
import com.microsoft.semantickernel.Kernel;
import com.microsoft.semantickernel.aiservices.openai.chatcompletion.OpenAIChatCompletion;
import com.microsoft.semantickernel.semanticfunctions.KernelFunctionFromPrompt;
import com.microsoft.semantickernel.services.chatcompletion.ChatCompletionService;
import com.microsoft.semantickernel.services.chatcompletion.ChatHistory;
import com.microsoft.semantickernel.services.chatcompletion.message.ChatMessageImageContent;
import com.microsoft.semantickernel.semanticfunctions.KernelFunctionArguments;

public class SemanticKernelOpenAIChatSample {
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
	
        String HFKey = "openAI key";
        String HFEndpoint = "openAI api endpoint";
        String ModelId = "gpt-4o";
        //String ServiceId = "gpt-4o";

        OpenAIAsyncClient client = new OpenAIClientBuilder()
            .credential(new AzureKeyCredential(HFKey))
            .endpoint(HFEndpoint)
            .buildAsyncClient();

        ChatCompletionService chatCompletion = OpenAIChatCompletion.builder()
            .withModelId(ModelId)
            .withOpenAIAsyncClient(client)
            .build();

        Kernel kernel = Kernel.builder()
            .withAIService(ChatCompletionService.class, chatCompletion)
            .build();
            
        ChatHistory chatHistory = new ChatHistory("You look at images and describe them");

        // First user message
        chatHistory.addUserMessage("Describe the following image");
        chatHistory.addMessage(ChatMessageImageContent.builder()
            .withImageUrl("https://cr.openjdk.org/~jeff/Duke/jpg/Welcome.jpg")
            .build());


        var result = chatCompletion.getChatMessageContentsAsync(chatHistory, kernel, null)
            .block();
        System.out.println(result.getLast().getContent());
        System.out.println("--------------------");

        //chatHistory.addAssistantMessage(result.getFirst().toString());

        var questionAnswerFunction = KernelFunctionFromPrompt.builder()
            .withTemplate("Question: {{$input}}; Answer:")
            .build();
        
        var result2 = questionAnswerFunction.invokeAsync(kernel)
            .withArguments(
                KernelFunctionArguments.builder()
                    .withVariable("input", "What is a black hole?")
                    .build())
            .block();
        System.out.println(result2.getResult());
        System.out.println("--------------------");

        var result1 = kernel.invokeAsync(questionAnswerFunction)
            .withArguments(
                KernelFunctionArguments.builder()
                    .withVariable("input", "What is a black hole?")
                    .build())
            .block();

            System.out.println(result1.getResult());
            System.out.println("--------------------");

    }
}
