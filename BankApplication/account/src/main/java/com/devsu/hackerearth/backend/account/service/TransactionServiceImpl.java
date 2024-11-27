package com.devsu.hackerearth.backend.account.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devsu.hackerearth.backend.account.exceptions.NotFoundExcepcion;
import com.devsu.hackerearth.backend.account.mapper.AccountMapper;
import com.devsu.hackerearth.backend.account.mapper.TransactionMapper;
import com.devsu.hackerearth.backend.account.model.Account;
import com.devsu.hackerearth.backend.account.model.Transaction;
import com.devsu.hackerearth.backend.account.model.dto.AccountDto;
import com.devsu.hackerearth.backend.account.model.dto.BankStatementDto;
import com.devsu.hackerearth.backend.account.model.dto.TransactionDto;
import com.devsu.hackerearth.backend.account.repository.AccountRepository;
import com.devsu.hackerearth.backend.account.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final AccountRepository accountRepository;

    private final TransactionMapper transactionMapper;

    private final AccountMapper accountMapper;

    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper,
            AccountRepository accountRepository, AccountMapper accountMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public List<TransactionDto> getAll() {
        // Get all transactions
        return transactionRepository.findAll().stream().map(transactionMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public TransactionDto getById(Long id) {
        // Get transactions by id
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new NotFoundExcepcion("Account " + id + " not found"));
        return transactionMapper.toResponse(transaction);
    }

    @Override
    public TransactionDto create(TransactionDto transactionDto) {
        // Create transaction
        Optional<AccountDto> existAccount = accountRepository.findById(transactionDto.getAccountId())
                .map(accountMapper::toResponse);
        if (!existAccount.isPresent()) {
            throw new NotFoundExcepcion("Account " + transactionDto.getAccountId() + " not found");
        }
        TransactionDto lastTransaction = this.getLastByAccountId(transactionDto.getAccountId());
        double lastBalance = lastTransaction.getId() != null ? lastTransaction.getBalance()
                : existAccount.get().getInitialAmount();
        if (lastBalance < transactionDto.getAmount()) {
            throw new NotFoundExcepcion("Saldo no disponible");
        }
        transactionDto.setBalance(lastBalance - transactionDto.getAmount());
        Transaction transaction = transactionMapper.toEntity(transactionDto);
        return transactionMapper.toResponse(transactionRepository.save(transaction));
    }

    @Override
    public List<BankStatementDto> getAllByAccountClientIdAndDateBetween(Long clientId, Date dateTransactionStart,
            Date dateTransactionEnd) {
        // Report
        // TODO
        transactionRepository.findTransactionsByAccountIdAndDateRange(clientId, dateTransactionStart,
                dateTransactionEnd);

        return null;
    }

    @Override
    public TransactionDto getLastByAccountId(Long accountId) {
        // If you need it
        Transaction transaction = transactionRepository.findFirstByAccountIdOrderByDateDesc(accountId)
                .orElse(new Transaction());
        return transactionMapper.toResponse(transaction);
    }

}
