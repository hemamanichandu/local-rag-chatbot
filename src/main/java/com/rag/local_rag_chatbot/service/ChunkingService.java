
package com.rag.local_rag_chatbot.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ChunkingService {

    private final DocumentService documentService;

    public ChunkingService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public List<String> createChunks() throws IOException {

        String text = documentService.readDocument();

        Document document = new Document(text);
        TokenTextSplitter splitter = TokenTextSplitter.builder()
                .withChunkSize(25)
                .withMinChunkSizeChars(40)
                .withMinChunkLengthToEmbed(5)
                .build();

        List<Document> chunks = splitter.apply(List.of(document));

        // ny streams chunk contents
        return chunks.stream()
                .map(Document::getText)
                .toList();
    }
}
