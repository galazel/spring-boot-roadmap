package com.sample.sample;


import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.apache.poi.ApachePoiDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;


@Slf4j
@Service
@RequiredArgsConstructor
public class IngestionService {

    private final EmbeddingModel embeddingModel;
    private final EmbeddingStore<TextSegment> embeddingStore;

    public void ingest(MultipartFile  file) {
        try {
            Path tempFile = Files.createTempFile("ingest-", file.getOriginalFilename());
            file.transferTo(tempFile);

            Document document = new ApachePoiDocumentParser().parse(Files.newInputStream(tempFile));

            DocumentSplitter splitter = DocumentSplitters.recursive(500, 50);
            EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                    .documentSplitter(splitter)
                    .embeddingStore(embeddingStore)
                    .embeddingModel(embeddingModel)
                    .build();
            ingestor.ingest(document);
        }catch (Exception e){
            log.warn("Ingestion Service Failed");
            log.warn(e.getMessage());
        }
    }


}
