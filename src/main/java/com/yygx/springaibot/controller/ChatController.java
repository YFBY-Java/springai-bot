package com.yygx.springaibot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor   // 自动创建一个“必需参数”构造函数
@RestController
public class ChatController {

    private final ChatClient chatClient;

    @GetMapping("/chat")
    public String chat(String prompt){
        return chatClient
                .prompt()
                .user(prompt)
                .call()
                .content();
    }


    @GetMapping(value = "/chat/stream",produces = "text/html;charset=UTF-8")
    public Flux<String> chatStream(String prompt){
        return chatClient
                .prompt()
                .user(prompt)
                .stream()
                .content();
    }
}