package com.yygx.springaibot.controller;

import com.yygx.springaibot.entity.vo.MessageVO;
import com.yygx.springaibot.repository.ChatHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;


@RequestMapping("/ai")
@RequiredArgsConstructor   // 自动创建一个“必需参数”构造函数
@RestController
public class ChatController {

    public static final String CHAT = "chat";
    private final ChatClient chatClient;
    private final ChatHistoryRepository chatHistoryRepository;

    @PostMapping(value = "/chat",produces = "text/html;charset=UTF-8")
    public Flux<String> chatStream(String prompt,String chatId){
        // 保存会话id
        chatHistoryRepository.save(CHAT,chatId);
        // 请求模型
        return chatClient
                .prompt()
                .user(prompt)
                .advisors(advisorSpec -> advisorSpec.param(CONVERSATION_ID,chatId))
                .stream()
                .content();
    }
}