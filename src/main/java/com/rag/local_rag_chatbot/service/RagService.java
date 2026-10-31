
package com.rag.local_rag_chatbot.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class RagService {

    private final ChatClient chatClient;
    private final VectorStoreService vectorStoreService;

    public RagService(ChatClient.Builder builder,
                      VectorStoreService vectorStoreService) {

        this.chatClient = builder.build();
        this.vectorStoreService = vectorStoreService;
    }

    public String askQuestion(String question) {

        var chunks = vectorStoreService.search(question);

        String context = String.join("\n", chunks);

        return chatClient.prompt()
                .system("""
                        Answer the question using only the provided context.
                        If the answer is not present in the context,
                        say "I don't know based on the provided document."
                        Context:
                        """ + context)
                .user(question)
                .call()
                .content();
    }
}
