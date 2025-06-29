package com.yygx.springaibot.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ai.chat.messages.Message;

@NoArgsConstructor  // 无参构造
@Data
public class MessageVO {

    private String role;
    private String content;

    public MessageVO(Message message){
        switch (message.getMessageType()){
            case USER -> this.role = "user";
            case SYSTEM -> this.role = "system";
            default -> this.role = "";
        }
        this.content = message.getText();
    }
}