package com.rag.local_rag_chatbot.controller;

import com.rag.local_rag_chatbot.service.ChunkingService;
import com.rag.local_rag_chatbot.service.DocumentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class DocumentController {

    private final DocumentService documentService;
    private final ChunkingService chunkingService;
    public DocumentController(DocumentService documentService, ChunkingService chunkingService) {
        this.documentService = documentService;
        this.chunkingService=chunkingService;
    }

    @GetMapping("/document")
    public String getDocument() throws IOException {
        return documentService.readDocument();
    }
    @GetMapping("/chunks")
    public List<String> getChunks() throws IOException {
        return chunkingService.createChunks();
    }

}
