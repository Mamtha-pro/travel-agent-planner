package com.substring.agent.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.substring.agent.backend.service.ChatServices;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatServices chatService;

    public ChatController(ChatServices chatService) {
        this.chatService = chatService;
    }
@PostMapping
public ResponseEntity<String> chat(
        @RequestHeader("Conversation-Id") String conversationId,
        @RequestBody String message) {

    return ResponseEntity.ok(
            chatService.chat(message, conversationId)
    );
}
}