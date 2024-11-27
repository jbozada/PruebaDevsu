package com.devsu.hackerearth.backend.client.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.devsu.hackerearth.backend.client.model.Client;
import com.devsu.hackerearth.backend.client.model.dto.ClientDto;
import com.devsu.hackerearth.backend.client.model.dto.PartialClientDto;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDto toResponse(Client entity);

    Client clientDtoToEntity(ClientDto clienteDto);

    void updateInfoEntity(ClientDto clientDto, @MappingTarget Client client);

    void updatePartialInfoEntity(PartialClientDto partialDto, @MappingTarget Client client);
}
