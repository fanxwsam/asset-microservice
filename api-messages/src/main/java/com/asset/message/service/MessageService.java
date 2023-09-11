package com.asset.message.service;

import com.asset.message.dto.Message;
import com.asset.message.repo.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageService {
    private MessageRepository messageRepository;

    public void saveMessage(Message msg){
        messageRepository.save(msg);
    }


}
