
package com.rag.local_rag_chatbot.controller;

import com.rag.local_rag_chatbot.service.RagService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final RagService ragService;

    public ChatController(RagService ragService) {
        this.ragService = ragService;
    }

    @GetMapping
    public String chat(@RequestParam String question) {
        return ragService.askQuestion(question);
    }
}
