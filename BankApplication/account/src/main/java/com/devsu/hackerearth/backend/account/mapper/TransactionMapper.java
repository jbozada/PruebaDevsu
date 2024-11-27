package com.devsu.hackerearth.backend.account.mapper;

import org.mapstruct.Mapper;

import com.devsu.hackerearth.backend.account.model.Transaction;
import com.devsu.hackerearth.backend.account.model.dto.TransactionDto;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionDto toResponse(Transaction entity);

    Transaction toEntity(TransactionDto dto);
}
