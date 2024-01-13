package com.example.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.exception.DuplicateUsernameException;
import com.example.exception.PasswordTooShortException;
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
        Optional<Account> optAccount = accountRepository.findByUsername(account.getUsername());
        if(optAccount.isPresent()){
            throw new DuplicateUsernameException();
        }

        return accountRepository.save(account);
    }

}
