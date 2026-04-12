package com.sample.sample;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContractorBotService {

    private final ContractorBotAssistant contractorBotAssistant;

    public String ask(String message)
    {
        return contractorBotAssistant.ask(message);
    }
}
