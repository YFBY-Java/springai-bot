package com.yygx.springaibot.controller;

import com.yygx.springaibot.entity.vo.MessageVO;
import com.yygx.springaibot.repository.ChatHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor // 自动创建一个“必需参数”构造函数
@RequestMapping("/ai/history")
@RestController
public class ChatHistoryController {

    private final ChatHistoryRepository chatHistoryRepository;
    private final ChatMemory chatMemory;

    @GetMapping("/{type}")
    public List<String> getChatIds(@PathVariable("type") String type){
        return chatHistoryRepository.getChatIds(type);
    }


    @GetMapping("/{type}/{chatId}")
    public List<MessageVO> getChatHistory(@PathVariable String type, @PathVariable String chatId){
        List<Message> messages = chatMemory.get(chatId);
        if(messages == null){
            return List.of();
        }
        return messages.stream().map(MessageVO::new).toList();
    }

}