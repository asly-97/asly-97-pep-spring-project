package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.exception.InvalidMessageTextException;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    private MessageRepository messageRepository;
    private AccountRepository accountRepository;


    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message createMessage(Message newMessage){

        String textMessage = newMessage.getMessage_text();

        if(textMessage.isBlank() || textMessage.length() > 255){
            throw new InvalidMessageTextException();
        }

        accountRepository.findById(newMessage.getPosted_by())
        .orElseThrow(InvalidMessageTextException::new);

        return messageRepository.save(newMessage);
    }

    public List<Message> getAllMessages(){
        return (List<Message>) messageRepository.findAll();
    }

    

    
}
