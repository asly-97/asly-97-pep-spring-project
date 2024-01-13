package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.exception.DuplicateUsernameException;
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

}
