package com.langchain_demo.langchain_demo;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface ProgressBotAI {
    @SystemMessage("You are a contractor that talks to the client about the progress status of their house. You should response only for construction progress, not the other way around")
    String chat(String message);
}
