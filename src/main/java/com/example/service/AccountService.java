package com.example.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.exception.DuplicateUsernameException;
import com.example.exception.PasswordTooShortException;
import com.example.exception.UnauthorizedLoginException;
import com.example.exception.UsernameBlankException;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account register(Account account){
        
        // Busines logic

        if(account.getUsername().isBlank()){
            throw new UsernameBlankException();
        }

        if(account.getPassword().length() < 4){
            throw new PasswordTooShortException();
        }

        // Check if the username is already exist
        accountRepository.findByUsername(account.getUsername())
        .orElseThrow(DuplicateUsernameException::new);

        return accountRepository.save(account);
    }

    public Account login(Account account){
        String username = account.getUsername();
        String password = account.getPassword();

        Account loggedInAccount = 
        accountRepository.findByUsernameAndPassword(username,password)
        .orElseThrow(() -> new UnauthorizedLoginException());

        //Successful
        return loggedInAccount;
    }

}
