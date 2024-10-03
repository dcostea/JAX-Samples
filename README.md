# JAX-Samples
These are the GenAI samples presented at JAX London (Oct 2nd 2024) build with Ollama4j for small language models like mistral and phi-3.

## Ollama samples are working
### OllamaChatSample is showing a simple chatbot
### OllamaVisionSample is showing a simple chatbot with vision capabilities (explaining an image)
### OllamaToolsSample is showing a simple chatbot with tools capabilities (aka function calling)

## Attention! The Semantic Kernel samples don't run yet, because there is a bug with response deserialization in the HuggingFace AI service connector in Semantic Kernel library.

# Methodology
- Oracle JDK or Open JDK 11.0 or above.
- Maven
- Ollama server (runs the Ollama server locally at http://localhost:11434)

### Add the dependency to your project's pom.xml (update the version to the latest!).
```
<dependency>
  <groupId>io.github.ollama4j</groupId>
  <artifactId>ollama4j</artifactId>
  <version>1.0.84</version>
</dependency>
```

### And this is sample on how to initialize an OllamaAPI object
```
import io.github.ollama4j.OllamaAPI;

public class OllamaAPITest {
    public static void main(String[] args) {
        OllamaAPI ollamaAPI = new OllamaAPI();
        boolean isOllamaServerReachable = ollamaAPI.ping();
        System.out.println("Is Ollama server running: " + isOllamaServerReachable);
    }
}
```






