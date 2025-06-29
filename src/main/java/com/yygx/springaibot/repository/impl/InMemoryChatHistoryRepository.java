package com.yygx.springaibot.repository.impl;


import com.yygx.springaibot.repository.ChatHistoryRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryChatHistoryRepository implements ChatHistoryRepository {

    private final Map<String,List<String>> chatHistory = new HashMap<>();

    @Override
    public void save(String type, String chatId) {
        // 若不存在该 key，则通过 computeIfAbsent 创建一个新的 ArrayList 并放入 map
        List<String> chatIds = chatHistory.computeIfAbsent(type, k -> new ArrayList<>());
        if(chatIds.contains(chatId)){
            return;
        }
        chatIds.add(chatId);
    }

    @Override
    public List<String> getChatIds(String type) {
        // 若不存在该 key，则返回一个空的 ArrayList
        return chatHistory.getOrDefault(type,new ArrayList<>());
    }
}