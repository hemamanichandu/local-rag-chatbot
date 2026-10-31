package com.rag.local_rag_chatbot.controller;

import com.rag.local_rag_chatbot.service.VectorStoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class VectorStoreController {

    private final VectorStoreService vectorStoreService;

    public VectorStoreController(VectorStoreService vectorStoreService) {
        this.vectorStoreService = vectorStoreService;
    }

    @PostMapping("/vectors/store")
    public String storeVectors() throws IOException {

        int count = vectorStoreService.storeChunks();

        return count + " chunks successfully embedded and stored!";
    }

    @GetMapping("/vectors/search")
    public List<String> search(@RequestParam String query) {

        return vectorStoreService.search(query);
    }
}
