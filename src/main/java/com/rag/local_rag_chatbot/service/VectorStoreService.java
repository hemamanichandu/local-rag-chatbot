
package com.rag.local_rag_chatbot.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class VectorStoreService {

    private final VectorStore vectorStore;
    private final ChunkingService chunkingService;

    public VectorStoreService(
            EmbeddingModel embeddingModel,
            ChunkingService chunkingService) {

        this.vectorStore = SimpleVectorStore.builder(embeddingModel).build();
        this.chunkingService = chunkingService;
    }

    public int storeChunks() throws IOException {

        List<Document> documents = chunkingService.createChunks()
                .stream()
                .map(Document::new)
                .toList();

        vectorStore.add(documents);

        return documents.size();
    }



    public List<String> search(String query) {

        List<Document> results = vectorStore.similaritySearch(
                SearchRequest.builder()
                        .query(query)
                        .topK(2)
                        .build()
        );

        return results.stream()
                .map(Document::getText)
                .toList();
    }
}
