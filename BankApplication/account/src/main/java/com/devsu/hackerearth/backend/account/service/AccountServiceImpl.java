package com.devsu.hackerearth.backend.account.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devsu.hackerearth.backend.account.exceptions.AlreadyExistsException;
import com.devsu.hackerearth.backend.account.exceptions.NotFoundExcepcion;
import com.devsu.hackerearth.backend.account.mapper.AccountMapper;
import com.devsu.hackerearth.backend.account.model.Account;
import com.devsu.hackerearth.backend.account.model.dto.AccountDto;
import com.devsu.hackerearth.backend.account.model.dto.PartialAccountDto;
import com.devsu.hackerearth.backend.account.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public List<AccountDto> getAll() {
        // Get all accounts
        return accountRepository.findAll().stream().map(accountMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public AccountDto getById(Long id) {
        // Get accounts by id
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundExcepcion("Account " + id + " not found"));
        return accountMapper.toResponse(account);
    }

    @Override
    public AccountDto create(AccountDto accountDto) {
        // Create account
        Optional<AccountDto> existAccount = accountRepository.findByNumber(accountDto.getNumber())
                .map(accountMapper::toResponse);
        if (existAccount.isPresent()) {
            throw new AlreadyExistsException("Account " + accountDto.getNumber() + " exist");
        }
        Account account = accountMapper.toEntity(accountDto);
        return accountMapper.toResponse(accountRepository.save(account));
    }

    @Override
    public AccountDto update(AccountDto accountDto) {
        // Update account
        Account account = accountRepository.findByNumber(accountDto.getNumber())
                .orElseThrow(() -> new NotFoundExcepcion("Account " + accountDto.getNumber() + " not found"));
        accountMapper.updateInfoEntity(accountDto, account);
        return accountMapper.toResponse(accountRepository.save(account));
    }

    @Override
    public AccountDto partialUpdate(Long id, PartialAccountDto partialAccountDto) {
        // Partial update account
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundExcepcion("Account " + id + " not found"));
        accountMapper.updatePartialInfoEntity(partialAccountDto, account);
        Account savedAccount = accountRepository.save(account);
        return accountMapper.toResponse(savedAccount);
    }

    @Override
    public void deleteById(Long id) {
        // Delete account
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundExcepcion("Account " + id + " not found"));
        account.setActive(false);
        accountRepository.save(account);
    }

}
