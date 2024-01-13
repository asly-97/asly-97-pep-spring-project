package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.exception.InvalidMessageDataException;
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
            throw new InvalidMessageDataException();
        }

        accountRepository.findById(newMessage.getPosted_by())
        .orElseThrow(InvalidMessageDataException::new);

        return messageRepository.save(newMessage);
    }

    public List<Message> getAllMessages(){
        return (List<Message>) messageRepository.findAll();
    }

    public Message getMessageById(int id){
        return messageRepository.findById(id)
                .orElse(null);
    }

    public Integer deleteMessageById(int id){
        Optional<Message> optionalMessage = messageRepository.findById(id);

        if(optionalMessage.isPresent()){
            Message message = optionalMessage.get();
            messageRepository.delete(message);
            return 1;
        }
        return null;
    }

    public int updateMessage(int message_id, String message_text){

        Message updatedMessage = messageRepository.findById(message_id)
                        .orElseThrow(InvalidMessageDataException::new);
        
        if(message_text.isBlank() || message_text.length() > 255){
            throw new InvalidMessageDataException();
        }

        updatedMessage.setMessage_text(message_text);

        messageRepository.save(updatedMessage);

        return 1;
    }

    public List<Message> getMessagesByAccountId(int account_id){
        return messageRepository.findAllByAccountId(account_id);
    }

    

    
}
