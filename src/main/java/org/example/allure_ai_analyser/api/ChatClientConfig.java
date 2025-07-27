package org.example.allure_ai_analyser.api;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ChatClientConfig {

    @Bean
    public Prompt prompt(@Value("classpath:/prompts/failure-analysis.txt") Resource resource) {
        return new Prompt(new SystemMessage(resource));
    }

    @Bean
    public ChatClient.Builder openAiChatClient(OpenAiChatModel openAiChatModel) {
        return ChatClient.create(openAiChatModel).mutate();
    }
}
