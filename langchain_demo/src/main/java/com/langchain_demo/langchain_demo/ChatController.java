package com.langchain_demo.langchain_demo;

import com.azure.core.annotation.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatController {

    private MyService myService;
    public ChatController(MyService myService) {
        this.myService = myService;
    }
    @GetMapping
    public String index() {
        return "Hello World!";
    }

    @PostMapping("/chat")
    public String chat(@RequestBody String input) {
        return myService.handle(input);
    }

}
