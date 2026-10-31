
package com.rag.local_rag_chatbot.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class DocumentService {

    public String readDocument() throws IOException {

        ClassPathResource resource =
                new ClassPathResource("knowledge.txt");

        return resource.getContentAsString(StandardCharsets.UTF_8);
    }
}
