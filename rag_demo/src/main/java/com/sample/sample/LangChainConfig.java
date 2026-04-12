package com.sample.sample;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
@Slf4j
public class LangChainConfig {

    private final AppProperties appProperties;
    @Bean
    public ChatMemory chatMemory() {
        return MessageWindowChatMemory.withMaxMessages(20);
    }
    @Bean
    public ChatLanguageModel chatLanguageModel() {
        log.info(appProperties.getOpenAiApiKey());
        return OpenAiChatModel.builder()
                .apiKey(appProperties.getOpenAiApiKey())
                .baseUrl(appProperties.getBaseUrl())
                .modelName(appProperties.getAiModelName())
                .build();
    }

}
