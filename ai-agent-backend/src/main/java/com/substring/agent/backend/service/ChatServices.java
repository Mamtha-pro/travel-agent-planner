package com.substring.agent.backend.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;

@Service
public class ChatServices {

    private final ChatClient chatClient;

    public ChatServices(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

  public String chat(String query, String conversationId) {

    // Use the chatClient to send the query and get response
    return chatClient
            .prompt()
            .user(query)
            .advisors(a ->
                    a.param(ChatMemory.CONVERSATION_ID, conversationId)
            )
            .call()
            .content();
}
}