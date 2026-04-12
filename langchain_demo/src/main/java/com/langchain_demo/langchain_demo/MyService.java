package com.langchain_demo.langchain_demo;

import dev.langchain4j.model.chat.ChatLanguageModel;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    private ProgressBotAI progressBotAI;
    public MyService(ProgressBotAI progressBotAI) {
        this.progressBotAI = progressBotAI;
    }

    public String handle(String input)
    {
        return progressBotAI.chat(input);
    }
}
