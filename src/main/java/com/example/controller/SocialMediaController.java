package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.DuplicateUsernameException;
import com.example.exception.InvalidMessageTextException;
import com.example.exception.PasswordTooShortException;
import com.example.exception.UnauthorizedLoginException;
import com.example.exception.UsernameBlankException;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 @RestController
public class SocialMediaController {

    private AccountService accountService;
    private MessageService messageService;

    

    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }



    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody Account account){
        return ResponseEntity.ok()
                .body(accountService.register(account));
    }

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account){
        return ResponseEntity.ok()
                .body(accountService.login(account));
    }

    @PostMapping("messages")
    public ResponseEntity<Message> createNewMessage(@RequestBody Message message){
        return ResponseEntity.ok()
                .body(messageService.createMessage(message));
    }

    @GetMapping("messages")
    public ResponseEntity< List<Message> > getAllMessages(){
        return ResponseEntity.ok()
                .body(messageService.getAllMessages());
    }

    @GetMapping("messages/{message_id}")
    public ResponseEntity<Message> getMessageById(@PathVariable int message_id){
        return ResponseEntity.status(200)
                .body(messageService.getMessageById(message_id));
    }

    @DeleteMapping("messages/{message_id}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable int message_id){
        Integer res = messageService.deleteMessageById(message_id) == 1 ? 1 : null;
        return ResponseEntity.status(200)
                .body(res);
    }



    // Handling Exceptions --- Registration

    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<String> handleDuplicateUsernameException(DuplicateUsernameException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                            .body(ex.getMessage());
    }

    @ExceptionHandler({
        UsernameBlankException.class,
        PasswordTooShortException.class
    })
    public ResponseEntity<String> handleInvalidEntryExceptions(Exception ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(ex.getMessage());
    }



    // Handling Exceptions --- Login

    @ExceptionHandler(UnauthorizedLoginException.class)
    public ResponseEntity<String> handleUnauthorizedLoginException(UnauthorizedLoginException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                            .body(ex.getMessage());
    }

    // Handling Exceptions --- Create New Message

    @ExceptionHandler(InvalidMessageTextException.class)
    public ResponseEntity<String> handleInvalidMessageTextException(InvalidMessageTextException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(ex.getMessage());
    }

}
