package org.example.allure_ai_analyser.ai_models;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAiModelConfiguration {

    @Bean
    public OpenAiChatModel openAiChatModel() {
        OpenAiApi baseOpenAiApi = OpenAiApi.builder()
                .apiKey(System.getenv("OPEN_API_KEY"))
                .baseUrl("https://api.openai.com")
                .build();

        return OpenAiChatModel.builder()
                .openAiApi(baseOpenAiApi)
                .defaultOptions(
                        OpenAiChatOptions.builder()
                                .model(OpenAiApi.ChatModel.GPT_4)
                                .temperature(0.7)
                                .build())
                .build();
    }
}
