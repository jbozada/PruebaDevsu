package com.devsu.hackerearth.backend.mapper;

import com.devsu.hackerearth.backend.account.mapper.AccountMapper;
import com.devsu.hackerearth.backend.account.model.Account;
import com.devsu.hackerearth.backend.account.model.dto.AccountDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-27T18:39:57+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 11.0.25 (Ubuntu)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public AccountDto toResponse(Account entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String number = null;
        String type = null;
        double initialAmount = 0.0d;
        boolean isActive = false;
        Long clientId = null;

        AccountDto accountDto = new AccountDto( id, number, type, initialAmount, isActive, clientId );

        return accountDto;
    }
}
