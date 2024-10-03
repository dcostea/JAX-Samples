package com.samples;

import java.io.IOException;
import java.net.URISyntaxException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.exceptions.OllamaBaseException;
import io.github.ollama4j.exceptions.ToolInvocationException;
import io.github.ollama4j.tools.OllamaToolsResult;
import io.github.ollama4j.utils.Options;
import io.github.ollama4j.utils.OptionsBuilder;

//https://ollama4j.github.io/ollama4j/apis-model-management/create-model
 
public class OllamaToolsSample {
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";

    public static void main(String[] args) throws OllamaBaseException, IOException, InterruptedException, URISyntaxException, ToolInvocationException {
        OllamaAPI ollamaAPI = new OllamaAPI("http://127.0.0.1:11434");
        ollamaAPI.setVerbose(true);
        
        var model = "mistral-nemo";
        //var model = "mistral";
        //var model = "llama3.2";

        ollamaAPI.registerTool(RobotCarTools.forwardToolSpecification);
        ollamaAPI.registerTool(RobotCarTools.backwardToolSpecification);
        ollamaAPI.registerTool(RobotCarTools.leftToolSpecification);
        ollamaAPI.registerTool(RobotCarTools.rightToolSpecification);
        ollamaAPI.registerTool(RobotCarTools.executeSequenceToolSpecification);
        ollamaAPI.registerTool(RobotCarTools.chooseToolSpecification);
        ollamaAPI.registerTool(RobotCarTools.temperatureToolSpecification);

        //var prompt = RobotCarPrompts.findSafePassagePrompt;
        //var prompt = RobotCarPrompts.breakdownComplexMovementPrompt;
        //var prompt = RobotCarPrompts.detectIntentionPrompt;
        //var prompt = RobotCarPrompts.sequenceOfReadingsPrompt;
        //var prompt = RobotCarPrompts.goPrompt;
        var prompt = RobotCarPrompts.smartGoPrompt;

        try {
            Options options = new OptionsBuilder()
                .setTemperature(0.1f)
                .setSeed(42)
                .build();
            OllamaToolsResult toolsResult = ollamaAPI.generateWithTools(model, prompt, options);
            for (OllamaToolsResult.ToolResult r : toolsResult.getToolResults()) {
                System.out.printf(GREEN + "[TOOL '%s']: %s%n" + RESET, r.getFunctionName(), r.getResult().toString());
            }
        } catch (JsonMappingException | JsonParseException | ToolInvocationException e) {  
            System.out.printf(RED + e.getMessage() + RESET);
        }
    }
}
