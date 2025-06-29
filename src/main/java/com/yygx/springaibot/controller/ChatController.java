package com.yygx.springaibot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;


@RequestMapping("/ai")
@RequiredArgsConstructor   // 自动创建一个“必需参数”构造函数
@RestController
public class ChatController {

    private final ChatClient chatClient;

    @PostMapping(value = "/chat",produces = "text/html;charset=UTF-8")
    public Flux<String> chatStream(String prompt,String chatId){
        return chatClient
                .prompt()
                .user(prompt)
                .advisors(advisorSpec -> advisorSpec.param(CONVERSATION_ID,chatId))
                .stream()
                .content();
    }
}