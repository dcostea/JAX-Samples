package com.samples;

import java.io.IOException;
import java.net.URISyntaxException;

import com.azure.core.credential.KeyCredential;
import com.microsoft.semantickernel.Kernel;
import com.microsoft.semantickernel.aiservices.huggingface.HuggingFaceClient;
import com.microsoft.semantickernel.aiservices.huggingface.models.TextGenerationRequest;
import com.microsoft.semantickernel.aiservices.huggingface.services.HuggingFacePromptExecutionSettings;
import com.microsoft.semantickernel.aiservices.huggingface.services.HuggingFaceTextGenerationService;
import com.microsoft.semantickernel.orchestration.PromptExecutionSettings;
import com.microsoft.semantickernel.semanticfunctions.KernelFunctionFromPrompt;

public class SemanticKernelChatSample {
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
	
        String HFKey = "huggingface key";
        String HFEndpoint = "huggingface api endpoint";
        String ModelId = "mistralai/Mistral-7B-Instruct-v0.3";
        String ServiceId = "mistral-7B-instruct-v0.3";

        HuggingFaceClient client = HuggingFaceClient.builder()
            .credential(new KeyCredential(HFKey))
            .endpoint(HFEndpoint)
            .build();

        HuggingFaceTextGenerationService chatCompletion = HuggingFaceTextGenerationService.builder()
            .withModelId(ModelId)
            .withServiceId(ServiceId)
            .withHuggingFaceClient(client)
            .build();

        Kernel kernel = Kernel.builder()
            .withAIService(HuggingFaceTextGenerationService.class, chatCompletion)
            .build();

        PromptExecutionSettings promptExecutionSettings = PromptExecutionSettings.builder()
            .withMaxTokens(100)
            .withTemperature(0.5)
            .withTopP(0.9)
            //.withResponseFormat(ResponseFormat.Type.JSON_SCHEMA)
            //.withJsonSchemaResponseFormat(GeneratedTextItem.class)
            .build();

        HuggingFacePromptExecutionSettings huggingFacePromptExecutionSettings = HuggingFacePromptExecutionSettings.fromExecutionSettings(promptExecutionSettings);

        var questionAnswerFunction = KernelFunctionFromPrompt.builder()
            .withTemplate("What is a black hole?")
        .build();

        TextGenerationRequest textGenerationRequest = TextGenerationRequest.fromPromptAndExecutionSettings("What is a black hole?", huggingFacePromptExecutionSettings);

        //var result1 = kernel.invokePromptAsync("What is a black hole?")
        var result1 = kernel.invokeAsync(questionAnswerFunction)
            .withPromptExecutionSettings(promptExecutionSettings)
            .withResultType(String.class)
            .block();

    }
}
