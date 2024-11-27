package com.devsu.hackerearth.backend.account.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.devsu.hackerearth.backend.account.model.Account;
import com.devsu.hackerearth.backend.account.model.dto.AccountDto;
import com.devsu.hackerearth.backend.account.model.dto.PartialAccountDto;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDto toResponse(Account entity);

    Account toEntity(AccountDto dto);

    void updateInfoEntity(AccountDto dto, @MappingTarget Account entity);

    void updatePartialInfoEntity(PartialAccountDto dto, @MappingTarget Account entity);
}
