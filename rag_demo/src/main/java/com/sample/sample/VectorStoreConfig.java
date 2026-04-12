package com.sample.sample;


import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@AllArgsConstructor
public class VectorStoreConfig {

    private AppProperties appProperties;

    @Bean
    public EmbeddingStore<TextSegment> embeddingStore() {
        return PgVectorEmbeddingStore.builder()
                .user(appProperties.getUsername())
                .password(appProperties.getPassword())
                .database("pg-db")
                .dimension(1536)
                .createTable(true)
                .table("embeddings")
                .host("ep-wandering-bar-a1moigem-pooler.ap-southeast-1.aws.neon.tech")
                .port(5432)
                .build();
    }
    @Bean
    public EmbeddingModel embeddingModel() {
        return OpenAiEmbeddingModel.builder()
                .baseUrl(appProperties.getBaseUrl())
                .apiKey(appProperties.getOpenAiApiKey())
                .modelName(appProperties.getEmbeddingModelName())
                .build();
    }

    @Bean
    public ContentRetriever contentRetriever(EmbeddingStore<TextSegment> embeddingStore, EmbeddingModel embeddingModel) {
      return EmbeddingStoreContentRetriever.builder()
              .embeddingStore(embeddingStore)
              .embeddingModel(embeddingModel)
              .maxResults(5)
              .minScore(0.4)
              .build();
    }
}
