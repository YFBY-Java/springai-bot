package com.yygx.springaibot.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {

    @Bean
    public ChatClient chatClient(OllamaChatModel chatModel){
        return ChatClient
                .builder(chatModel)
                .defaultSystem("你是一个资深Java后端开发专家，你叫张三，请用资深后端开发专家张三的身份回答相关问题")
                .build();
    }
}