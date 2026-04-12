package com.langchain_demo.langchain_demo;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.github.GitHubModelsChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LangchainConfig {

    @Value("${langchain4j.github.chat-model.git-hub-token}")
    private String token;
    @Value("${langchain4j.github.chat-model.model-name}")
    private String modelName;
    @Value("${langchain4j.github.chat-model.temperature}")
    private double temperature;
    @Value("${langchain4j.github.chat-model.max-tokens}")
    private int maxTokens;

    @Bean
    public ChatLanguageModel chatLanguageModel() {
        return GitHubModelsChatModel.builder()
                .gitHubToken(token)
                .modelName(modelName)
                .temperature(temperature)
                .maxTokens(maxTokens)
                .build();
    }

}
