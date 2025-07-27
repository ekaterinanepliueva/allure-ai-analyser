package org.example.allure_ai_analyser.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.allure_ai_analyser.input_models.TestCaseErrorModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenAiAnalyserRestController {

    private final ChatClient chatClient;
    private final Prompt prompt;
    private final ObjectMapper objectMapper;

    public OpenAiAnalyserRestController(@Qualifier("openAiChatClient") ChatClient.Builder chatClientBuilder,
                                        Prompt prompt, ObjectMapper objectMapper) {
        chatClient = chatClientBuilder.build();
        this.prompt = prompt;
        this.objectMapper = objectMapper;
    }

    @PostMapping(path = "/ai-completion", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> completion(@RequestBody TestCaseErrorModel message) {
        try {
            String request = objectMapper.writeValueAsString(message);
            String result = chatClient.prompt(prompt).user(request).call().content();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
}
