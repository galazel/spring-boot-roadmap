package com.sample.sample;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@Slf4j
public class SampleController {

    private final ContractorBotService contractorBot;
    private final IngestionService ingestionService;

    @GetMapping
    public String index() {
        return "Hello World";
    }

    @PostMapping("/chat")
    public String chat(@RequestBody String message) {
        return contractorBot.ask(message);
    }

    @PostMapping(value = "/ingest", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> ingest(@RequestParam("file") MultipartFile file) {
        ingestionService.ingest(file);
        return ResponseEntity.ok("Ingested: " + file.getOriginalFilename());
    }
}